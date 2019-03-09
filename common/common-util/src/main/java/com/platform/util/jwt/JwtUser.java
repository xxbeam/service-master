package com.platform.util.jwt;

import java.util.Map;

public interface JwtUser {

    /**
     * 将需要签名的数据转换成map对象
     * @return
     */
    public Map<String, Object> praseToJwtMap();

}
