package com.platform.sso.service;

import com.platform.config.FeignSupportConfig;
import com.platform.dto.accessAuth.AccessAuthDTO;
import com.platform.vo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "sso" ,configuration = FeignSupportConfig.class)
public interface AccessAuthServiceFeignApi {

    @RequestMapping(value="/access/checkLoginPathAuth", method= RequestMethod.POST)
    public JsonResult checkLoginPathAuth(@RequestBody AccessAuthDTO accessAuthDTO);

}
