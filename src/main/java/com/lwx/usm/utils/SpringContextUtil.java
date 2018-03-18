package com.lwx.usm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext; // Spring应用上下文环境

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		
		SpringContextUtil.applicationContext = arg0;
	}
	
	public static boolean containsBean(String name){
		return SpringContextUtil.applicationContext.containsBean(name);
	}

	public static <T> T getBean(String name) throws BeansException {
		return (T) SpringContextUtil.applicationContext.getBean(name);
	}

}
