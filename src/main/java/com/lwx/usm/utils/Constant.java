/*
 * 文 件 名:  Constant.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  ZJJSCommon
 * 修改时间:  2015年6月1日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.utils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  ZJJSCommon
 * @version  [版本号, 2015年6月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Constant
{
    /**
     * 系统管理员名称
     */
   // public static String SYSTEMADMIN_USER_NAME="admin"; 废弃
	
	public static String SYSTEM_USER_ADMIN = "1"; //系统管理员
	
	public static String SYSTEM_USER_GENERAL = "2"; //普通用户
	
	public static String SYSTEM_DATA_AUTH_CITY = "1"; // 城市公司
	
	public static String SYSTEM_DATA_AUTH_PROJECT= "2"; //项目
	
	public static String SESSION_DATA_AUTH_CITY = "auth4city"; //存入SESSION中的城市公司权限
	
	public static String SESSION_DATA_AUTH_PROJECT = "auth4project"; //存入SESSION中的项目权限
	
	public static String SESSION_FUNC_BTN = "auth4btn"; //存入SESSION中的按钮权限
	
	public static String ORG_TYPE_COMPANY = "COMPANY"; //城市公司
	
	public static String ORG_TYPE_PROJECT = "PROJECT"; //项目
	
	
	public static String SUCCESS = "0000";
	
	public static String ERROR = "0001";
	
	public static String LOGIN_TYPE_LOGIN = "1";
	public static String LOGIN_TYPE_OUT = "2";
	public static String LOGIN_STATUS_OK = "1";
	public static String LOGIN_STATUS_FAIL = "2";
	
	public static String ACCOUNT_RESULT_SUCCESS = "1";
	public static String ACCOUNT_RESULT_ERROR = "2";
}
