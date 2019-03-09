package com.platform.base;

import com.platform.exception.ResponseExpection;
import com.platform.util.jwt.JwtSSOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BaseController {

    /**
     * 取request对象
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取token
     * @return
     */
    public String getToken(){
        return this.getRequest().getHeader("token");
    }

    /**
     * 根据token获取登陆用户id
     * @param token
     * @param from
     * @return
     */
    public int getUserIdByToken(String token,String from){
        Map<String,Object> tokenMap = JwtSSOUtil.parseJWT(token, "user");
        if(tokenMap==null){
            throw new ResponseExpection(RetCode.NEED_LOGIN,"token校验失败");
        }
        int loginUserId = Integer.parseInt(tokenMap.get("userId").toString());
        return loginUserId;
    }

    /**
     * 判断userId是否为当前登陆用户
     * @param userId
     * @param from
     * @return
     */
    public boolean checkTokenUser(int userId,String from){
        String token = this.getToken();
        if(StringUtils.isEmpty(token)){
            throw new ResponseExpection(RetCode.NEED_LOGIN,"token不存在");
        }
        Map<String,Object> tokenMap = JwtSSOUtil.parseJWT(token, "user");
        if(tokenMap==null){
            throw new ResponseExpection(RetCode.NEED_LOGIN,"token校验失败");
        }
        int loginUserId = Integer.parseInt(tokenMap.get("userId").toString());
        return userId==loginUserId;
    }
}
