<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
	<script src="js/jquery.json.min.js?version=${version}" type="text/javascript"></script>
	<script src="js/jquery.cookie.js?version=${version}" type="text/javascript"></script>
	<script src="js/common.js?version=${version}" type="text/javascript"></script>
	<script src="js/index/login.js?version=${version}" type="text/javascript"></script>
	<title>登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>USM管理后台</title>
        <style>
		body{
			/*background-image: url(images/banner-01.jpg);
			filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/banner-01.jpg', sizingMethod='scale');
			background-repeat: no-repeat;
			background-size: 100%;
			background-color: #AED5F2;*/
			margin:0;
			color:#333;
			
		}
		.bgimg{
			width:100%;
  			height:100%;
			z-index:1;
		}
		h1{
			padding: 0;
			margin: 0;
			font-weight: normal;
			font-size:28px;
		}
		
		a:-webkit-any-link{
			color: initial;}
			
		a:link,a:active,a:hover,a:visited{
			color: initial;}
			
		#header{
			height: 74px;
			line-height: 74px;
			background-color:#fff;
		}
		#header .logo{
			width:960px;
			margin:0 auto;
			padding-left:80px;
			background:url(images/icon-logo.jpg) no-repeat;
		}
		.login-box{
			width:350px;
			height:340px;
			background-color:#fff;
			box-shadow: 0 0 16px #999;
			position: absolute;
			left: 55%;
			top: 25%;
			border-radius: 4px;
		}
		.login-box .title{
			font-size: 32px;
			line-height: 72px;
			text-align: center;
			border-bottom: 1px solid #dedede;
		}
		.login-box .login-content{
			padding: 25px;
		}
		.login-box .login-title{
			line-height: 72px;
			border-bottom: 1px solid #dedede;
			text-align: center;
			font-size: 28px;
		}
		.login-box .login-content .login-item{
			float: left;
			width: 100%;
			box-sizing: border-box;
			line-height:32px;
			padding:2px 0;
			border: 1px solid #dedede;
			margin: 8px 0;
		}
		.login-item:before{
			content:'';
			float:left;
			width:36px;
			height:32px;
		}
		.login-item input{
			height: 32px;
			line-height: 32px;
			border: 0;
			float: right;
			margin: 0 5px;
			width: 190px;
			outline:none;
		}
		.login-item.username:before{
			background:url(images/icon-user.jpg) no-repeat center;
		}
		.login-item.password:before{
			background:url(images/icon-lock.jpg) no-repeat center;
		}
		.login-item.verifycode:before{
			background:url(images/icon-safe.jpg) no-repeat center;
		}
		.login-item.verifycode{
			width: 220px!important;}
		.login-box .forgetPwd{
			color:#3BA9F6;
		}
		.login-box .forgetPwd:after{
			content:'';
			width:24px;
			height:24px;
			float:right;
			background:url(images/icon-help.jpg) no-repeat;
			margin-left: 4px;
		}
		#verifycode{
			width: 110px;}
		#verifycode-img{
			width: 120px;
			margin: 8px 0 8px 10px;
		}
		.login-btn{
			width: 100%;
			height: 40px;
			line-height: 40px;
			background: #F6C53B;
			border: none;
			color: #fff;
			margin-top: 30px;
			font-size: 18px;
			cursor:pointer;}
		.forgetPwd{
			color: #3BA9F6;
			float: right;
			margin-top: 16px;
		}
	</style>
</head>
<body>
	<img src="images/banner-01.jpg" class="bgimg"/>
	<div class="login-box">
		<div class="login-title">登录</div>
		<form class="login-content" id="loginfrm">
			<div class="login-item username"><label for="username">用户名</label><input  type="text" name="loginName" id="username"/></div>

			<div class="login-item password"><label for="password">密码</label><input  type="password" name="password" id="password"/></div>
			<div id="passwordtip" style="color:red;width:100%;height:20px;float:left;"></div>
			<!-- <div class="login-item verifycode"><label for="verifycode">验证码</label><input type="text" id="verifycode"/></div> -->
			<!-- <img src="./icon-verifycode.jpg" alt="验证码" id="verifycode-img"/> -->
			<input type="button" class="login-btn" id="login-btn" value="登录"></input>
			<!-- <a href="" class="forgetPwd">忘记密码</a> -->
		</form>
	</div>
</body>
</html>
