package com.example.user.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetails {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码哈希值
     */
    private String passwordHash;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 名字
     */
    private String firstName;

    /**
     * 姓氏
     */
    private String lastName;

    /**
     * 出生日期
     */
    private LocalDate dateOfBirth;

    /**
     * 性别(M-男性, F-女性, O-其他)
     */
    private String gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 城市
     */
    private String city;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 国家
     */
    private String country;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescription;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限描述
     */
    private String permissionDescription;
}
