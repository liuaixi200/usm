package com.vanke.controller;

import org.junit.Test;

import com.lwx.usm.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class HeadTest {

	@Test
	public void test(){
		String url = "http://127.0.0.1:8080/mea/demo/check";
		String msg = HttpUtil.httpPost4Body(url, "");
		System.out.println(msg);
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");


	}
}
