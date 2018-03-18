package com.lwx.usm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dictionary")
public class SysDictionary implements Serializable {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 字典类型
     */
    @Column(name = "dic_type")
    private String dicType;

    /**
     * 字典类型名称
     */
    @Column(name = "dic_type_name")
    private String dicTypeName;

    /**
     * 字典编码
     */
    @Column(name = "dic_code")
    private String dicCode;

    /**
     * 字典编码对应的值
     */
    @Column(name = "dic_value")
    private String dicValue;

    /**
     * 字典编码名称
     */
    @Column(name = "dic_code_name")
    private String dicCodeName;

    /**
     * 是否允许编辑 Y 允许 N 不允许
     */
    private String edit;

    /**
     * 排序编码
     */
    @Column(name = "sort_code")
    private String sortCode;

    /**
     * 0 有效 1 无效
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
     * 获取字典类型
     *
     * @return dic_type - 字典类型
     */
    public String getDicType() {
        return dicType;
    }

    /**
     * 设置字典类型
     *
     * @param dicType 字典类型
     */
    public void setDicType(String dicType) {
        this.dicType = dicType == null ? null : dicType.trim();
    }

    /**
     * 获取字典类型名称
     *
     * @return dic_type_name - 字典类型名称
     */
    public String getDicTypeName() {
        return dicTypeName;
    }

    /**
     * 设置字典类型名称
     *
     * @param dicTypeName 字典类型名称
     */
    public void setDicTypeName(String dicTypeName) {
        this.dicTypeName = dicTypeName == null ? null : dicTypeName.trim();
    }

    /**
     * 获取字典编码
     *
     * @return dic_code - 字典编码
     */
    public String getDicCode() {
        return dicCode;
    }

    /**
     * 设置字典编码
     *
     * @param dicCode 字典编码
     */
    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    /**
     * 获取字典编码对应的值
     *
     * @return dic_value - 字典编码对应的值
     */
    public String getDicValue() {
        return dicValue;
    }

    /**
     * 设置字典编码对应的值
     *
     * @param dicValue 字典编码对应的值
     */
    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }

    /**
     * 获取字典编码名称
     *
     * @return dic_code_name - 字典编码名称
     */
    public String getDicCodeName() {
        return dicCodeName;
    }

    /**
     * 设置字典编码名称
     *
     * @param dicCodeName 字典编码名称
     */
    public void setDicCodeName(String dicCodeName) {
        this.dicCodeName = dicCodeName == null ? null : dicCodeName.trim();
    }

    /**
     * 获取是否允许编辑 Y 允许 N 不允许
     *
     * @return edit - 是否允许编辑 Y 允许 N 不允许
     */
    public String getEdit() {
        return edit;
    }

    /**
     * 设置是否允许编辑 Y 允许 N 不允许
     *
     * @param edit 是否允许编辑 Y 允许 N 不允许
     */
    public void setEdit(String edit) {
        this.edit = edit == null ? null : edit.trim();
    }

    /**
     * 获取排序编码
     *
     * @return sort_code - 排序编码
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     * 设置排序编码
     *
     * @param sortCode 排序编码
     */
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode == null ? null : sortCode.trim();
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