package com.platform.sso.hystrix;

import com.platform.base.RetCode;
import com.platform.dto.accessAuth.AccessAuthDTO;
import com.platform.sso.service.AccessAuthServiceFeignApi;
import com.platform.util.ResultUtil;
import com.platform.vo.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class AccessAuthServiceFeignHystrix implements AccessAuthServiceFeignApi {

    @Override
    public JsonResult checkLoginPathAuth(AccessAuthDTO accessAuthDTO) {
        return ResultUtil.error(RetCode.SERVICE_BROKEN);
    }
}
