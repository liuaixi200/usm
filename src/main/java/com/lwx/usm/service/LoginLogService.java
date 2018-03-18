package com.lwx.usm.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.model.LoginLog;

public interface LoginLogService {

	void addLoginLog(LoginLog log);
	
	public Page<LoginLog> queryListByPage(LoginLog loginLog,SimplePage sp);
}
