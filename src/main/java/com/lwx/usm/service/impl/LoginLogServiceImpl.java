package com.lwx.usm.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.lwx.usm.utils.NetworkUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.mapper.LoginLogMapper;
import com.lwx.usm.model.LoginLog;
import com.lwx.usm.model.LoginSession;
import com.lwx.usm.service.LoginLogService;
import com.lwx.usm.service.LoginSessionService;
import com.lwx.usm.utils.Constant;

@Service("loginLogServiceImpl") 
public class LoginLogServiceImpl implements LoginLogService{

	@Resource
	private LoginLogMapper loginLogMapper;
	
    protected Log logger = LogFactory.getLog(getClass());
    
    @Resource
    private LoginSessionService loginSessionService;
	
	@Override
	public void addLoginLog(LoginLog log) {
		log.setLoginTime(new Date());
		log.setLocalIp(NetworkUtil.getLocalIp());
		
		//设置Ip
		try {
			// jsessionI
			loginLogMapper.insertSelective(log);
		} catch (Exception e) {
			//吞掉异常
			logger.error("写入登 (陆|出)日志失败",e);
		}
		if(Constant.LOGIN_STATUS_OK.equals(log.getLoginStatus()) && Constant.LOGIN_TYPE_LOGIN.equals(log.getLoginType())){
			LoginSession login = new LoginSession();
			BeanUtils.copyProperties(log, login);
			login.setCreateTime(new Date());
			login.setUpdateTime(new Date());
			login.setSessionSize(login.getSessionSize());
			login.setCreateUser(log.getLoginName());
			login.setUpdateUser(log.getLoginName());
			loginSessionService.addSesison(login);
		}
	}

	@Override
	public Page<LoginLog> queryListByPage(LoginLog loginLog, SimplePage sp) {
		Page<LoginLog> page = PageHelper.startPage(sp.getPageNo(), sp.getPageSize());
		loginLogMapper.select(loginLog);
		return page;
	}

}
