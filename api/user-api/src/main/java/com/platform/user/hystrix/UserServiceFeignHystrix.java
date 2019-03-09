package com.platform.user.hystrix;

import com.platform.base.RetCode;
import com.platform.dto.user.UserDTO;
import com.platform.user.service.UserServiceFeignApi;
import com.platform.util.ResultUtil;
import com.platform.vo.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFeignHystrix implements UserServiceFeignApi {
    @Override
    public JsonResult login(UserDTO userDTO) {
        return ResultUtil.error(RetCode.SERVICE_BROKEN);
    }

    @Override
    public JsonResult getUserById(Integer userId) {
        return ResultUtil.error(RetCode.SERVICE_BROKEN);
    }
}
