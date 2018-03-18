package com.lwx.usm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户帐号
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 电话
     */
    @Column(name = "user_mobile")
    private String userMobile;

    /**
     * 邮箱
     */
    @Column(name = "user_mail")
    private String userMail;

    /**
     * 证件号码
     */
    @Column(name = "cert_no")
    private String certNo;

    /**
     * 公司;后期可能更改
     */
    private String company;

    /**
     * 地址
     */
    private String address;

    /**
     * M男 F女
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 类型 1 超级用户 2 普通用户
     */
    private String type;

    /**
     * 1 可用 2 冻结（初始化状态 1 可用）
     */
    private String status;

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

    /**
     * 扩展字段1
     */
    private String extfld1;

    /**
     * 扩展字段2
     */
    private String extfld2;

    /**
     * 扩展字段3
     */
    private String extfld3;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return user_id - 主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置主键
     *
     * @param userId 主键
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用户帐号
     *
     * @return login_name - 用户帐号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置用户帐号
     *
     * @param loginName 用户帐号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取电话
     *
     * @return user_mobile - 电话
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * 设置电话
     *
     * @param userMobile 电话
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     * 获取邮箱
     *
     * @return user_mail - 邮箱
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * 设置邮箱
     *
     * @param userMail 邮箱
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    /**
     * 获取证件号码
     *
     * @return cert_no - 证件号码
     */
    public String getCertNo() {
        return certNo;
    }

    /**
     * 设置证件号码
     *
     * @param certNo 证件号码
     */
    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    /**
     * 获取公司;后期可能更改
     *
     * @return company - 公司;后期可能更改
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置公司;后期可能更改
     *
     * @param company 公司;后期可能更改
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取M男 F女
     *
     * @return sex - M男 F女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置M男 F女
     *
     * @param sex M男 F女
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取类型 1 超级用户 2 普通用户
     *
     * @return type - 类型 1 超级用户 2 普通用户
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型 1 超级用户 2 普通用户
     *
     * @param type 类型 1 超级用户 2 普通用户
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取1 可用 2 冻结（初始化状态 1 可用）
     *
     * @return status - 1 可用 2 冻结（初始化状态 1 可用）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置1 可用 2 冻结（初始化状态 1 可用）
     *
     * @param status 1 可用 2 冻结（初始化状态 1 可用）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    /**
     * 获取扩展字段1
     *
     * @return extfld1 - 扩展字段1
     */
    public String getExtfld1() {
        return extfld1;
    }

    /**
     * 设置扩展字段1
     *
     * @param extfld1 扩展字段1
     */
    public void setExtfld1(String extfld1) {
        this.extfld1 = extfld1 == null ? null : extfld1.trim();
    }

    /**
     * 获取扩展字段2
     *
     * @return extfld2 - 扩展字段2
     */
    public String getExtfld2() {
        return extfld2;
    }

    /**
     * 设置扩展字段2
     *
     * @param extfld2 扩展字段2
     */
    public void setExtfld2(String extfld2) {
        this.extfld2 = extfld2 == null ? null : extfld2.trim();
    }

    /**
     * 获取扩展字段3
     *
     * @return extfld3 - 扩展字段3
     */
    public String getExtfld3() {
        return extfld3;
    }

    /**
     * 设置扩展字段3
     *
     * @param extfld3 扩展字段3
     */
    public void setExtfld3(String extfld3) {
        this.extfld3 = extfld3 == null ? null : extfld3.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}