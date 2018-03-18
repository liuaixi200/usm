package com.lwx.usm.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_user_data_auth")
public class SysUserDataAuth implements Serializable {
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
     * 权限类型
     */
    @Column(name = "auth_type")
    private String authType;

    /**
     * 权限类型名称
     */
    @Column(name = "auth_type_name")
    private String authTypeName;

    /**
     * 权限编码,存组织ID:org_id
     */
    @Column(name = "auth_code")
    private String authCode;

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
     * 获取权限类型
     *
     * @return auth_type - 权限类型
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * 设置权限类型
     *
     * @param authType 权限类型
     */
    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    /**
     * 获取权限类型名称
     *
     * @return auth_type_name - 权限类型名称
     */
    public String getAuthTypeName() {
        return authTypeName;
    }

    /**
     * 设置权限类型名称
     *
     * @param authTypeName 权限类型名称
     */
    public void setAuthTypeName(String authTypeName) {
        this.authTypeName = authTypeName == null ? null : authTypeName.trim();
    }

    /**
     * 获取权限编码,存组织ID:org_id
     *
     * @return auth_code - 权限编码,存组织ID:org_id
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * 设置权限编码,存组织ID:org_id
     *
     * @param authCode 权限编码,存组织ID:org_id
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }
}