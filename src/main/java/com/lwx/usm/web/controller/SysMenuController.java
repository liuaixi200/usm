/*
 * 文 件 名:  CloudMenuController.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  ZJJSCommon
 * 修改时间:  2015年5月18日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.web.controller;

import com.lwx.usm.dto.Node;
import com.lwx.usm.model.SysMenu;
import com.lwx.usm.model.SysRoleMenu;
import com.lwx.usm.model.SysUser;
import com.lwx.usm.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author ZJJSCommon
 * @version [版本号, 2015年5月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/cmenu")
public class SysMenuController extends BaseController
{
    @Autowired
    private SysMenuService cloudMenuService;
    
    @RequestMapping("toMenuList")
    public String toMenuListHtml(){
    	return "system/menuList";
    }
    
    /**
     * <查询菜单信息> <查询菜单基本信息> 系统管理查询list----》用于页面展示
     * 
     * @param zxm
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryMenuInfo", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> queryMenuInfo(SysMenu queryInfo, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        logger.info("queryMenuInfo【 系统管理查询list----》用于页面展示 】");
        Map<String, Object> map = new HashMap<String, Object>();

        List<SysMenu> list = cloudMenuService.queryList(queryInfo);
        map.put("state", "succ");
        map.put("total", list.size());
        map.put("rows", list);

        return map;
    }
    
    /**
     * <新增菜单信息> <新增菜单基本信息> 系统管理------》用户add
     *
     * @param leon
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/addMenuInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addMenuInfo(@RequestBody SysMenu info, HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        logger.info("addMenInfo【 系统管理------》菜单add 】");
		Map<String, Object> map = new HashMap<String, Object>();
        int num = 0;
        if (num == 1)
        {
            map.put("state", "succ");
            map.put("msg", "操作成功!");
        }
        else
        {
            map.put("state", "fail");
            map.put("msg", "操作失败!");
        }
        return map;
    }
    
    /**
     * <查询菜单信息>
     * <查询菜单基本信息> 暂时没用到
     * @param info
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/findByPrimaryKey", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findByPrimaryKey(@RequestBody String menuId) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
		SysMenu _info = cloudMenuService.findByPrimaryKey(menuId);
        if(_info!=null)
        {
            map.put("cloudMenu", _info);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
    }
    
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateMenu(@RequestBody SysMenu meunInfo) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	if(StringUtils.isEmpty(meunInfo.getMenuId())){
    		  map.put("state", "fail");
              map.put("msg", "menuid不能为空");
    	} else{
    		//只允许修改序号 
			SysMenu menuInfo2 = new SysMenu();
    		menuInfo2.setMenuId(meunInfo.getMenuId());
    		menuInfo2.setMenuCode(meunInfo.getMenuCode());
        	int iRtn= cloudMenuService.update(meunInfo);
            if(iRtn > 0)
            {
                map.put("cloudMenu", meunInfo);
                map.put("state", "succ");
            }
    	}
        return map;
    }
    
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteMenu(@RequestBody String... meunIds) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(meunIds!=null && meunIds.length>0)
    	{

    	}	
    	
    	map.put("menuCount", meunIds!=null?meunIds.length:0);
    	map.put("state", "succ");
        
        return map;
    }
    
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(SysMenu meunInfo,HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<SysMenu> rtnList = cloudMenuService.queryList(meunInfo);
        
        map.put("rows", rtnList);
        map.put("total", rtnList.size());
        
        map.put("state", "succ");
        
        return map;
    }
    
    @RequestMapping(value = "/queryTree", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryTree(@RequestBody String parentMenuId,HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Node> rtnTree = cloudMenuService.queryTree(parentMenuId);
        
    	if(rtnTree!=null)
        {
            map.put("menuTree", rtnTree);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "succ");
           map.put("msg", "菜单树为空");
        }
        
        return map;
    }
    
    @RequestMapping(value = "/disableMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> disableMenu(@RequestBody String... menuIds) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	int iRtn= cloudMenuService.disableMenu(menuIds);
        if(iRtn > 0)
        {
            map.put("menuCount", iRtn);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
    }
    
    @RequestMapping(value = "/enableMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> enableMenu(@RequestBody String... menuIds) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	int iRtn= cloudMenuService.enableMenu(menuIds);
        if(iRtn > 0)
        {
            map.put("menuCount", iRtn);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
	}
    
    @RequestMapping(value = "/queryRoleMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryRoleMenu(String roleId, HttpServletRequest request, HttpServletResponse response) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<SysMenu> rtnList = cloudMenuService.queryRoleMenu(roleId);
        
        map.put("rows", rtnList);
        map.put("total", rtnList.size());
        
        map.put("state", "succ");
        
        return map;
    }
    
    @RequestMapping(value = "/queryRoleMenuTree", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryRoleMenuTree(@RequestBody String roleId,HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	String userId = getCurUserId();

    	List<Node> rtnTree = cloudMenuService.queryRoleMenuTree(roleId, userId,getUserAuth4Btn());
         
    	if(rtnTree!=null)
        {
            map.put("menuTree", rtnTree);
            map.put("state", "succ");
        }
        else
        {
        	map.put("state", "succ");
        	map.put("msg", "菜单树为空");
        }
        
        return map;
    }
    
    @RequestMapping(value = "/queryUserMenuTree", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryUserMenuTree(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	SysUser _info = new SysUser();
        _info = (SysUser)session.getAttribute("cloudUser");
		Node rtnTree = null;
    	
    	//如果是管理员，不需要传入用户ID，会将所有的菜单都查出来
    	rtnTree = cloudMenuService.queryUserMenuTree(_info.getUserId());
    	
    	if(rtnTree!=null)
        {
            map.put("menuTree", rtnTree);
            map.put("state", "succ");
        }
        else
        {
        	map.put("state", "succ");
        	map.put("msg", "菜单树为空");
        }
        
        return map;
    }
    
    @RequestMapping(value = "/saveRoleMenu", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveRoleMenu(@RequestBody SysRoleMenu[] infoList)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	int iRtn = cloudMenuService.saveRoloMenu(infoList);
    	
    	if(iRtn > 0)
        {
            map.put("menuCount", iRtn);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "保存失败");
        }
    	
    	return map;
    }
   
    
    
}
