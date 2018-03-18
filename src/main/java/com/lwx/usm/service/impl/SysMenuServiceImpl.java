/*
 * 文 件 名:  CloudMenuService.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  Leon
 * 修改时间:  2015年7月14日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.service.impl;

import com.lwx.usm.dto.Node;
import com.lwx.usm.enums.EnumBoolean;
import com.lwx.usm.mapper.SysButtonMapper;
import com.lwx.usm.mapper.SysMenuMapper;
import com.lwx.usm.mapper.SysRoleMenuMapper;
import com.lwx.usm.mapper.SysUserMapper;
import com.lwx.usm.model.*;
import com.lwx.usm.service.SysMenuService;
import com.lwx.usm.service.SysRoleService;
import com.lwx.usm.utils.Constant;
import com.lwx.usm.utils.ListUtil;
import com.lwx.usm.utils.NodeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.*;

/**
 * <功能详细描述>
 *
 * @author leon
 * @version [版本号, 2015年7月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("CloudMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysButtonMapper sysButtonMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * <修改菜单信息>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int update(SysMenu info) {
		int iReturn = sysMenuMapper.updateByPrimaryKeySelective(info);
		return iReturn;
	}


	/**
	 * <根据主键查询>
	 *
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public SysMenu findByPrimaryKey(String menuId) {
		SysMenu info = sysMenuMapper.selectByPrimaryKey(menuId);
		return info;
	}

	/**
	 * <查询Menu列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<SysMenu> queryList(SysMenu info) {
		List<SysMenu> rtnList = sysMenuMapper.select(info);

		return rtnList;
	}

	/**
	 * <查询角色所有的Menu列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<SysMenu> queryRoleMenu(String roleId) {
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setRoleId(roleId);
		List<SysRoleMenu> list = sysRoleMenuMapper.select(sysRoleMenu);

		Example example = new Example.Builder(SysMenu.class).where(WeekendSqls.<SysMenu>custom().andIn(SysMenu::getMenuId,
				list)).build();
		List<SysMenu> rtnList = sysMenuMapper.selectByExample(example);

		return rtnList;
	}

	/**
	 * <查询用户的所有的Menu列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public List<SysMenu> queryUserMenu(String userId) {
		//当前用户如果是管理员，查出所有的
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		if (StringUtils.equals(Constant.SYSTEM_USER_ADMIN, user.getType())) {
			SysMenu menu = new SysMenu();
			menu.setStatus(EnumBoolean.YES.getCode());
			return sysMenuMapper.select(menu);
		} else {
			List<SysRole> urList = sysRoleService.queryUserRole(userId);
			Example example = new Example.Builder(SysRoleMenu.class).
					where(WeekendSqls.<SysRoleMenu>custom().andIn(SysRoleMenu::getRoleId,
							urList)).build();
			//criteria.andEqualTo("menu_type", ConstantRoleMenuType.MENU);
			List<SysRoleMenu> rlist = sysRoleMenuMapper.selectByExample(example);
			Example example2 = new Example.Builder(SysMenu.class).
					where(WeekendSqls.<SysMenu>custom().andIn(SysMenu::getMenuId,
							rlist)).build();
			List<SysMenu> mlist = sysMenuMapper.selectByExample(example2);
			return mlist;
		}
	}

	/**
	 * <查询Menu树>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Node> queryTree(String... parentIds) {
		List<Node> rtnList = new ArrayList<>();
		List<SysMenu> allMenuList = sysMenuMapper.selectAll();

		if (parentIds == null || parentIds.length == 0 || (parentIds != null && (parentIds[0] == null || parentIds[0].equalsIgnoreCase("-1")))) //查询整个树
		{
			Node rootNode = new Node();
			rootNode.setId("-1");
			rootNode.setParentId(null);
			rootNode.setText("菜单列表");
			rootNode.setState("open");
			NodeUtil.buildTree(rootNode,NodeUtil.build4CloudMenuInfo(allMenuList));
			rtnList.add(rootNode);
		} else //查询某个或某几个子树
		{
			for (String parentId : parentIds) {
				SysMenu sysMenu = this.findByPrimaryKey(parentId);
				Node rootNode = NodeUtil.build4CloudMenuInfo(sysMenu);
				NodeUtil.buildTree(rootNode,NodeUtil.build4CloudMenuInfo(allMenuList));
				rtnList.add(rootNode);
			}
		}

		return rtnList;
	}

	/**
	 * 查询一个角色所拥有的全部菜单
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Node> queryRoleMenuTree(String roleId, String userId, List<String> btnAuths) {

		//获取所有菜单
		List<SysMenu> menuList = queryUserMenu(userId);
		List<Node> menuNodes = NodeUtil.build4CloudMenuInfo(menuList);
		//获取所有按钮
		List<SysButton> buttonlist = sysButtonMapper.selectAll();
		//过滤获取当前用户拥有的按钮权限
		if (buttonlist != null && btnAuths != null) {
			Iterator<SysButton> it = buttonlist.iterator();
			while (it.hasNext()) {
				SysButton sysButton = it.next();
				if (!btnAuths.contains(sysButton.getButtonId())) {
					it.remove();
				}
			}
		}

		List<Node> buttonNodes = NodeUtil.build4SysButton(buttonlist);

		//获取当前角色拥有的菜单和按钮
		Map<String, Object> params = new HashMap<>();
		params.put("roleId", roleId);
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setRoleId(roleId);
		//params.put("menuType", "2");
		List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.select(sysRoleMenu);
		//提取menuId
		List<String> selectMenuId = ListUtil.getSlist(roleMenuList, "menuId");
		NodeUtil.setChecked(selectMenuId, menuNodes);

		NodeUtil.setChecked(selectMenuId, buttonNodes);
		//将BUTTON放到MENU下面
		NodeUtil.buildTree(menuNodes, buttonNodes);
		//构造树
		Node rootNode = new Node();
		rootNode.setId("-1");
		rootNode.setParentId("");
		rootNode.setText("菜单列表");
		rootNode.setType("1");
		//将menu放入rootNode下面
		NodeUtil.buildTree(rootNode, menuNodes);
		List<Node> rtnList = new ArrayList<Node>();
		rtnList.add(rootNode);
		return rtnList;
	}

	/**
	 * 查询用户所拥有的全部菜单，用于登录后左边的菜单树展现
	 *
	 * @param userId，如果是管理员的话，不传入userId，将会获取所有的菜单
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public Node queryUserMenuTree(String userId) {
		List<Node> rtnList = new ArrayList<>();
		List<SysMenu> userMenuList = queryUserMenu(userId);

		//这种排序不是最佳方式，最佳方式应该在数据库中添加“level”字段，sql中按照level排序，在内存中排序在有大数据量的时候效率较低
		Node rootNode = new Node();
		rootNode.setId("-1");
		rootNode.setParentId(null);
		rootNode.setText("菜单列表");
		rootNode.setState("open");
		NodeUtil.buildTree(rootNode,NodeUtil.build4CloudMenuInfo(userMenuList));
		return rootNode;
	}

	/**
	 * <查询Menu列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int disableMenu(String[] menuIds) {
		int iRtn = 0;
		Example example = new Example.Builder(SysMenu.class).where(WeekendSqls.<SysMenu>custom()
		.andIn(SysMenu::getMenuId,Arrays.asList(menuIds))).build();
		SysMenu sysMenu = new SysMenu();
		sysMenu.setStatus(EnumBoolean.NO.getCode());
		sysMenuMapper.updateByExample(sysMenu,example);
		return iRtn;
	}

	/**
	 * <查询Menu列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int enableMenu(String[] menuIds) {
		int iRtn = 0;
		Example example = new Example.Builder(SysMenu.class).where(WeekendSqls.<SysMenu>custom()
				.andIn(SysMenu::getMenuId,Arrays.asList(menuIds))).build();
		SysMenu sysMenu = new SysMenu();
		sysMenu.setStatus(EnumBoolean.YES.getCode());
		sysMenuMapper.updateByExample(sysMenu,example);
		return iRtn;
	}

	@Override
	public int saveRoloMenu(SysRoleMenu[] infoList) {
		if(infoList != null){
			Arrays.asList(infoList).forEach(srm->sysRoleMenuMapper.insertSelective(srm));
		}
		return 0;
	}

	/**
	 * <保存角色的菜单列表>
	 *
	 * @param info
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int saveRoleMenu(SysRoleMenu[] infoList) {
		int iRtn = infoList != null ? infoList.length : 0;

		//先批量删除
		if (infoList != null && infoList.length > 0) {
			SysRoleMenu info = infoList[0];
			sysRoleMenuMapper.deleteByPrimaryKey(info.getId());
		}

		//然后批量新增
		List<SysRoleMenu> rmlist = new ArrayList<>();
		for (SysRoleMenu info : infoList) {
			if (StringUtils.isEmpty(info.getMenuId())) {
				continue;
			}
			SysRoleMenu roleMenuInfo = new SysRoleMenu();
			roleMenuInfo.setMenuId(info.getMenuId());
			roleMenuInfo.setRoleId(info.getRoleId());
			roleMenuInfo.setMenuType(info.getMenuType());
			//改为批处理
			rmlist.add(roleMenuInfo);
		}
		rmlist.forEach(sr->{
			sysRoleMenuMapper.insertSelective(sr);
		});

		return iRtn;
	}
}
