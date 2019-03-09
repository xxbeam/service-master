package com.platform.sso.service;

import com.platform.sso.bo.AccessAuth;

import java.util.List;

public interface AccessAuthService {

    public List<AccessAuth> getAllAccessAuth(String gateway);
}
