package com.example.login.controller;

import com.example.login.entity.UserLogin;
import com.example.login.token.TokenUtils;
import com.example.method.log.LogUtils;
import com.example.method.result.AjaxResult;
import com.example.user.entity.Users;
import com.example.user.service.IUsersService;
import com.example.utils.util.PasswordUtil;
import io.jsonwebtoken.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user/login")
@Api(tags = "用户登录")
public class UserLoginController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private PasswordUtil passwordUtil;
    @Autowired
    LogUtils logUtils;

    @ApiOperation(value = "用户登录")
    @GetMapping("/login")
    public AjaxResult login(@RequestBody UserLogin userLogin){
        //token为空则返回失败，否则返回成功
        Users users = usersService.getByUsername(userLogin.getUsername());
        if (users == null){
            return AjaxResult.error("请注册用户信息");
        }
        try {
            String hashPassword = passwordUtil.encryptedHashPassword(userLogin.getPassword());
            System.out.println(hashPassword);
            boolean resolveHashPassword = passwordUtil.resolveHashPassword(userLogin.getPassword(), users.getPasswordHash());
            if (resolveHashPassword){
                String token = TokenUtils.genToken(users);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Claims claims = null;
                try {
                    claims = TokenUtils.verifyToken(token);
                } catch (Exception e) {
                    logUtils.setExceptionInLog(e);
                    return AjaxResult.success("token验证失败");
                }
                if (claims != null){
                    return AjaxResult.success("登录成功");
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("登录失败");
    }
}
