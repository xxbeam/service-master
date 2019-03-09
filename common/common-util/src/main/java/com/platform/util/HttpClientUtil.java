package com.platform.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Httpclient工具类
 * @author zhanggj
 * created at 2017年4月25日
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String get(String url) throws Exception {
    	return get(url, "UTF-8");
    }
    
    /**
     * 使用get方式调用
     * 
     * @param url 调用的URL
     * @return 调用得到的字符串
     */
    public static String get(String url, String charset) throws Exception {
        logger.debug("get url: " + url);
        StringBuilder sb = new StringBuilder();
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        if (StringUtils.isEmpty(charset)) {
        	charset = "UTF-8";
        } 
        try {
            // 执行getMethod
            httpClient.executeMethod(getMethod);
            // 以流的行式读入，避免中文乱码
            InputStream is = getMethod.getResponseBodyAsStream();
            BufferedReader dis = new BufferedReader(new InputStreamReader(is, charset));
            String str = "";
            while ((str = dis.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception e) {
            // 发生网络异常
            logger.error("HttpClientUtil.get:httpClient调用远程出错;发生网络异常", e);
            throw e;
        } finally {
            // 关闭连接
            getMethod.releaseConnection();
        }
        return sb.toString();
    }

    public static String post(String url, Map<String, String> params) {
        return post(url, params, "UTF-8");
    }
    
    public static String post(String url, Map<String, String> params, String charset) {
        logger.debug("post url: " + url);
        StringBuilder sb = new StringBuilder();
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        if (StringUtils.isEmpty(charset)) {
        	charset = "UTF-8";
        } 
        
        if(params != null) {
	        NameValuePair[] values = convertParams(params);
	        postMethod.addParameters(values);
	    }
        
        try {
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            // 执行postMethod
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(20000);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
            httpClient.executeMethod(postMethod);
            
            // 以流的行式读入，避免中文乱码
            InputStream is = postMethod.getResponseBodyAsStream();
            BufferedReader dis = new BufferedReader(new InputStreamReader(is, charset));
            String str = "";
            while ((str = dis.readLine()) != null) {
                sb.append(str);
            }

        } catch (Exception e) {
            logger.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常", e);
        } finally {
            postMethod.releaseConnection();
        }
        return sb.toString();
    }
    
    public static String postWithHeader(String url, Map<String, String> params, String charset,Map<String,String> headers,String body) {
        logger.debug("post url: " + url);
        StringBuilder sb = new StringBuilder();
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        if (StringUtils.isEmpty(charset)) {
        	charset = "UTF-8";
        } 
        
        if(headers != null && headers.size() > 0){
        	Iterator<String> its = headers.keySet().iterator();
        	while(its.hasNext()){
        		String key = its.next();
        		Header header = new Header();
        		header.setName(key);
        		header.setValue(headers.get(key));
        		postMethod.setRequestHeader(header);
        	}
        }
        
        if(StringUtils.isNotEmpty(body)){
        	postMethod.setRequestBody(body);
        }
        
        if(params != null) {
	        NameValuePair[] values = convertParams(params);
	        postMethod.addParameters(values);
	    }
        
        try {
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            // 执行postMethod
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(20000);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
            httpClient.executeMethod(postMethod);
            
            // 以流的行式读入，避免中文乱码
            InputStream is = postMethod.getResponseBodyAsStream();
            BufferedReader dis = new BufferedReader(new InputStreamReader(is, charset));
            String str = "";
            while ((str = dis.readLine()) != null) {
                sb.append(str);
            }

        } catch (Exception e) {
            logger.error("HttpClientUtil.httpClientPost():httpClient调用远程出错;发生网络异常", e);
        } finally {
            postMethod.releaseConnection();
        }
        return sb.toString();
    }

    private static NameValuePair[] convertParams(Map<String, String> map) {

        NameValuePair[] nvps = new NameValuePair[map.size()];
        Set<String> keys = map.keySet();
        int i = 0;
        for (String key : keys) {
            nvps[i] = new NameValuePair();
            nvps[i].setName(key);
            nvps[i].setValue(String.valueOf(map.get(key)));
            i++;
        }

        return nvps;
    }

}
