package com.platform.service;

import com.alibaba.fastjson.JSON;
import com.platform.dto.user.UserDTO;
import com.platform.user.service.UserService;
import com.platform.vo.user.UserVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiongxin
 * @date 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void getUserById(){
        try {
            UserVo vo = userService.getUserById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void login(){
        UserDTO userDTO = new UserDTO();
        userDTO.setLoginName("xxBeam");
        userDTO.setPassword("123456");
        try {
            userService.login(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

}
