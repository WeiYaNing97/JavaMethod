package com.example.instance.stream;

import lombok.Data;

@Data
public class StreamEntity {
    /** 主键ID */
    private Integer id;

    /** 用户名 */
    private String userName;

    /** 用户密码 */
    private Integer userPassword;

    /** 用户性别 */
    private String match;

    StreamEntity(Integer id, String userName, Integer userPassword, String match) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.match = match;
    }

}
