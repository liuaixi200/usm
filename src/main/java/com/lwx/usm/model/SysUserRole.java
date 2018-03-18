package com.lwx.usm.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 0 有效 1 无效
     */
    private String status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取角色ID
     *
     * @return ROLE_ID - 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 获取0 有效 1 无效
     *
     * @return status - 0 有效 1 无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0 有效 1 无效
     *
     * @param status 0 有效 1 无效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}