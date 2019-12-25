package com.platform.user.service.impl;

import com.platform.dto.user.UserDTO;
import com.platform.exception.ResponseExpection;
import com.platform.user.bo.User;
import com.platform.user.dao.UserDao;
import com.platform.user.service.UserService;
import com.platform.util.CopyBeans;
import com.platform.util.MD5Security;
import com.platform.util.convert.UserVoConvert;
import com.platform.vo.user.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserVo getUserById(Integer userId) throws Exception{
        if(userId==12){
            System.out.println(111);
            System.out.println(111);
            System.out.println(111);
            System.out.println(111);
        }
        if(userId ==1){
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
            System.out.println(222);
        }
        if(userId==1){
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
            System.out.println(333);
        }
        if(userId==10){
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
            System.out.println(4444);
        }
        if(true){
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
        }
        User user = userDao.getById(userId);
        if(user==null){
            throw new ResponseExpection("用户不存在");
        }
        UserVo userVo = new UserVo();
        CopyBeans.copy(userVo, user);
        userVo = UserVoConvert.convert(userVo);
        return userVo;
    }

    @Override
    public UserVo login(UserDTO userDTO) throws Exception {
        User user = userDao.getByLoginName(userDTO.getLoginName());
        if(user==null){
            throw new ResponseExpection("用户名不存在");
        }
        String signPwd = MD5Security.encrypt(userDTO.getPassword());
        if(!user.getPassword().equals(signPwd)){
            throw new ResponseExpection("用户密码不正确");
        }
        UserVo userVo = new UserVo();
        CopyBeans.copy(userVo, user);
        userVo = UserVoConvert.convert(userVo);
        return userVo;
    }

    @Override
    public void newtest() {
        User user = new  User();
        UserVo userVo = new UserVo();
        CopyBeans.copy(userVo, user);
        userVo = UserVoConvert.convert(userVo);
    }
//
//    @Override
//    public void newtest2() {
//        User user = new  User();
//        UserVo userVo = new UserVo();
//        CopyBeans.copy(userVo, user);
//        userVo = UserVoConvert.convert(userVo);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//        System.out.println(111);
//    }
}
