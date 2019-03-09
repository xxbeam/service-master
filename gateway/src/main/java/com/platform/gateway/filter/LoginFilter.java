package com.platform.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.platform.base.RetCode;
import com.platform.dto.accessAuth.AccessAuthDTO;
import com.platform.dto.user.UserTokenDTO;
import com.platform.sso.service.AccessAuthServiceFeignApi;
import com.platform.sso.service.SsoUserServiceFeignApi;
import com.platform.util.ResultUtil;
import com.platform.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class LoginFilter extends ZuulFilter {

    @Autowired
    AccessAuthServiceFeignApi accessAuthServiceFeignApi;
    @Autowired
    SsoUserServiceFeignApi ssoUserServiceFeignApi;


    @Override
    public String filterType() {
        return "pre"; //定义filter的类型，有pre、route、post、error四种
    }

    @Override
    public int filterOrder() {
        return 0; //定义filter的顺序，数字越小表示顺序越高，越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; //表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //获取访问白名单'
        AccessAuthDTO authDTO = new AccessAuthDTO();
        authDTO.setGateway("gateway");
        authDTO.setPath(request.getServletPath());
        JsonResult jsonResult =accessAuthServiceFeignApi.checkLoginPathAuth(authDTO);
        if(jsonResult.getRetcode()== RetCode.NEED_LOGIN.getCode()){
            String token = request.getHeader("token");
            //判断token
            if (token == null || token.isEmpty()) {
                JsonResult loginResult = ResultUtil.error(RetCode.NEED_LOGIN,"token校验失败");
                ctx.setSendZuulResponse(false); //不对其进行路由
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody(JSON.toJSONString(loginResult));
                ctx.set("isSuccess", false);
                return null;
            }else{
                //请求sso认证token
                UserTokenDTO userTokenDTO = new UserTokenDTO();
                userTokenDTO.setToken(token);
                JsonResult loginResult = ssoUserServiceFeignApi.checkLogin(userTokenDTO);
                if(loginResult.getRetcode()!=RetCode.SUCCESS.getCode()){
                    ctx.setSendZuulResponse(false); //不对其进行路由
                    ctx.setResponseStatusCode(200);
                    ctx.setResponseBody(JSON.toJSONString(loginResult));
                    ctx.set("isSuccess", false);
                    return null;
                }
             }
        }else if(jsonResult.getRetcode()!= RetCode.SUCCESS.getCode()){
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(200);
            ctx.setResponseBody(JSON.toJSONString(jsonResult));
            ctx.set("isSuccess", false);
            return null;
        }
        ctx.setSendZuulResponse(true); //对请求进行路由
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
        return null;
    }
}
