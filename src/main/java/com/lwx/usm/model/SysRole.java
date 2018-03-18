package com.lwx.usm.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 角色编号
     */
    @Column(name = "ROLE_NO")
    private String roleNo;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "ROLE_DESC")
    private String roleDesc;

    /**
     * 类型（1：超级管理员；2：访客；3：系统新增）
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 0 NO 1 YES
     */
    private String status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return ROLE_ID - 主键
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置主键
     *
     * @param roleId 主键
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 获取角色编号
     *
     * @return ROLE_NO - 角色编号
     */
    public String getRoleNo() {
        return roleNo;
    }

    /**
     * 设置角色编号
     *
     * @param roleNo 角色编号
     */
    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo == null ? null : roleNo.trim();
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取角色描述
     *
     * @return ROLE_DESC - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * 获取类型（1：超级管理员；2：访客；3：系统新增）
     *
     * @return TYPE - 类型（1：超级管理员；2：访客；3：系统新增）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型（1：超级管理员；2：访客；3：系统新增）
     *
     * @param type 类型（1：超级管理员；2：访客；3：系统新增）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取0 NO 1 YES
     *
     * @return status - 0 NO 1 YES
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0 NO 1 YES
     *
     * @param status 0 NO 1 YES
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}