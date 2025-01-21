package com.example.userLogin.controller;

import com.example.method.login.LoginUser;
import com.example.userLogin.domain.TUser;
import com.example.userLogin.service.ITUserService;
import com.example.utils.util.EncryptionAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 用户登录信息表Controller
 * 
 * @author ruoyi
 * @date 2024-06-16
 */
@RestController
@RequestMapping("/tuser")
public class TUserController
{
    @Autowired
    private ITUserService tUserService;

    /**
     * 新增用户登录信息表
     */
    @PostMapping("/add")
    public boolean add(String name,String loginName,String passWord,String phone)/*@RequestBody TUser tUser*/
    {
        // 创建一个新的TUser对象
        TUser tUser = new TUser();
        // 设置用户名
        tUser.setUserName(name);
        // 设置登录名
        tUser.setLoginName(loginName);
        // 设置手机号码
        tUser.setPhone(phone);

        try {
            // 对密码进行加密处理
            tUser.setUserPassword(EncryptionAlgorithm.PasswordVerification(passWord));
        } catch (NoSuchAlgorithmException e) {
            // 如果加密过程中出现异常，则抛出运行时异常
            throw new RuntimeException(e);
        }

        // 调用tUserService的insertTUser方法插入用户信息，并返回插入结果
        int i = tUserService.insertTUser(tUser);

        // 如果插入结果大于0，表示插入成功，返回true
        if (i>0){
            return true;
        }

        // 否则，返回false
        return false;
    }
    /**
     * 登录
     */
    @GetMapping("/login")
    public boolean Login(String loginName, String userPassword, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        // 根据登录名查询用户信息
        TUser tUser = tUserService.selectTUserByLoginName(loginName);

        // 如果用户信息为空，则返回false
        if (tUser==null){
            return false;
        }

        // 如果用户信息不为空且用户密码匹配加密后的密码
        if (tUser!=null && tUser.getUserPassword().equals(EncryptionAlgorithm.PasswordVerification(userPassword)) ){
            // 创建一个LoginUser对象，并将tUser的属性复制到loginUser中
            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(tUser, loginUser);

            // 更新用户的最后登录时间
            tUser.setLastLoginTime(new Date());

            // 更新用户信息
            int i = tUserService.updateTUser(tUser);

            // 如果更新成功，则返回true
            if (i>0){
                return true;
            }else {
                // 如果更新失败，则返回false
                return false;
            }
        }

        // 如果用户密码不匹配，则返回false
        return false;
    }

}
