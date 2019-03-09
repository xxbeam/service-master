package com.platform.sso.service.impl;

import com.platform.base.BaseConst;
import com.platform.sso.bo.AccessAuth;
import com.platform.sso.bo.AccessAuthExample;
import com.platform.sso.dao.AccessAuthMapper;
import com.platform.sso.service.AccessAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessAuthServiceImpl implements AccessAuthService {

    @Autowired
    AccessAuthMapper accessAuthMapper;


    @Override
    public List<AccessAuth> getAllAccessAuth(String gateway) {
        AccessAuthExample accessAuthExample = new AccessAuthExample();
        AccessAuthExample.Criteria criteria = accessAuthExample.createCriteria();
        criteria.andGatewayEqualTo(gateway);
        criteria.andStatusEqualTo(BaseConst.STATUS_EFFECTIVE);
        List<AccessAuth> list = accessAuthMapper.selectByExample(accessAuthExample);
        return list;
    }

}
