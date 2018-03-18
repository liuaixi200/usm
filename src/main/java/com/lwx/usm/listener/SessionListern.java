package com.lwx.usm.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.lwx.usm.service.LoginSessionService;
import com.lwx.usm.utils.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionListern implements HttpSessionListener{


	protected Log logger = LogFactory.getLog(getClass());

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();

		logger.info("创建sesion,sessionId="+session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		//销毁
		HttpSession session = arg0.getSession();
		logger.info("销毁sesion,sessionId="+session.getId());
		LoginSessionService service = SpringContextUtil.getBean("loginSessionServiceImpl");
		service.deleteSession(session.getId());
	}

}
