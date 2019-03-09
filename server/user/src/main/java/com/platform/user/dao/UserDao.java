package com.platform.user.dao;

import com.platform.user.bo.User;

public interface UserDao {

    public User getById(Integer id);

    public User getByLoginName(String loginName);

}
