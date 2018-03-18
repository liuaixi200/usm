package com.lwx.usm.dto;

import com.lwx.usm.model.SysUser;

/**
 * @author liuax01
 * @date 2018/3/18 12:04
 */
public class SysUserDto extends SysUser {

	private String password;

	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
