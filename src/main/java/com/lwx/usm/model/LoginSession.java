package com.lwx.usm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "login_session")
public class LoginSession implements Serializable {
    /**
     * session_id
     */
    @Id
    @Column(name = "session_id")
    private String sessionId;

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
     * 登陆时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * session占用空间大小
     */
    @Column(name = "session_size")
    private String sessionSize;

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
     * 获取session占用空间大小
     *
     * @return session_size - session占用空间大小
     */
    public String getSessionSize() {
        return sessionSize;
    }

    /**
     * 设置session占用空间大小
     *
     * @param sessionSize session占用空间大小
     */
    public void setSessionSize(String sessionSize) {
        this.sessionSize = sessionSize == null ? null : sessionSize.trim();
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