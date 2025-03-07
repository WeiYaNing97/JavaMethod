package com.example.login.controller;

import com.example.login.entity.UserLogin;
import com.example.login.token.TokenUtils;
import com.example.method.log.LogUtils;
import com.example.method.result.AjaxResult;
import com.example.user.entity.Users;
import com.example.user.service.IUsersService;
import com.example.utils.util.PasswordUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user/login")
@Api(tags = "用户登录")
public class UserLoginController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private PasswordUtil passwordUtil;
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

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Claims claims = TokenUtils.verifyToken(token);
                    if (claims != null){
                        return AjaxResult.success("登录成功");
                    }
                } catch (ExpiredJwtException e) {
                    return AjaxResult.error("token超时,验证失败");
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error("登录失败");
    }
}
