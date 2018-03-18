package com.lwx.usm.listener;

import com.alibaba.fastjson.util.TypeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CacheInitListener implements ServletContextListener
{
	protected Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{

		TypeUtils.compatibleWithJavaBean = true; //处理fastJSON首字母大小写的问题
		logger.info("=================初始化缓存数据=================");

		//ServletContext context=sce.getServletContext();
		//ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(context);
	
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{
		
	}
}
