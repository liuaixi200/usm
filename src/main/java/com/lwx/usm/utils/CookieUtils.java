package com.lwx.usm.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class CookieUtils {
	
	public static String JSESSIONID = "JSESSIONID";
	
	public static String getJessionId(HttpServletRequest req){
		return getCookieValue(req, JSESSIONID);
	}

	public static String getCookieValue(HttpServletRequest req,String cookieName){
		Cookie c = getCookie(req, cookieName);
		if(null != c){
			return c.getValue();
		}
		return null;
	}
	
	public static Cookie getCookie(HttpServletRequest req,String cookieName){
		Cookie[] cs = req.getCookies();
		if(null != cs ){
			for(Cookie c : cs){
				if(StringUtils.equals(c.getName(), cookieName)){
					return c;
				}
			}
		}
		return null;
	}
}
