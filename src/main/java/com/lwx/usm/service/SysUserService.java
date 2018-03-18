/*
 * 文 件 名:  CloudUserService.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  ZJJSCommon
 * 修改时间:  2015年5月14日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.service;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.dto.SysUserDto;
import com.lwx.usm.model.SysUser;

import java.util.List;

/**
 * <云用户接口> <功能详细描述>
 * 
 * @author liudong
 * @version [版本号, 2015年5月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SysUserService
{
    /**
     * <用户注册> <添加用户注册详细信息>
     * 
     * @see [类、类#方法、类#成员]
     */
    public int saveRegister(SysUserDto info);
    
    /**
     * <用户注册表单验证> <用户名、邮箱验证是否存在>
     * 
     * @param info
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<SysUser> queryRegisterValite(SysUser info);
    
    /**
     * <用户登陆验证> <用户名、密码验证是否存在>
     * 
     * @param info
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<SysUser> queryLoginValite(SysUser info);
    
    /**
     * <更改密码> <功能详细描述>
     * 
     * @param info
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updatePassword(SysUser info);
    
    /**
     * <查询User对象> <可以根据name或Id查>
     * 
     * @param info
     * @return
     * @see [类、类#方法、类#成员]
     */
    
    public List<SysUser> queryUserInfo(SysUser user);

    public Page<SysUser> queryUserInfoLst(SysUser user,SimplePage sp);
    

    public SysUser findByPrimaryKey(String userId);
    
    public SysUser resetPassword(SysUser userInfo);
    
    public SysUser updateUserSelective(SysUser userInfo);

    
}
