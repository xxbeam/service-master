package com.platform.sso.controller;

import com.platform.base.RetCode;
import com.platform.dto.accessAuth.AccessAuthDTO;
import com.platform.service.RedisService;
import com.platform.sso.bo.AccessAuth;
import com.platform.sso.common.Const;
import com.platform.sso.service.AccessAuthService;
import com.platform.util.ResultUtil;
import com.platform.vo.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/access")
public class AccessAuthController {


    @Autowired
    RedisService<String> stringRedisService;

    @Autowired
    AccessAuthService accessAuthService;

    @ApiOperation(value="判断网关地址是否要登陆", notes="根据网关类型和访问地址判断是否需要登陆")
    @ApiImplicitParam(name = "accessAuthDTO", value = "登陆权限传输类", required = true, dataType = "AccessAuthDTO")
    @RequestMapping(value="/checkLoginPathAuth", method= RequestMethod.POST)
    public JsonResult checkLoginPathAuth(@RequestBody @Valid AccessAuthDTO accessAuthDTO) {
        boolean flag = false;
        String key = Const.GATEWAY_LOGIN_ACCESS_AUTH_KEY+accessAuthDTO.getGateway();
        //从redis获取网关白名单
        Set<String> set = stringRedisService.getForSet(key);
        if(set==null||set.size()==0){
            List<AccessAuth> list = accessAuthService.getAllAccessAuth(accessAuthDTO.getGateway());
            for (AccessAuth accessAuth:list){
                String path = "/"+accessAuth.getServer()+accessAuth.getPath();
                if(accessAuthDTO.getPath().matches(path)){
                    flag = true;
                }
                stringRedisService.setForSet(key,path);
            }
        }else{
            for (String path:set) {
                if(accessAuthDTO.getPath().matches(path)){
                    flag = true;
                    break;
                }
            }
            if(set.contains(accessAuthDTO.getPath())){
                flag = true;
            }
        }

        if(!flag){
            return ResultUtil.error(RetCode.NEED_LOGIN,"该地址需要登陆");
        }
        return ResultUtil.ok();
    }

    @ApiOperation(value="刷新网关的登陆白名单", notes="刷新网关的登陆白名单")
    @ApiImplicitParam(name = "gateway", value = "网关名称", required = true,paramType = "path",dataType = "String")
    @RequestMapping(value="/refreshLoginPathAuth/{gateway}", method= RequestMethod.GET)
    public JsonResult refreshLoginPathAuth(@PathVariable("gateway") String gateway){
        boolean flag = false;
        String key = Const.GATEWAY_LOGIN_ACCESS_AUTH_KEY + gateway;
        stringRedisService.removeKey(key);
        List<AccessAuth> list = accessAuthService.getAllAccessAuth(gateway);
        for (AccessAuth accessAuth:list){
            String path = "/"+accessAuth.getServer()+accessAuth.getPath();
            stringRedisService.setForSet(key,path);
        }
        return ResultUtil.ok();
    }

}
