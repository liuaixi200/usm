package com.lwx.usm.web.controller;

import com.lwx.usm.dto.ApiResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoConroller {

	private Log log = LogFactory.getLog(getClass());
	

	@RequestMapping("check")
	@ResponseBody
	public ApiResult<String> check(){
		ApiResult<String> res = new ApiResult<>();
		res.setResult("ok");
		return res;
	}
	
}
