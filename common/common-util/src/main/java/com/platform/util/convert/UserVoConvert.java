package com.platform.util.convert;

import com.platform.util.MobileUtil;
import com.platform.vo.user.UserVo;

public class UserVoConvert {

    public static UserVo convert(UserVo userVo){
        userVo.setMobile(MobileUtil.hideMobile(userVo.getMobile()));
        return userVo;
    }
}
