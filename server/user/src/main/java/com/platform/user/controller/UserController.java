package com.platform.user.controller;

import com.platform.base.BaseController;
import com.platform.dto.user.UserDTO;
import com.platform.user.service.UserService;
import com.platform.util.ResultUtil;
import com.platform.vo.JsonResult;
import com.platform.vo.user.UserVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @ApiOperation(value="根据用户id获取用户信息", notes="根据用户id获取用户信息")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path", dataType = "int")
    @RequestMapping(value="/getUserById/{userId}", method=RequestMethod.GET)
    public JsonResult getUserById(@PathVariable("userId")  Integer userId) throws Exception {
        UserVo userVo = userService.getUserById(userId);
        return ResultUtil.ok(userVo);
    }

    @ApiOperation(value="登陆", notes="根据账号密码获取用户信息")
    @ApiImplicitParam(name = "userDTO", value = "用户传输实体类", required = true, dataType = "UserDTO")
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public JsonResult login(@RequestBody @Valid UserDTO userDTO) throws Exception {
        UserVo userVo = userService.login(userDTO);
        return ResultUtil.ok(userVo);
    }

}
