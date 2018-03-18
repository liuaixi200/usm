package com.lwx.usm.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

public class DataSourceIntercept {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	public void selectDataSource(JoinPoint jp){

		logger.info("拦截开始:"+jp.getSignature().getName());
		
		Class clazz = jp.getSignature().getDeclaringType();
		DataSourceSelect anno = (DataSourceSelect)clazz.getAnnotation(DataSourceSelect.class);
		if(null == anno){
			DataSourceHolder.clearDataSourceName();
		} else {
			String value = anno.value();
			if(StringUtils.isEmpty(value)){
				DataSourceHolder.clearDataSourceName();
			} else {
				DataSourceHolder.setDataSourceName(value);
			}
		}
		
		
	}
	public static void main(String[] args) {
		 Map<String,String> map = new HashMap<>();
		 System.out.println(map.get(null));
	}
}
