package com.lwx.usm.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_button")
public class SysButton implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "button_id")
    private String buttonId;

    /**
     * 按钮编码 规则:菜单编码_铵钮中文标识
     */
    @Column(name = "button_code")
    private String buttonCode;

    /**
     * 按钮名
     */
    @Column(name = "button_name")
    private String buttonName;

    /**
     * 按钮图标
     */
    @Column(name = "button_icon")
    private String buttonIcon;

    /**
     * 0 有效 1 无效
     */
    private String status;

    /**
     * 父级菜单ID
     */
    @Column(name = "parent_id")
    private String parentId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return button_id - 主键
     */
    public String getButtonId() {
        return buttonId;
    }

    /**
     * 设置主键
     *
     * @param buttonId 主键
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId == null ? null : buttonId.trim();
    }

    /**
     * 获取按钮编码 规则:菜单编码_铵钮中文标识
     *
     * @return button_code - 按钮编码 规则:菜单编码_铵钮中文标识
     */
    public String getButtonCode() {
        return buttonCode;
    }

    /**
     * 设置按钮编码 规则:菜单编码_铵钮中文标识
     *
     * @param buttonCode 按钮编码 规则:菜单编码_铵钮中文标识
     */
    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode == null ? null : buttonCode.trim();
    }

    /**
     * 获取按钮名
     *
     * @return button_name - 按钮名
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * 设置按钮名
     *
     * @param buttonName 按钮名
     */
    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    /**
     * 获取按钮图标
     *
     * @return button_icon - 按钮图标
     */
    public String getButtonIcon() {
        return buttonIcon;
    }

    /**
     * 设置按钮图标
     *
     * @param buttonIcon 按钮图标
     */
    public void setButtonIcon(String buttonIcon) {
        this.buttonIcon = buttonIcon == null ? null : buttonIcon.trim();
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
     * 获取父级菜单ID
     *
     * @return parent_id - 父级菜单ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级菜单ID
     *
     * @param parentId 父级菜单ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
}