<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<%@ include file="/WEB-INF/admin/constants.inc" %>
	<%@ include file="/WEB-INF/admin/head.inc" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=7, IE=9, IE=10, IE=11, IE=12"/>
	<script src='js/index/updatePassword.js?version=${version}' type="text/javascript"></script>
	<title>修改密码</title>
	
</head>
<body>
	<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<form id="modifyPwdForm">
	                <table cellpadding=3 style="margin:auto;">
	                    <tr>
	                        <td>旧密码</td>
	                        <td><input id="password"  type="password" class="easyui-textbox txt01" /></td>
	                    </tr>
	                    <tr>
	                        <td>新密码</td>
	                        <td><input id="newPassword" type="password" class="easyui-textbox txt01" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码</td>
	                        <td><input id="rePassword" type="password" class="easyui-textbox txt01" /></td>
	                    </tr>
	                    <tr>
	                    	<td colspan="2">
	                    	
	                    	</td>
	                    </tr>
	                </table>
				</form>
				<div>
				<a id="btnModifyPwd" class="easyui-linkbutton" style="margin-left:50%" icon="icon-ok" href="javascript:void(0)" > 确定</a> 
				</div>
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
				
			</div>
		</div>	
</body>

<script>
$(function(){
	$('#btnModifyPwd').click(function() {
		modifyPwd('login');
	})  
	
});


</script>
</html>