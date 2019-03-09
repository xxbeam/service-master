package com.platform.sso.hystrix;

import com.platform.base.RetCode;
import com.platform.dto.user.UserTokenDTO;
import com.platform.sso.service.SsoUserServiceFeignApi;
import com.platform.util.ResultUtil;
import com.platform.vo.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class SsoUserServiceFeignHystrix implements SsoUserServiceFeignApi {

    @Override
    public JsonResult checkLogin(UserTokenDTO userTokenDTO) {
        return ResultUtil.error(RetCode.SERVICE_BROKEN);
    }
}
