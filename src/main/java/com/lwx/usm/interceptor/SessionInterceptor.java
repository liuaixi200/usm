package com.lwx.usm.interceptor;

import com.lwx.usm.model.SysUser;
import com.lwx.usm.service.LoginSessionService;
import com.lwx.usm.utils.Constant;
import com.lwx.usm.utils.CookieUtils;
import com.lwx.usm.utils.NetworkUtil;
import com.lwx.usm.utils.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	
    private String[] doNotCheckUrl;
    
    private Log log = LogFactory.getLog(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		//打印客户端真实IP
		log.info("客户端真实IP："+ NetworkUtil.getIpAddress(request));
		log.info("当前处理请求:"+url);
		log.info("当前处理方法:"+handler.toString());

		if(null != doNotCheckUrl){
			if(!PatternMatchUtils.simpleMatch(doNotCheckUrl, uri)){
				String jessionId = CookieUtils.getJessionId(request);
				HttpSession session = request.getSession();
				SysUser user = (SysUser)session.getAttribute("cloudUser");
				if(user == null){
					//打印
					
					log.info("*******不存在jessionId["+jessionId+"]***************");
					// ajax
					if(StringUtils.equals("XMLHttpRequest", request.getHeader("X-Requested-With"))){
						response.setStatus(909);
						response.getWriter().write("请重新登陆");
						return false;
					} else {
						StringBuilder sb = new StringBuilder();
						String backPath = getBasePath(request);
						sb.append("<html>");
						sb.append("<body>");
						sb.append("<div><script>top.location.href='"+backPath+"/login';</script></div>");
						sb.append("</body>");
						sb.append("</html>");
						response.getWriter().write(sb.toString());
						return false;
					}
				} else {
					//更新SESSION
					LoginSessionService service = SpringContextUtil.getBean("loginSessionServiceImpl");
					service.updateSessionTime(jessionId);
				}
			}
		}
		
		// 是否需要校验要限
		if(handler instanceof HandlerMethod){
			HandlerMethod handle = (HandlerMethod)handler;
			ButtonIntercept an = handle.getMethodAnnotation(ButtonIntercept.class);
			if(an != null){
				String btnId = an.value();
				//用户是否拥有该权限
				HttpSession session = request.getSession();
				List<String> list = (List<String>)session.getAttribute(Constant.SESSION_FUNC_BTN);
				if(null != list && !list.contains(btnId)){
					SysUser user = (SysUser)session.getAttribute("cloudUser");
					log.error("当前用户["+user.getUserName()+"]没有该功能权限["+btnId+"]");
					if(StringUtils.equals("XMLHttpRequest", request.getHeader("X-Requested-With"))){
						response.setStatus(908);
						response.getWriter().write("权限不足");
						return false;
					} else {
						StringBuilder sb = new StringBuilder();
						sb.append("<html>");
						sb.append("<body>");
						sb.append("<div style='color:red'>权限不足</div>");
						sb.append("</body>");
						sb.append("</html>");
						response.getWriter().write(sb.toString());
						return false;
					}
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
	
	private static String getBasePath(HttpServletRequest request){
		
		String path = request.getContextPath();
		//添加协议，有值是SLB过来的
		String scheme = request.getHeader("X-Forwarded-Proto");
		if(StringUtils.isEmpty(scheme)){
			scheme = request.getScheme();
		}
		String basePath = scheme + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		return basePath;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

		super.postHandle(request, response, handler, modelAndView);
	}

	public String[] getDoNotCheckUrl() {
		return doNotCheckUrl;
	}

	public void setDoNotCheckUrl(String[] doNotCheckUrl) {
		this.doNotCheckUrl = doNotCheckUrl;
	}

}
