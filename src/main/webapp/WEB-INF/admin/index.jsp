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
    <script src="js/jquery.cookie.js?version=${version}" type="text/javascript"></script>
	<script src='js/outlook2.js?version=${version}' type="text/javascript"></script>
	<script src='js/index/updatePassword.js?version=${version}' type="text/javascript"></script>
	<script src="js/common/easyui-ext.js?version=${version}" type="text/javascript"></script>
	<title>用户管理系统</title>
    <script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存 
		var _menus = {"menus":[]};
		
        
		/*
		 *获取当前用户
		 */
		function getUser() 
		{
			jQuery.ajax({  
		    	type : 'POST',  
		        contentType:"application/json",  
		        url : contextPath+'/cuser/getCurrentUser',  
		        data :  "", 
		        dataType : 'json',
		        success : function(data) {
		        	var state = data.state;
		        	if(state=='succ')
		        	{
		        		$("#currentUser").text(data.cloudUser.contactName);
		        	}
		        	else if(state=='fail')
		        	{
		        		openReLoginWin();
		        	}	        	
		        }
		    });
		}
		
		/*
		 *初始化菜单树
		 */
		function initMenuTree() 
		{
			jQuery.ajax({  
		    	type : 'POST',  
		        contentType:"application/json",  
		        url : contextPath+'/cmenu/queryUserMenuTree',  
		        data :  "", 
		        dataType : 'json',
		        success : function(data) {
		        	var state = data.state;
		        	if(state=='succ')
		        	{
		        		_menus = data.menuTree;
		        		
		        		InitLeftMenu();
		            	tabClose();
		            	tabCloseEven();
		        	}
		        	else if(state=='fail')
		        	{
		        		alert("初始化菜单失败，请检查!");
		        	}	        	
		        }
		    });
		}
		
		/*
		 * 关闭标签
		 */
		function closeTab()
		{
			var tab = $('#tabs').tabs('getSelected');
			if (tab)
			{
				var index = $('#tabs').tabs('getTabIndex', tab);
				$('#tabs').tabs('close', index);
			}
        }
		
		/*
		 * 刷新标签
		 */
        function reloadTab()
        {
            //var tab = $('#tabs').tabs('getSelected');          
            //var href = tab.panel('options').href;//$(tab.panel('options').content).attr('src');
            //alert(href);
            //$('#tabs').tabs('getTab', index).panel('refresh');
            var href = $('#tabs').tabs('getSelected').panel('options').href;
			if (href) 
			{		
				var index = $('#tabs').tabs('getTabIndex', $('#tabs').tabs('getSelected'));
				$('#tabs').tabs('getTab', index).panel('refresh');
			} 
			else 
			{
				var panel = $('#tabs').tabs('getSelected').panel('panel');
				var frame = panel.find('iframe');
				try 
				{
					if (frame.length > 0) {
						for ( var i = 0; i < frame.length; i++) 
						{
							frame[i].contentWindow.document.write('');
							frame[i].contentWindow.close();
							frame[i].src = frame[i].src;
						}
						if (navigator.userAgent.indexOf("MSIE") > 0) {// IE
							try 
							{
								CollectGarbage();
							}
							catch (e) 
							{}
						}
					}
				} catch (e) {
				}
			}
        }
		
        window.onload = function(){
        	//获取当前用户
        	getUser();
        	//初始化用户菜单
        	initMenuTree(); 
        };
        
		
        $(function() {
        	
        	    	    	
        	//$('#reLogin').window({        	    
        	//    closed:true
        	//});
        	
            $('#editpass').click(function() {
                openPwdWin();
            });

            $('#btnModifyPwd').click(function() {
            	modifyPwd('index');
            })  
            
            $('#reLoginBtn').click(function() {
            	reLogin();
            })  

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登陆么?', function(r){
                    if (r) 
                    {
                    	jQuery.ajax({  
            		    	type : 'POST',  
            		        contentType:"application/json",  
            		        url : contextPath+'/login/quitLogin',  
            		        data :  "", 
            		        dataType : 'json',
            		        success : function(data) {
            		        	location.href = contextPath+'/login';
            		        },  
            		        error : function(XMLResponse) {  
            		        	location.href = contextPath+'/login';
            		        }  
            		    });
                    	
                    }
                });

            })
            
            $('#ith').combobox({
                panelHeight:100,
                onChange:function(newVal, oldVal)
                {
					var $easyuiTheme = $('#easyuiTheme');
                	var url = $easyuiTheme.attr('href');
                	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + newVal + '/easyui.css';
                	$easyuiTheme.attr('href', href);

                	var $iframe = $('iframe');
                	if ($iframe.length > 0) {
                		for (var i = 0; i < $iframe.length; i++) {
                			var ifr = $iframe[i];
                			try {             				
                				var ifrUrl = $(ifr).contents().find('#easyuiTheme').attr('href');
                				var ifrHref = ifrUrl.substring(0, ifrUrl.indexOf('themes')) + 'themes/' + newVal + '/easyui.css';
                				$(ifr).contents().find('#easyuiTheme').attr('href', ifrHref);
                			} catch (e) {
                				try {
                					//ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
                				} catch (e) {
                				}
                			}
                		}
                	}

                	//ext.cookie('easyuiTheme', themeName, { expires : 7 });               	
					//设置cookie值，并设置180天有效时间
					$.cookie('easyuiTheme', newVal, {
						expires : 180
					})
					//$.cookie('the_cookie'); // 读取 cookie 
					//$.cookie('the_cookie', 'the_value'); // 存储 cookie 
					//$.cookie('the_cookie', 'the_value', { expires: 7 }); // 存储一个带7天期限的 cookie 
					//$.cookie('the_cookie', '', { expires: -1 }); // 删除 cookie
                }
            });		
			
        });
        
    </script>
</head>

<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    		<img src="images/noscript.gif" alt='请开启脚本支持' />
		</div>
	</noscript>
	
	<!--north div-->
    <div region="north" split="false" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        color: #fff; font-family: Verdana,微软雅黑,黑体">
        <span style="float:right; padding-right:20px; display:none" class="head" >
			主题
			<select id="ith" class="easyui-combobox" name="theme">
				<option value="default">default</option>
				<option value="bootstrap">bootstrap</option>
				<option value="metro">metro</option>
				<option value="black">black</option>
				<option value="gray">gray</option>
			</select>
		</span>
        <span style="float:right; line-height:30px;padding-right:20px;" class="head">欢迎 <a href="#" id="currentUser"></a>！ <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px;line-height:30px; font-size: 16px; ">用户管理系统 &nbsp;&nbsp;版本号:${version}</span>
    </div>
    
    <!--south div-->
    <div region="south" split="false" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">  2017-2020</div>
    </div>

    <!--west div-->
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
		<div class="easyui-accordion" id="menu-accordion" fit="true" border="false">
			<!--  导航内容 -->
		</div>
    </div>
    
    <!--mainPanle div-->
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" data-options="tools:'#tab-tools'">
			
			<jsp:include page="main.jsp" />
			
			
		</div>
		<div id="tab-tools">
        	<!--  
        		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="closeTab();"></a>
        	-->
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="reloadTab();"></a>
    	</div>
		
    </div>   
    
    <!--修改密码窗口-->
    <div id="mp" class="easyui-window" title="修改密码" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false" 
    	style="width: 300px; height: 185px; padding: 5px; background: #fafafa; text-align:center">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<form id="modifyPwdForm">
	                <table cellpadding=3 style="margin:auto;">
	                    <tr>
	                        <td>旧密码</td>
	                        <td><input id="password" type="password" class="txt01" /></td>
	                    </tr>
	                    <tr>
	                        <td>新密码</td>
	                        <td><input id="newPassword" type="password" class="txt01" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码</td>
	                        <td><input id="rePassword" type="password" class="txt01" /></td>
	                    </tr>
	                </table>
				</form>
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnModifyPwd" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" > 确定</a> 
				<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="$('#mp').window('close');">取消</a>
			</div>
		</div>	
    </div>
	
	<!--测试窗口-->
	<!-- 
	<div id="testWin" class="easyui-window" data-options="title:'Inline Window',inline:true" style="width:250px;height:150px;padding:10px">
            This window stay inside its parent
    </div>
	 -->
	
	<!--tabs右键菜单-->
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>

</body>
</html>