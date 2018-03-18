package com.lwx.usm.web.controller;

import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.model.LoginLog;
import com.lwx.usm.model.SysUser;
import com.lwx.usm.utils.Constant;
import com.lwx.usm.utils.CookieUtils;
import com.lwx.usm.utils.NetworkUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


/**
 * 
 * controller层的公共类，放置一些controller公用的方法
 * <功能详细描述>
 * 
 * @author  liudong
 * @version  [版本号, 2015年5月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseController 
{
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private  HttpServletRequest request;
    /**
     * 获得登录信息
     * @return
     */
    public SysUser getCloudUserInfo(){
    	HttpSession session = request.getSession();
		SysUser cloudUserInfo = new SysUser();
    	cloudUserInfo = (SysUser)session.getAttribute("cloudUser");
    	return cloudUserInfo;
    }
    
    public boolean isSuperUser(){
		SysUser cloudUserInfo = getCloudUserInfo();
    	if(Constant.SYSTEM_USER_ADMIN.equals(cloudUserInfo.getType())){
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    public SimplePage getPage(){
    	SimplePage sp = new SimplePage();
    	String page = request.getParameter("page");
    	if(StringUtils.isNotEmpty(page)){

    		sp.setPageNo(Integer.parseInt(page));
    	}
    	String rows =  request.getParameter("rows");
    	if(StringUtils.isNotEmpty(rows)){
    		sp.setPageSize(Integer.parseInt(rows));
    	}
    	return sp;
    }

    /**
     * 获取当前登录用户id
     * @return
     */
	public String getCurUserId(){
		HttpSession session = request.getSession();
		SysUser cloudUserInfo = new SysUser();
    	cloudUserInfo = (SysUser)session.getAttribute("cloudUser");
		return cloudUserInfo.getUserId();
	}
	
	public List<String> getUserData4CityCompany(){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(Constant.SESSION_DATA_AUTH_CITY);
		return (List<String>)obj;
	}
	
	public List<String> getUserData4Project(){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(Constant.SESSION_DATA_AUTH_PROJECT);
		return (List<String>)obj;
	}
	

	
	public List<String> getUserAuth4Btn(){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(Constant.SESSION_FUNC_BTN);
		return (List<String>)obj;
	}
	
	public LoginLog getLoginLog(){
		LoginLog log = new LoginLog();
		log.setCreateTime(new Date());
		log.setUpdateTime(new Date());
		log.setCreateUser("sys");
		log.setUpdateUser("sys");
		log.setSessionId(CookieUtils.getJessionId(request));
		log.setRemoteIp(NetworkUtil.getIpAddress(request));

		return log;
	}

}
