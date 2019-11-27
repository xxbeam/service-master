package com.platform.user.service;


import com.platform.dto.user.UserDTO;
import com.platform.vo.user.UserVo;

public interface UserService {


    public UserVo getUserById(Integer userId) throws Exception;

    public UserVo login(UserDTO userDTO) throws Exception;

    public void newtest();

    public void newtest2();

}
