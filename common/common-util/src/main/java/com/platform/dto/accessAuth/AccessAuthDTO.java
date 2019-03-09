package com.platform.dto.accessAuth;

import javax.validation.constraints.NotEmpty;

public class AccessAuthDTO {
    @NotEmpty(message="访问路径为空")
    private String path;
    @NotEmpty(message="网关名称为空")
    private String gateway;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
}
