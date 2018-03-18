/*
 * 文 件 名:  CloudRoleController.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  ZJJSCommon
 * 修改时间:  2015年5月18日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.web.controller;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.model.SysRole;
import com.lwx.usm.model.SysUserRole;
import com.lwx.usm.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
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
@RequestMapping("/crole")
public class SysRoleController extends BaseController
{
    @Autowired
    private SysRoleService cloudRoleService;
    
    @RequestMapping("toRoleList")
    public String toRoleListHtml(){
    	return "system/roleList";
    }
    
    
    /**
     * <查询菜单信息> <查询菜单基本信息> 系统管理查询list----》用于页面展示
     * 
     * @param zxm
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryRoleInfo", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> queryRoleInfo(SysRole queryInfo, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        logger.info("queryRoleInfo【 系统管理查询list----》用于页面展示 】");
        Map<String, Object> map = new HashMap<String, Object>();

        SimplePage sp = getPage();
    	Page<SysRole> page  = cloudRoleService.queryList(queryInfo,sp);
        
        map.put("rows", page.getResult());
        map.put("total", page.getTotal());
        
        map.put("state", "succ");

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
    @RequestMapping(value = "/addRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addRoleInfo(@RequestBody SysRole info, HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        logger.info("addRoleInfo【 系统管理------》角色add 】");
        Map<String, Object> map = new HashMap<String, Object>();
        int num = cloudRoleService.save(info);
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
    public Map<String, Object> findByPrimaryKey(@RequestBody String roleId) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
        SysRole _info = cloudRoleService.findByPrimaryKey(roleId);
        if(_info!=null)
        {
            map.put("cloudRole", _info);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
    }
    
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateRole(@RequestBody SysRole info) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	int iRtn= cloudRoleService.update(info);
        if(iRtn > 0)
        {
            map.put("cloudRole", info);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
    }
    
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRole(@RequestBody String... roleIds) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(roleIds!=null && roleIds.length>0)
    	{
    		for(String roleId:roleIds)
    		{
    			cloudRoleService.delete(roleId);
    		}	
    	}	
    	
    	map.put("roleCount", roleIds!=null?roleIds.length:0);
    	map.put("state", "succ");
        
        return map;
    }
    
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(SysRole info,HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {   
		SimplePage sp = getPage();
    	Map<String, Object> map = new HashMap<String, Object>();
    	Page<SysRole> page  = cloudRoleService.queryList(info,sp);
        
        map.put("rows", page.getResult());
        map.put("total", page.getTotal());
        
        map.put("state", "succ");
        
        return map;
    }
    
    @RequestMapping(value = "/disableRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> disableRole(@RequestBody String... roleIds) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	int iRtn= cloudRoleService.disableRole(roleIds);
        if(iRtn > 0)
        {
            map.put("roleCount", iRtn);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
    }
    
    @RequestMapping(value = "/enableRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> enableRole(@RequestBody String... roleIds) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	int iRtn= cloudRoleService.enableRole(roleIds);
        if(iRtn > 0)
        {
            map.put("roleCount", iRtn);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
    }
    
    @RequestMapping(value = "/queryUserRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryUserRole(@RequestBody String userId,HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<SysRole> rtnList = cloudRoleService.queryUserRole(userId);
    	
    	if(rtnList!=null)
        {
            map.put("rows", rtnList);
            map.put("state", "succ");
        }
        else
        {
        	map.put("state", "succ");
        	map.put("msg", "角色为空");
        }
        
        return map;
    	
    }
    
    @RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveUserRole(@RequestBody SysUserRole[] infoList) throws IOException
    {        
    	Map<String, Object> map = new HashMap<String, Object>();
    	int iRtn= cloudRoleService.saveUserRole(Arrays.asList(infoList));
        if(iRtn > 0)
        {
            map.put("roleCount", iRtn);
            map.put("state", "succ");
        }
        else
        {
           map.put("state", "fail");
           map.put("msg", "id为空");
        }
        return map;
    }
    
}
