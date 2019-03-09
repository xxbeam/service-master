package com.platform.dto.user;

import javax.validation.constraints.NotEmpty;

public class UserTokenDTO {

    @NotEmpty(message="token不能为空")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
