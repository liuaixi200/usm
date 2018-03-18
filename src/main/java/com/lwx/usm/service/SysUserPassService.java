package com.lwx.usm.service;

import com.lwx.usm.model.SysUserPass;

public interface SysUserPassService {

	SysUserPass getSysUserPassByUserName(String userName);
	
	void updateSysUserPass(SysUserPass pass);
	
	int addSysUserPass(SysUserPass pass);
	
	/**
	 * 密码强度校验
	 * @param password
	 * @return
	 */
	String validatePass(String password);
}
