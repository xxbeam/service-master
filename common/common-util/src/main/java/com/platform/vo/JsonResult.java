/**
 * 通用的返回JSON数据的结构s
 * Created by zhanggj
 * At 2013-4-12
 */
package com.platform.vo;

import com.platform.base.RetCode;
import org.apache.commons.lang.StringUtils;

public class JsonResult {
	private int retcode; // 状态码
	private String retmsg; // 消息
	private Object retdata; // 返回的数据

	public JsonResult() {
	}

	public JsonResult(RetCode retcode) {
		this.retcode = retcode.getCode();
		this.retmsg = retcode.getMsg();
	}

	public JsonResult(RetCode retcode, String retmsg) {
		this.retcode = retcode.getCode();
		if(StringUtils.isNotEmpty(retmsg)){
			this.retmsg = retmsg;
		}else{
			this.retmsg = retcode.getMsg();
		}
	}

	public JsonResult(RetCode retcode, Object retdata) {
		this.retcode = retcode.getCode();
		this.retmsg = retcode.getMsg();
		this.retdata = retdata;
	}

	public JsonResult(RetCode retcode, String retmsg, Object retdata) {
		this.retcode = retcode.getCode();
		if(StringUtils.isNotEmpty(retmsg)){
			this.retmsg = retmsg;
		}else{
			this.retmsg = retcode.getMsg();

		}
		this.retdata = retdata;
	}

	public void setRetCode(RetCode retcode, String retmsg){
		this.retcode = retcode.getCode();
		if(StringUtils.isNotEmpty(retmsg)){
			this.retmsg = retmsg;
		}else{
			this.retmsg = retcode.getMsg();
		}
	}

	public void setRetCode(RetCode retcode){
		this.retcode = retcode.getCode();
		this.retmsg = retcode.getMsg();
	}

	public int getRetcode() {
		return retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public Object getRetdata() {
		return retdata;
	}

	public void setRetdata(Object retdata) {
		this.retdata = retdata;
	}
	
}
