package com.platform.sso.controller;

import com.platform.base.BaseController;
import com.platform.base.RetCode;
import com.platform.dto.user.UserDTO;
import com.platform.dto.user.UserTokenDTO;
import com.platform.service.RedisService;
import com.platform.sso.common.Const;
import com.platform.user.service.UserServiceFeignApi;
import com.platform.util.ResultUtil;
import com.platform.util.jwt.JwtSSOUtil;
import com.platform.util.jwt.JwtUserConst;
import com.platform.vo.JsonResult;
import com.platform.vo.user.UserVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserServiceFeignApi userServiceFeignApi;

    @Autowired
    RedisService<String> redisStringService;

    @ApiOperation(value="登陆", notes="根据账号密码获取用户信息")
    @ApiImplicitParam(name = "userDTO", value = "用户传输实体类", required = true, dataType = "UserDTO")
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public JsonResult login(@RequestBody @Valid UserDTO userDTO) throws Exception {
        JsonResult jsonResult = userServiceFeignApi.login(userDTO);
        if(jsonResult.getRetcode()==RetCode.SUCCESS.getCode()){
            UserVo user =  ResultUtil.parseRetData(jsonResult,UserVo.class);
            //生成token
            Map<String,String> jwtMap = JwtSSOUtil.createJWT(0L,user);
            user.setToken(jwtMap.get("token"));
            user.setExpires(jwtMap.get("expires"));

            redisStringService.setForValue(Const.USER_LOGIN_TOKEN_KEY+user.getId(),jwtMap.get("token"),Long.parseLong(jwtMap.get("expiresmills")),TimeUnit.MILLISECONDS);
            return ResultUtil.ok(user);
        }else{
            return jsonResult;
        }

    }

    @ApiOperation(value="校验user登陆", notes="判断传递的token是否有效")
    @ApiImplicitParam(name = "userTokenDTO", value = "用户token传输类", required = true, dataType = "UserTokenDTO")
    @RequestMapping(value="/checkLogin", method=RequestMethod.POST)
    public JsonResult checkLogin(@RequestBody @Valid UserTokenDTO userTokenDTO) throws Exception {
        String token = userTokenDTO.getToken();
        int loginUserId = this.getUserIdByToken(token, JwtUserConst.USER);
        //判断redis中是否有token
        String redisToken =  redisStringService.getForValue(Const.USER_LOGIN_TOKEN_KEY+loginUserId);
        if(!token.equals(redisToken)){
            return ResultUtil.error(RetCode.NEED_LOGIN,"token校验失败");
        }
        JsonResult jsonResult = userServiceFeignApi.getUserById(loginUserId);
        if(jsonResult.getRetcode()==RetCode.SUCCESS.getCode()){
            return ResultUtil.ok();
        }else{
            return jsonResult;
        }
    }

    @ApiOperation(value="退出登录", notes="删除redis缓存的token")
    @RequestMapping(value="/loginOut", method=RequestMethod.POST)
    public JsonResult loginOut() throws Exception {
        //从header中获取当前token
        String token = this.getToken();
        int loginUserId = this.getUserIdByToken(token, JwtUserConst.USER);

        //判断token是否在redis中
        String redisToken = redisStringService.getForValue(Const.USER_LOGIN_TOKEN_KEY+loginUserId);
        if(!redisToken.equals(token)){
            //删除redis中的token
            return ResultUtil.error(RetCode.NEED_LOGIN,"token已失效，退出登录失败");
        }
        redisStringService.removeKey(Const.USER_LOGIN_TOKEN_KEY+loginUserId);
        return ResultUtil.ok();
    }

}
