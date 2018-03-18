/*
 * 文 件 名:  CloudRoleService.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  Leon
 * 修改时间:  2015年7月14日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.enums.EnumBoolean;
import com.lwx.usm.mapper.SysRoleMapper;
import com.lwx.usm.mapper.SysUserRoleMapper;
import com.lwx.usm.model.SysRole;
import com.lwx.usm.model.SysUserRole;
import com.lwx.usm.service.SysRoleService;
import com.lwx.usm.utils.BaseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.Arrays;
import java.util.List;

/**
 * <功能详细描述>
 *
 * @author leon
 * @version [版本号, 2015年7月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("CloudRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	/**
	 * <添加菜单详细信息>
	 *
	 * @see [类、类#方法、类#成员]
	 */
	public int save(SysRole info) {
		info.setRoleId(BaseUtil.getUUID());
		int iReturn = sysRoleMapper.insertSelective(info);
		return iReturn;
	}

	/**
	 * <修改菜单信息>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int update(SysRole info) {
		int iReturn = sysRoleMapper.updateByPrimaryKey(info);
		return iReturn;
	}

	/**
	 * <删除菜单信息>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int delete(String roleId) {
		int iReturn = sysRoleMapper.deleteByPrimaryKey(roleId);
		return iReturn;
	}

	/**
	 * <根据主键查询>
	 *
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public SysRole findByPrimaryKey(String roleId) {
		SysRole info = sysRoleMapper.selectByPrimaryKey(roleId);
		return info;
	}


	/**
	 * <查询role列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Page<SysRole> queryList(SysRole info, SimplePage sp) {
		Page<SysRole> page = PageHelper.startPage(sp.getPageNo(), sp.getPageSize());
		sysRoleMapper.select(info);

		return page;
	}

	/**
	 * <查询role列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int disableRole(String[] ids) {
		int iRtn = 0;
		Example example = new Example.Builder(SysRole.class).where(WeekendSqls.<SysRole>custom()
				.andIn(SysRole::getRoleId,Arrays.asList(ids))).build();
		SysRole info = new SysRole();
		info.setStatus(EnumBoolean.NO.getCode());
		sysRoleMapper.updateByExample(info,example);
		return iRtn;
	}

	/**
	 * <查询role列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int enableRole(String[] ids) {
		int iRtn = 0;
		Example example = new Example.Builder(SysRole.class).where(WeekendSqls.<SysRole>custom()
				.andIn(SysRole::getRoleId,Arrays.asList(ids))).build();
		SysRole info = new SysRole();
		info.setStatus(EnumBoolean.YES.getCode());
		sysRoleMapper.updateByExample(info,example);
		return iRtn;
	}


	/**
	 * <查询role列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<SysRole> queryUserRole(String userId) {
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(userId);
		List<SysUserRole> list = sysUserRoleMapper.select(sysUserRole);
		Example.Builder builder = new Example.Builder(SysRole.class);
		if(list != null && list.size() > 0){
			builder.where(WeekendSqls.<SysRole>custom()
					.andIn(SysRole::getRoleId,list));
		}

		List<SysRole> retList = sysRoleMapper.selectByExample(builder.build());

		return retList;
	}


	/**
	 * <保存用户的角色列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int saveUserRole(List<SysUserRole> infoList) {
		int iRtn = infoList != null ? infoList.size() : 0;

		//先批量删除
		if (infoList != null && infoList.size() > 0) {
			String userId = infoList.get(0).getUserId();
			Example example = new Example.Builder(SysUserRole.class).where(WeekendSqls.<SysUserRole>custom()
					.andIn(SysUserRole::getUserId,infoList)).build();
			sysUserRoleMapper.deleteByExample(example);
		}

		//然后批量新增
		for (SysUserRole roleInfo : infoList) {
			SysUserRole userRoleInfo = new SysUserRole();
			userRoleInfo.setUserId(roleInfo.getUserId());
			userRoleInfo.setRoleId(roleInfo.getRoleId());

			sysUserRoleMapper.insert(userRoleInfo);
		}
		return iRtn;
	}


}
