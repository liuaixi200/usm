package com.lwx.usm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.exception.BizException;
import com.lwx.usm.mapper.LoginSessionMapper;
import com.lwx.usm.model.LoginLog;
import com.lwx.usm.model.LoginSession;
import com.lwx.usm.service.LoginLogService;
import com.lwx.usm.service.LoginSessionService;
import com.lwx.usm.utils.BaseUtil;
import com.lwx.usm.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.Date;

@Service("loginSessionServiceImpl")
public class LoginSessionServiceImpl implements LoginSessionService {
	
	@Resource
	private LoginSessionMapper loginSessionMapper;
	
    protected Log logger = LogFactory.getLog(getClass());
    
    @Resource
    private LoginLogService loginLogService;

	@Override
	public void addSesison(LoginSession login) {
		// TODO Auto-generated method stub
		//设置Ip
		try {
			//存在即更新
			LoginSession old = loginSessionMapper.selectByPrimaryKey(login.getSessionId());
			if(null == old){
				loginSessionMapper.insertSelective(login);
			} else {
				LoginSession newS = new LoginSession();
				//如果用户名不相同，干掉之前的
				if(StringUtils.equals(login.getLoginName(), old.getLoginName())){
					newS.setSessionId(login.getSessionId());
					newS.setUpdateTime(new Date());
					BeanUtils.copyProperties(login, newS);
					loginSessionMapper.updateByPrimaryKeySelective(newS);
				} else {
					loginSessionMapper.deleteByPrimaryKey(login.getSessionId());
					loginSessionMapper.insertSelective(login);
				}
				
			}
			
		} catch (Exception e) {
			//吞掉异常
			logger.error("写入登陆会话异常",e);
			throw new BizException("写入登陆会话异常");
		}
	}

	@Override
	public Page<LoginSession> queryListByPage(LoginSession loginSession, SimplePage sp) {
		Page<LoginSession> page = PageHelper.startPage(sp.getPageNo(), sp.getPageSize());
		loginSessionMapper.select(loginSession);
		return page;
	}

	@Override
	public void deleteSession(String sessionId) {
		// TODO Auto-generated method stub
		loginSessionMapper.deleteByPrimaryKey(sessionId);
		//删 掉1个小时之前的
		Date date = new Date();
		Example example = new Example.Builder(LoginSession.class).where(
				WeekendSqls.<LoginSession>custom().andLessThanOrEqualTo(LoginSession::getLoginTime,
						DateUtil.addHours(date,-1))
		).build();
		loginSessionMapper.deleteByExample(example);
	}

	@Override
	public void updateSessionTime(String jessionId) {
		// TODO Auto-generated method stub
		LoginSession newS = new LoginSession();
		newS.setSessionId(jessionId);
		newS.setUpdateTime(new Date());
		loginSessionMapper.updateByPrimaryKeySelective(newS);
	}

}
