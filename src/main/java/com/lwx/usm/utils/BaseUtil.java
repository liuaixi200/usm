/*
 * 文 件 名:  BaseUtil.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  ZJJSCommon
 * 修改时间:  2015年5月14日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import tk.mybatis.mapper.entity.Example;

import java.util.UUID;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author liudong
 * @version [版本号, 2015年5月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseUtil {
	/**
	 * <获取主鍵>
	 * <获取32位UUID>
	 *
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return temp;
	}

	/**
	 * BASE64解密
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String inputStr = "123456";
		System.err.println("原文:\n" + inputStr);

		byte[] inputData = inputStr.getBytes();
		String code = encryptBASE64(inputData);

		System.err.println("BASE64加密后:\n" + code);

		byte[] output = decryptBASE64(code);

		String outputStr = new String(output);

		System.err.println("BASE64解密后:\n" + outputStr);

	}

}
