package com.lwx.usm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	private Log LOG = LogFactory.getLog(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//记录日志,继续抛出异常
		LOG.error(ex.getMessage(),ex);
		throw new RuntimeException(ex);
	}

}
