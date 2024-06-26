package com.example.userLogin.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户登录信息表对象 t_user
 * 
 * @author ruoyi
 * @date 2024-06-16
 */
public class TUser
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 用户名 */
    private String userName;

    /** 登录用户名 */
    private String loginName;

    /** 用户密码 */
    private String userPassword;

    /** 最后登录时间 */
    private Date lastLoginTime;

    /** 删除标记(0正常 1删除) */
    private Long isDeleted;

    /** 密码更改时间 */
    private Date pwdValTime;

    /** 数据归属码 */
    private String belongCode;

    /** 数据归属名称 */
    private String belongName;

    /** 数据类型(预留) */
    private String dataType;

    /** 手机号 */
    private String phone;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setUserPassword(String userPassword) 
    {
        this.userPassword = userPassword;
    }

    public String getUserPassword() 
    {
        return userPassword;
    }
    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setPwdValTime(Date pwdValTime) 
    {
        this.pwdValTime = pwdValTime;
    }

    public Date getPwdValTime() 
    {
        return pwdValTime;
    }
    public void setBelongCode(String belongCode) 
    {
        this.belongCode = belongCode;
    }

    public String getBelongCode() 
    {
        return belongCode;
    }
    public void setBelongName(String belongName) 
    {
        this.belongName = belongName;
    }

    public String getBelongName() 
    {
        return belongName;
    }
    public void setDataType(String dataType) 
    {
        this.dataType = dataType;
    }

    public String getDataType() 
    {
        return dataType;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("loginName", getLoginName())
            .append("userPassword", getUserPassword())
            .append("lastLoginTime", getLastLoginTime())
            .append("isDeleted", getIsDeleted())
            .append("pwdValTime", getPwdValTime())
            .append("belongCode", getBelongCode())
            .append("belongName", getBelongName())
            .append("dataType", getDataType())
            .append("phone", getPhone())
            .toString();
    }
}
