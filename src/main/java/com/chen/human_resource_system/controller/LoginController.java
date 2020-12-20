package com.chen.human_resource_system.controller;

import com.chen.human_resource_system.jwt.JWTToken;
import com.chen.human_resource_system.jwt.JWTUtil;
import com.chen.human_resource_system.pojo.User;
import com.chen.human_resource_system.service.UserService;
import com.chen.human_resource_system.util.JsonResult;
import com.chen.human_resource_system.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-10 21:22
 **/
@Api(tags = "登录接口")
@RestController
@CrossOrigin
public class LoginController {
    @Resource
    UserService userService;


    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("login")
    public JsonResult userLogin(String username,String password) {
        String passwords = MD5Util.getHexPassword(password);
        JWTToken token = new JWTToken(JWTUtil.sign(username, passwords));
        Subject subject = SecurityUtils.getSubject();
        //登录认证
        subject.login(token);

        User user=userService.findUserByUserName(username);
        List<String> roles =userService.getUserRole(user.getUserId());
        return JsonResult.success().addObject("token", token.getPrincipal())
                .addObject("roles", roles);

    }

    @ApiOperation(value = "用户退出")
    @PostMapping("logout")
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonResult.success();
    }

    @GetMapping("/error/{message}")
    public JsonResult error(@PathVariable String message) {
        return JsonResult.errorOf(200, message);
    }
}
