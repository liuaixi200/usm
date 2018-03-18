<%
	String BASE_PATH = "/";
    String path = request.getContextPath();

	String scheme = request.getHeader("X-Forwarded-Proto");
	if(null == scheme || "".equals(scheme)){
		scheme = request.getScheme();
	}
    String CONTEXT = scheme + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path;
	request.setAttribute("CONTEXT",CONTEXT);
%>
<%@ taglib prefix="vk" uri="http://www.vanke.cn/vk"%>