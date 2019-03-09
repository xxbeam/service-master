package com.platform.dto.user;

import javax.validation.constraints.NotEmpty;

public class UserDTO {

    @NotEmpty(message="用户名不能为空")
    private String loginName;
    @NotEmpty(message="密码不能为空")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
