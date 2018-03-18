package com.lwx.usm.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 菜单编码（用于排序）
     */
    @Column(name = "MENU_CODE")
    private String menuCode;

    /**
     * 菜单名
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 菜单链接
     */
    @Column(name = "MENU_LINK")
    private String menuLink;

    /**
     * 菜单图标
     */
    @Column(name = "MENU_ICON")
    private String menuIcon;

    /**
     * 父级菜单ID
     */
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 是否父级菜单
     */
    @Column(name = "IS_PARENT")
    private String isParent;

    /**
     * 0 有效 1 无效
     */
    private String status;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return MENU_ID - 主键
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置主键
     *
     * @param menuId 主键
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 获取菜单编码（用于排序）
     *
     * @return MENU_CODE - 菜单编码（用于排序）
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编码（用于排序）
     *
     * @param menuCode 菜单编码（用于排序）
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    /**
     * 获取菜单名
     *
     * @return MENU_NAME - 菜单名
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名
     *
     * @param menuName 菜单名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单链接
     *
     * @return MENU_LINK - 菜单链接
     */
    public String getMenuLink() {
        return menuLink;
    }

    /**
     * 设置菜单链接
     *
     * @param menuLink 菜单链接
     */
    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink == null ? null : menuLink.trim();
    }

    /**
     * 获取菜单图标
     *
     * @return MENU_ICON - 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标
     *
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * 获取父级菜单ID
     *
     * @return PARENT_ID - 父级菜单ID
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

    /**
     * 获取是否父级菜单
     *
     * @return IS_PARENT - 是否父级菜单
     */
    public String getIsParent() {
        return isParent;
    }

    /**
     * 设置是否父级菜单
     *
     * @param isParent 是否父级菜单
     */
    public void setIsParent(String isParent) {
        this.isParent = isParent == null ? null : isParent.trim();
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