package com.vanke.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author liuax01
 * @date 2018/3/18 12:06
 */
public class UserPasswordTest {

	@Test
	public void test生成密码(){
		System.out.println(DigestUtils.md5Hex("adminadmin123"));
	}
}
