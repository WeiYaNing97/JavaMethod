package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色与权限关联表
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@TableName("role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer permissionId;

    /**
     * 授权时间
     */
    private LocalDateTime grantedAt;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    public LocalDateTime getGrantedAt() {
        return grantedAt;
    }

    public void setGrantedAt(LocalDateTime grantedAt) {
        this.grantedAt = grantedAt;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
            "roleId=" + roleId +
            ", permissionId=" + permissionId +
            ", grantedAt=" + grantedAt +
        "}";
    }
}
