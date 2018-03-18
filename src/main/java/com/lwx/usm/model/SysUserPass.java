package com.lwx.usm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user_pass")
public class SysUserPass implements Serializable {
    /**
     * 用户名
     */
    @Id
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 强制修改密码标识
     */
    @Column(name = "update_pass_flag")
    private String updatePassFlag;

    /**
     * 登陆次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * 锁定标识
     */
    @Column(name = "lock_flag")
    private String lockFlag;

    /**
     * 锁定时间
     */
    @Column(name = "lock_time")
    private Date lockTime;

    /**
     * 当前错误次数
     */
    private Integer errors;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户名
     *
     * @return login_name - 用户名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置用户名
     *
     * @param loginName 用户名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取强制修改密码标识
     *
     * @return update_pass_flag - 强制修改密码标识
     */
    public String getUpdatePassFlag() {
        return updatePassFlag;
    }

    /**
     * 设置强制修改密码标识
     *
     * @param updatePassFlag 强制修改密码标识
     */
    public void setUpdatePassFlag(String updatePassFlag) {
        this.updatePassFlag = updatePassFlag == null ? null : updatePassFlag.trim();
    }

    /**
     * 获取登陆次数
     *
     * @return login_count - 登陆次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登陆次数
     *
     * @param loginCount 登陆次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * 获取锁定标识
     *
     * @return lock_flag - 锁定标识
     */
    public String getLockFlag() {
        return lockFlag;
    }

    /**
     * 设置锁定标识
     *
     * @param lockFlag 锁定标识
     */
    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag == null ? null : lockFlag.trim();
    }

    /**
     * 获取锁定时间
     *
     * @return lock_time - 锁定时间
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * 设置锁定时间
     *
     * @param lockTime 锁定时间
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 获取当前错误次数
     *
     * @return errors - 当前错误次数
     */
    public Integer getErrors() {
        return errors;
    }

    /**
     * 设置当前错误次数
     *
     * @param errors 当前错误次数
     */
    public void setErrors(Integer errors) {
        this.errors = errors;
    }
}