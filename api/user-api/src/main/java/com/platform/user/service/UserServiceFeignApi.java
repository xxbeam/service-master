package com.platform.user.service;

import com.platform.config.FeignSupportConfig;
import com.platform.dto.user.UserDTO;
import com.platform.user.hystrix.UserServiceFeignHystrix;
import com.platform.vo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user" ,configuration = FeignSupportConfig.class)
public interface UserServiceFeignApi {

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public JsonResult login(@RequestBody  UserDTO userDTO);

    @RequestMapping(value="/user/getUserById/{userId}", method=RequestMethod.GET)
    public JsonResult getUserById(@PathVariable("userId") Integer userId);

}
