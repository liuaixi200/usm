package com.lwx.usm.service;

import com.lwx.usm.model.SysUserDataAuth;

import java.util.List;

public interface SysUserDataAuthService {

	public List<SysUserDataAuth> queryDataAuthListByUser(String userId,String authType);
	
	public List<SysUserDataAuth> queryDataAuthListByParam(SysUserDataAuth params);
	
	void saveUserDateAuth(SysUserDataAuth[] datas);

}
