package com.lwx.usm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "login_log")
public class LoginLog implements Serializable {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 远程IP
     */
    @Column(name = "remote_ip")
    private String remoteIp;

    /**
     * 本地IP
     */
    @Column(name = "local_ip")
    private String localIp;

    /**
     * 登陆用户名 
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * session_id
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 登陆时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 1 登陆 2登出
     */
    @Column(name = "login_type")
    private String loginType;

    /**
     * 登陆结果
     */
    @Column(name = "login_result")
    private String loginResult;

    /**
     * 1 OK 2 FAIL
     */
    @Column(name = "login_status")
    private String loginStatus;

    /**
     * 创建人帐号
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 更新人帐号
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改日期
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取远程IP
     *
     * @return remote_ip - 远程IP
     */
    public String getRemoteIp() {
        return remoteIp;
    }

    /**
     * 设置远程IP
     *
     * @param remoteIp 远程IP
     */
    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp == null ? null : remoteIp.trim();
    }

    /**
     * 获取本地IP
     *
     * @return local_ip - 本地IP
     */
    public String getLocalIp() {
        return localIp;
    }

    /**
     * 设置本地IP
     *
     * @param localIp 本地IP
     */
    public void setLocalIp(String localIp) {
        this.localIp = localIp == null ? null : localIp.trim();
    }

    /**
     * 获取登陆用户名 
     *
     * @return login_name - 登陆用户名 
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登陆用户名 
     *
     * @param loginName 登陆用户名 
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取session_id
     *
     * @return session_id - session_id
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 设置session_id
     *
     * @param sessionId session_id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * 获取登陆时间
     *
     * @return login_time - 登陆时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登陆时间
     *
     * @param loginTime 登陆时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取1 登陆 2登出
     *
     * @return login_type - 1 登陆 2登出
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * 设置1 登陆 2登出
     *
     * @param loginType 1 登陆 2登出
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType == null ? null : loginType.trim();
    }

    /**
     * 获取登陆结果
     *
     * @return login_result - 登陆结果
     */
    public String getLoginResult() {
        return loginResult;
    }

    /**
     * 设置登陆结果
     *
     * @param loginResult 登陆结果
     */
    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult == null ? null : loginResult.trim();
    }

    /**
     * 获取1 OK 2 FAIL
     *
     * @return login_status - 1 OK 2 FAIL
     */
    public String getLoginStatus() {
        return loginStatus;
    }

    /**
     * 设置1 OK 2 FAIL
     *
     * @param loginStatus 1 OK 2 FAIL
     */
    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus == null ? null : loginStatus.trim();
    }

    /**
     * 获取创建人帐号
     *
     * @return create_user - 创建人帐号
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人帐号
     *
     * @param createUser 创建人帐号
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取更新人帐号
     *
     * @return update_user - 更新人帐号
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人帐号
     *
     * @param updateUser 更新人帐号
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改日期
     *
     * @return update_time - 修改日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改日期
     *
     * @param updateTime 修改日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}