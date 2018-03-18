/*
 * 文 件 名:  CloudRoleService.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  Leon
 * 修改时间:  2015年7月14日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.service;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.model.SysRole;
import com.lwx.usm.model.SysUserRole;

import java.util.List;

/**
 * <功能详细描述>
 *
 * @author leon
 * @version [版本号, 2015年7月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SysRoleService {

	public int save(SysRole info);


	public int update(SysRole info);

	public int delete(String roleId);

	public SysRole findByPrimaryKey(String menuId);

	public Page<SysRole> queryList(SysRole info, SimplePage sp);

	public int disableRole(String[] ids);

	public int enableRole(String[] ids);

	public List<SysRole> queryUserRole(String userId);


	public int saveUserRole(List<SysUserRole> infoList);

}
