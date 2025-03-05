package com.example.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门信息表
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Data
public class Departments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId(value = "department_id", type = IdType.AUTO)
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 经理ID
     */
    private Integer managerId;

    /**
     * 位置
     */
    private String location;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Departments{" +
            "departmentId=" + departmentId +
            ", departmentName=" + departmentName +
            ", managerId=" + managerId +
            ", location=" + location +
            ", createdAt=" + createdAt +
        "}";
    }
}
