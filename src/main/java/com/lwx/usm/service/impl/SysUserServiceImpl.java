/*
 * 文 件 名:  CloudUserServiceImpl.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  ZJJSCommon
 * 修改时间:  2015年5月14日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.dto.SysUserDto;
import com.lwx.usm.enums.ConstantUserStatus;
import com.lwx.usm.enums.EnumBoolean;
import com.lwx.usm.mapper.SysUserMapper;
import com.lwx.usm.model.SysUser;
import com.lwx.usm.model.SysUserPass;
import com.lwx.usm.service.SysUserPassService;
import com.lwx.usm.service.SysUserService;
import com.lwx.usm.utils.BaseUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author liudong
 * @version [版本号, 2015年5月14日]
 * @see [相关类/方法]
 * @since [云门户/云用户]
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysUserMapper sysUserMapper;

	@Resource
	private SysUserPassService sysUserPassService;

	/**
	 * 用户注册
	 * 
	 * @param info
	 */
	@Override
	public int saveRegister(SysUserDto info) {
		if(StringUtils.isEmpty(info.getUserId())){
			info.setUserId(BaseUtil.getUUID());
		}
		info.setCreateTime(new Date());
		info.setUpdateTime(new Date());
		info.setStatus(ConstantUserStatus.STATUS_AVAILABLE);
		String pwd = info.getPassword();
		// 改成MD5
		String code = DigestUtils.md5Hex(info.getUserName() + pwd);
		// info.setPassword(code);
		SysUserPass pass = new SysUserPass();
		pass.setLoginName(info.getLoginName());
		pass.setErrors(0);
		pass.setLockFlag(EnumBoolean.NO.getCode());
		pass.setLockTime(null);
		pass.setLoginCount(0);
		pass.setPassword(code);
		pass.setUpdatePassFlag(EnumBoolean.YES.getCode());
		sysUserPassService.addSysUserPass(pass);
		info.setPassword(null);
		int result = sysUserMapper.insertSelective(info);
		return result;
	}

	/**
	 * 云用户注册表单验证
	 * 
	 * @param info
	 * @return
	 */
	@Override
	public List<SysUser> queryRegisterValite(SysUser user) {

		List<SysUser> result = sysUserMapper.select(user);
		return result;
	}

	/**
	 * 用户登陆验证
	 * 
	 * @param info
	 * @return
	 */
	@Override
	public List<SysUser> queryLoginValite(SysUser user) {
		List<SysUser> result = sysUserMapper.select(user);
		return result;
	}

	/**
	 * 更改密码
	 * 
	 * @param info
	 * @return
	 */
	@Override
	public void updatePassword(SysUser info) {
		sysUserMapper.updateByPrimaryKey(info);
	}

	@Override
	public List<SysUser> queryUserInfo(SysUser user) {
		// TODO Auto-generated method stub
		return sysUserMapper.select(user);
	}

	/**
	 * 查询用户信息
	 * 
	 * @param info
	 * @return
	 */
	@Override
	public Page<SysUser> queryUserInfoLst(SysUser user, SimplePage sp) {

		Page<SysUser> page = PageHelper.startPage(sp.getPageNo(), sp.getPageSize());
		queryUserInfo(user);
		return page;
	}


	/**
	 * 查询用户信息
	 * 
	 * @param info
	 * @return
	 */
	@Override
	public SysUser findByPrimaryKey(String userId) {
		SysUser result = sysUserMapper.selectByPrimaryKey(userId);
		return result;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param info
	 * @return
	 */
	@Override
	public SysUser resetPassword(SysUser userInfo) {

		String initPassword = "123456";
		SysUserPass pass = new SysUserPass();
		pass.setLoginName(userInfo.getLoginName());
		pass.setLockTime(null);
		pass.setLockFlag(EnumBoolean.NO.getCode());
		pass.setErrors(0);
		pass.setUpdatePassFlag(EnumBoolean.YES.getCode());
		String code = DigestUtils.md5Hex(userInfo.getUserName() + initPassword);
		pass.setPassword(code);
		sysUserPassService.updateSysUserPass(pass);
		return userInfo;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param info
	 * @return
	 */
	@Override
	public SysUser updateUserSelective(SysUser userInfo) {
		sysUserMapper.updateByPrimaryKeySelective(userInfo);
		return userInfo;
	}

}
