package com.lwx.usm.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 菜单类型 1 菜单 2按钮
     */
    @Column(name = "MENU_TYPE")
    private String menuType;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键 自增
     *
     * @return id - 主键 自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键 自增
     *
     * @param id 主键 自增
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取菜单ID
     *
     * @return MENU_ID - 菜单ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单ID
     *
     * @param menuId 菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 获取菜单类型 1 菜单 2按钮
     *
     * @return MENU_TYPE - 菜单类型 1 菜单 2按钮
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型 1 菜单 2按钮
     *
     * @param menuType 菜单类型 1 菜单 2按钮
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }
}