/*
 * 文 件 名:  CloudMenuService.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  Leon
 * 修改时间:  2015年7月14日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.service;

import com.lwx.usm.dto.Node;
import com.lwx.usm.model.SysMenu;
import com.lwx.usm.model.SysRoleMenu;

import java.util.List;

/**
 * <功能详细描述>
 *
 * @author leon
 * @version [版本号, 2015年7月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SysMenuService {

	public int update(SysMenu info);

	public SysMenu findByPrimaryKey(String menuId);

	public List<SysMenu> queryList(SysMenu info);

	public List<Node> queryTree(String... parentIds);

	public List<SysMenu> queryRoleMenu(String roleId);

	public List<Node> queryRoleMenuTree(String roleId, String userId, List<String> btnAuths);

	public List<SysMenu> queryUserMenu(String userId);

	public Node queryUserMenuTree(String userId);

	public int disableMenu(String[] menuIds);

	public int enableMenu(String[] menuIds);

	public int saveRoloMenu(SysRoleMenu[] infoList);


}
