package com.lwx.usm.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.model.LoginSession;

public interface LoginSessionService{

	public void addSesison(LoginSession login);
	
	public Page<LoginSession> queryListByPage(LoginSession loginSession,SimplePage sp);
	
	public void deleteSession(String jessionId);
	
	public void updateSessionTime(String jessionId);
}
