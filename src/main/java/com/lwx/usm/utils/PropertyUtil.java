package com.lwx.usm.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.Assert;

public class PropertyUtil {

	public static Object getProperty(String prop,Object obj){
		Assert.notNull(obj, "对象为空无法获取属性:"+prop);
		Object key;
		try {
			key = PropertyUtils.getProperty(obj, prop);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("从"+obj.getClass().getName()+"获取属性["+prop+"]失败"+e.getMessage(),e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("从"+obj.getClass().getName()+"获取属性["+prop+"]失败"+e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("从"+obj.getClass().getName()+"获取属性["+prop+"]失败"+e.getMessage(),e);
		} catch (IllegalArgumentException e){
			throw new RuntimeException("从"+obj.getClass().getName()+"获取属性["+prop+"]失败"+e.getMessage(),e);
		}
		
		return key;
	}
	
	public static void setProperty(String k,Object v,Object obj){
		Assert.notNull(obj, "对象为空无法设置属性");

		try {
			PropertyUtils.setProperty(obj, k, v);
		} catch (Exception e) {
			//忽略
		}

	}
}
