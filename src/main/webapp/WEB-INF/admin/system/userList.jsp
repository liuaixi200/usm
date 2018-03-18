<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
	<title>用户管理</title>

</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
		<div  data-options="region:'north'"  style="height: 65px" title="查询条件">
			<form id="queryForm">
				<table class="tabForm datagrid-toolbar" style="height:100%;width:100%">
					<tr>
						<th class="r" width="60">&nbsp;&nbsp;关键字&nbsp;</th>
						<td width="100">
							<input name="userName" class="easyui-textbox" value="" prompt="帐号/姓名" id="userName" />
						</td>
						<th class="r" width="70">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态&nbsp;</th>
						<td width="100">
							<select class="easyui-combobox" name="status" id="status" style="width:100px;" data-options="panelHeight:'auto'">
        						<option value="">全部</option>
        						<option value="1">可用</option>
        						<option value="2">冻结</option>
        					</select>	
        				</td>
						<td align="left" >
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-search" id="dategridSearch">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						   <a href="javascript:void(0);" class="easyui-linkbutton" onClick="$('#queryForm').form('clear');" iconCls="icon-redo" id="doEmpty">清空</a>
							
						</td>
					</tr>
					
				</table>
			</form>
		</div>

<div data-options="region:'center',border:false">
	<!-- 查询列表 -->
	<table id="userDataGrid"></table>
</div>

<!-- 新增用户窗口 -->
	<div id="mydialog" title="用户新增" modal=true  draggable=false class="easyui-dialog" closed=true style="width:300px;">
		<form id="myform"  method="post">
	 		<input type="hidden" name="userId" value="" />
	 		<table>
	    					<tr>
	    						<td>账号:</td>
	    						<td><input type="text" name="userName" style="width:160px;"  class="easyui-textbox"  required=true  missingMessage="账号必填!"  value="" /></td>
	    					</tr>
	    					<tr>
	    						<td>密码:</td>
	    						<td><input type="password" id="password" name="password" style="width:160px;"  class="easyui-textbox"  required=true  missingMessage="密码必填!" value="" /></td>
	    					</tr>
	    					<tr>
	    						<td>姓名:</td>
	    						<td><input id="contactName" type="text" style="width:160px;"  class="easyui-textbox"  required=true  missingMessage="姓名必填!" name="contactName" value="" /></td>
	    					</tr>
	    					<!-- 
							<tr>
								<td>城市公司:</td>
								<td>
		    						<input class="easyui-combobox" id="cityCompany" name="cityCompany" data-options="panelHeight:200,valueField:'orgId',textField:'orgName',url:contextPath+'/orgInfo/getCityCompanyList4Auth',
			    						onSelect: function(rec){    
	            								var url = contextPath+'/orgInfo/getProjectList4Auth?orgId='+rec.orgId;
	            								$('#project').combobox('clear');
	           									 $('#project').combobox('reload', url);
	      								}
		    						"/>
							</tr>
							<tr>
								<td>项目:</td>
								<td>
		    						<input class="easyui-combobox" id="project" name="projectCode" data-options="valueField:'orgId',textField:'orgName'" />
							</tr>
	    					<tr>
	    						<td>性别:</td>
	    						<td>
	    							男<input type="radio" checked="checked" name="sex" value="M" />
	    							女<input type="radio" name="sex" value="F" />
	    						</td>
	    					</tr>    				
	    					<!--  
	    					<tr>
	    						<td>出生日期:</td>
	    						<td><input id="birthday" editable="false"  style="width:160px;" class="easyui-datebox"  name="birthday" value="" /></td>
	    					</tr>
	    					-->
	    					<tr>
	    						<td>个人电话:</td>
	    						<td>
	    							<input name="tel" style="width:160px;"  class="easyui-textbox"  name="tel"  value="" data-options="validType:'mobile'" />
	    						</td>
	    					</tr>
	    					<tr>
	    						<td>邮箱:</td>
	    						<td><input id="mail" style="width:160px;" class="easyui-textbox"  name="mail" required=true missingMessage="邮箱必填!" data-options="validType:'email'"/></td>
	    					</tr>
	    					<tr>
	    						<td>身份证号:</td>
	    						<td><input id="certNo" style="width:160px;" class="easyui-textbox"  name="certNo" data-options="validType:'idcard'"/></td>
	    					</tr>

	    					<tr align="center">
	    						<td colspan="2">
	    							<a id="btn_userCommit" class="easyui-linkbutton">确定</a>
	    							<a id="btn_userClose" class="easyui-linkbutton">关闭</a>
	    						</td>
	    					</tr>   					 					    					    					    					    					    					    					    					
	    	</table>
	 	</form>
	</div>
	
	<!-- 编辑用户窗口 -->
	<div id="editWindow" class="easyui-window" title="用户编辑" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false"  style="width:300px;">
		<div style="padding:10px 20px 5px 20px">
			<form id="editForm"  method="post">
		 		<input type="hidden" name="userId" value="" />
		 		<table>
		    		<tr>
		    			<td>账号:</td>
		    			<td><input type="text" readonly name="userName" style="width:160px;" class="easyui-textbox" required=true  missingMessage="账号必填!"  value="" /></td>
		    		</tr>		    	
		    		<tr>
		    			<td>姓名:</td>
		    			<td><input id="contactName" type="text" style="width:160px;" class="easyui-textbox" required=true  missingMessage="姓名必填!" name="contactName" value="" /></td>
		    		</tr>
		    		<!--  <tr>
								<td>城市公司:</td>
								<td>
		    						<input class="easyui-combobox" id="cityCompany2" name="cityCompany" data-options="panelHeight:200,valueField:'orgId',textField:'orgName',url:contextPath+'/orgInfo/getCityCompanyList4Auth',
			    						onSelect: function(rec){    
	            								var url = contextPath+'/orgInfo/getProjectList4Auth?orgId='+rec.orgId;
	            			
	            								$('#project2').combobox('clear');
	           									 $('#project2').combobox('reload', url);
	      								}
		    						"/>
							</tr>
							<tr>
								<td>项目:</td>
								<td>
		    						<input class="easyui-combobox" id="project2" name="projectCode" data-options="valueField:'orgId',textField:'orgName',onLoadSuccess:function(){setProjectCode()}" />
							</tr>
		    		<tr>
		    			<td>性别:</td>
		    			<td>
		    				男<input type="radio" checked="checked" name="sex" value="M" />
		    				女<input type="radio" name="sex" value="F" />
		    			</td>
		    		</tr>    				
		    		<!--  
		    		<tr>
		    			<td>出生日期:</td>
		    			<td><input id="birthday" editable="false"  style="width:160px;" class="easyui-datebox"  name="birthday" value="" /></td>
		    		</tr>
		    		-->
		    		<tr>
		    			<td>个人电话:</td>
		    			<td>
		    				<input name="tel" style="width:160px;"  type="text" name="tel"  value="" class="easyui-textbox" data-options="validType:'mobile'" />
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>邮箱:</td>
		    			<td><input id="mail" style="width:160px;" class="easyui-textbox"  type="text" name="mail" value="" data-options="required:true,validType:'email'" /></td>
		    		</tr>
		    		<tr>
	    				<td>身份证号:</td>
	    				<td><input id="certNo" style="width:160px;" class="easyui-textbox"  name="certNo" data-options="validType:'idcard'"/></td>
	    			</tr>
		    		<!-- <tr>
		    			<td>住址:</td>
		    			<td><input type="text" id="address" style="width:160px;"  name="address"  value="" /></td>
		    		</tr>  -->
		    		  					 					    					    					    					    					    					    					    					
		    	</table>
		    	<div style="text-align:center;padding:5px">
	            	<a id="btn_editCommit" class="easyui-linkbutton" onclick="editCommit();">确定</a>
	            	<a id="btn_editClose" class="easyui-linkbutton" onclick="$('#editWindow').window('close');">关闭</a>
	        	</div>
		 	</form>
	 	</div>
	</div> 
	
	<!-- 用户角色编辑窗口 -->
	<div id="assignWindow" class="easyui-window" title="用户角色分配" data-options="onBeforeClose:closeAssign, closed:true,  border:false, minimizable:false, maximizable:false, collapsible:false, modal:true" style="width:500px;height:350px;padding:5px;">
	        <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'north',split:true" style="height:33px">
	            	<form id="assignForm">
						<table class="tabForm datagrid-toolbar" style="height:100%;width:100%">
							<tr>
								<th class="r" width="60">&nbsp;&nbsp;用户：&nbsp;&nbsp;</th>
								<td width="100">
									<input type="hidden" name="assignUserId" id="assignUserId" value="" />
									<input name="assignUserName" value="" id="assignUserName" readonly />
								</td>
							</tr>					
						</table>
					</form>	            	
	            </div>	            
	            <div data-options="region:'center'">	       
	                <table id="roleDataGrid"></table>
	            </div>
	            <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="#" onclick="saveUserRole()" style="width:80px">保存</a>
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="#" onclick="$('#assignWindow').window('close');" style="width:80px">取消</a>
	            </div>
	        </div>
		</div> 
	</div>
</div>

<!-- 新增数据权限 -->
<div id="assignDataWindow" class="easyui-window" title="用户数据权限分配" data-options="closed:true,  border:false, minimizable:false, maximizable:false, collapsible:false, modal:true" style="width:500px;height:350px;padding:5px;">
	 <input type="hidden" name="userId" value=""/>
	 <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'north',split:false,border:0" >
	            	<span style="margin-right:20px" >商户:</span><input  class="easyui-textbox" name="merId" id="merId"/>
	            	<a class="easyui-linkbutton" plain=true onclick="queryMerTree();">搜索</a>
	            </div>
	            <div data-options="region:'center'">
	              <ul id="orgTree"></ul>
	              
	            </div>
	            <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="#" onclick="saveUserData()" style="width:80px">保存</a>
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="#" onclick="$('#assignDataWindow').window('close');" style="width:80px">取消</a>
	            </div>
	        </div>
		</div> 
	</div>
<script type="text/javascript">
var dg;
var flag ;		//undefined 判断新增和修改方法 
$(function(){ 
	dg=$('#userDataGrid').datagrid({  
	method: "post",
    url:getContextPath()+'/cuser/queryUserInfo', 
    fit : true,
	fitColumns : false,
	pagination :true,
	border : false,
	queryParams:$('#queryForm').serializeObject(),
	striped:true,
	rownumbers:true,
	checkOnSelect:false,
	selectOnCheck:false,
	singleSelect:true,

    columns:[[
		{field:'userId',title:'选择',checkbox:true,align:'center',halign:'center'},
        {field:'contactName',title:'姓名',width:80,align:'center',halign:'center',
        	formatter : function(value, row, index) {
        		return "<vk:btn btncode="101010_XGYH"><a style=\"text-decoration:underline;color:blue;\"  href=\"#\" onClick=\"editUser('"+row.userId+"')\"></vk:btn>"+value+"<vk:btn btncode="101010_XGYH"></a></vk:btn>";
        	}
        },
        {field:'userName',title:'账号',width:60,align:'center',halign:'center'},          
        /**{field:'sex',title:'性别',align:'center',halign:'center',
        	formatter : function(value, row, index) {
        	    if(value!=null&&value!="")
       			return value=='M'?'男':'女';
        	}
        },*/
        {field:'tel',title:'电话',width:120,align:'center',halign:'center'},
        {field:'mail',title:'Email',width:140,align:'center',halign:'center'},
        {field:'certNo',title:'身份证号',width:160,align:'center',halign:'center'},
        {field:'createUser',title:'创建人',width:80,align:'center',halign:'center'},
        {field:'createTime',title:'创建日期',width:120,align:'center',halign:'center'},
        {field:'updateTime',title:'修改日期',width:120,align:'center',halign:'center'},
        {field:'status',title:'状态',width:40,align:'center',halign:'center',
        	formatter : function(value, row, index) {
        	    if(value!=null&&value!="")
       			return value=='1'?'可用':'冻结';
        	}
        },
        {field:'remark',title:'操作',width:180,align:'center',halign:'center',
        	formatter : function(value, row, index) {
        		var display = "";
        		if(row.userId!=null&&row.userId!=""&&row.userId != '${cloudUser.userId}'&&row.type!='1')
        		{        					
        			if(row.status == '1')
        			{
        				display += "<vk:btn btncode="101010_MMCZ"><a href=\"#\" class=\"resetcls\"  onclick=\"resetPass('"+row.userId+"')\" iconCls=\"icon-no\">密码重置</a>&nbsp;&nbsp;</vk:btn>";
        				//display += "<a href='#' class='freezecls' onclick='editRow("+row.userId+")' iconCls='icon-no'>冻结</a>&nbsp;&nbsp;";
        				display += "<vk:btn btncode="101010_FPJS"><a href=\"#\" class=\"assigncls\"   onclick=\"assignRole('"+index+"')\" iconCls=\"icon-no\">分配角色</a>&nbsp;&nbsp;</vk:btn>";
        			}
        			else
        			{
        				//display += "<a href='#' class='unfreezecls' onclick='editRow("+row.userId+")' iconCls='icon-ok'>解冻</a>&nbsp;&nbsp;";
        			}	
        		}	
        		//var da = "<a href=\"#\" class=\"assignData\" onclick=\"assignData('"+index+"')\" iconCls=\"icon-no\">分配数据权限</a>";
       			return display;
        	}
        }
    ]],
	toolbar:[<vk:btn btncode="101010_XZYH">{
		text:"新增",
		iconCls:"icon-add",
		handler:function(){
			$('#mydialog').dialog({
					title:'新增用户' 
			});
			
			$('#myform').form('clear');
			$('#myform').find(":input[name='sex'][value='M']").prop("checked","true");

			$('#mydialog').dialog('open');
		} 
	},'-',</vk:btn><vk:btn btncode="101010_DJYH">{
		text:"冻结",
		iconCls:"icon-no",
		handler:freezeUser
	},'-',</vk:btn><vk:btn btncode="101010_JDYH">{
		text:"解冻",
		iconCls:"icon-ok",
		handler:unFreezeUser
	},'-',</vk:btn><vk:btn btncode="101010_FPSJQX">{
		text:"分配数据权限",
		iconCls:"icon-hamburg-suppliers",
		handler:assignData
	},</vk:btn>{}],
	onLoadSuccess:function(data){
		console.log(data);
		var rows = data.rows;
		if(rows && rows.length > 0){
			for(var i=0;i<rows.length;i++){
				var row = rows[i];
				if(row.type == '1' || row.userId == '${cloudUser.userId}'){
					$("#userDataGrid").datagrid("getPanel").find(".datagrid-view2 .datagrid-body")
					.find("tr[datagrid-row-index="+i+"]").find("td:first").find("input").attr("disabled",true).remove();
				}
			}
		}
		$('.resetcls').linkbutton({text:'密码重置',iconCls:'icon-edit'}); 
		$('.assigncls').linkbutton({text:'分配角色',iconCls:'icon-hamburg-suppliers'});
	}
	});
	
	/**
	 *  提交表单方法
	 */
	$('#btn_userCommit').click(function(){
			if($('#myform').form('validate')){
				$.ajax({
					type: 'post' ,
					contentType:"application/json",  
					url: getContextPath()+'/cuser/addUserInfo',
					cache:false ,
					data:$.toJSON($('#myform').serializeObject()),
					dataType:'json' ,
					success:function(result){
					    if(result.status=='repeat'){
					         $.messager.alert('提示',result.rows,'error');
					      }else{
					        //新增成功
							//1 关闭窗口
							$('#mydialog').dialog('close');
							//2刷新datagrid 
							$('#userDataGrid').datagrid('reload');
							//3 提示信息
							$.messager.show({
								title:result.status , 
								msg:result.rows
							});
						}
					} 
				});
			} else {
				$.messager.show({
					title:'提示信息!' ,
					msg:'数据验证不通过,不能保存!'
				});
			}
	});

	/**
	 * 关闭窗口方法
	 */
	$('#btn_userClose').click(function(){
		$('#mydialog').dialog('close');
	});
	
	/**
 	 * 点击查询按钮
 	 */
	$('#dategridSearch').click(function(){
		$('#userDataGrid').datagrid("load",$('#queryForm').serializeObject());
		$('#userDataGrid').datagrid("clearChecked");	
	});
	
});

function editUser(userId)
{	
	jQuery.ajax({  
	    	type : 'POST',  
	        contentType:"text/plain",  
	        url : contextPath+'/cuser/findByPrimaryKey',  
	        data :   userId,//JSON.stringify(userId) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{      		
	        		$('#editForm').form('load', data.cloudUser);
	        		// 处理城市公司与项目
	        		if($.trim(data.cloudUser.cityCompany) != ''){
	        			$("#cityCompany2").combobox("setValue",data.cloudUser.cityCompany);
	        			initProject = true;
	        			$("#project2").combobox("reload",contextPath+'/orgInfo/getProjectList4Auth?orgId='+data.cloudUser.cityCompany);
	        			projectCode = data.cloudUser.projectCode;
	        		} else {
	        			$("#cityCompany2").combobox("setValue","");
	        			$("#project2").combobox("setValue","");
	        		}
	        		
	        		$('#editWindow').window('open');	        		
	        	}
	        	else if(state=='fail')
	        	{
	        		//alert("操作失败,请检查!");
	        		$.messager.alert('失败','操作失败,请检查!','error');
	        	}	        	
	        } 
	    });
}

//修改用户时，延迟加载项目使用
var initProject = false;
var projectCode = "";
function setProjectCode(){
	if(initProject){
		$("#project2").combobox("setValue",projectCode);
		initProject =false;
	}
}

function editCommit()
{
	if($('#editForm').form('validate'))
	{
		jQuery.ajax({  
	    	type : 'POST',  
	        contentType:"application/json",  
	        url : contextPath+'/cuser/updateUser',  
	        data :   JSON.stringify($("#editForm").serializeObject()) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{        		
	        		$('#editWindow').window('close');
	        		$('#editForm').form('clear');
	        		$('#userDataGrid').datagrid("reload");
	        		$.messager.show({
		                title:'成功',
		                msg:'操作成功',
		                timeout:2000
		            });
	        	}
	        	else if(state=='fail')
	        	{
	        		//alert("操作失败,请检查!");
	        		$.messager.alert('失败','操作失败,请检查!','error');
	        	}	        	
	        }
	    });
	}
}

function assignRole(userId)
{
	
}

function resetPass(userId)
{
	jQuery.ajax({  
    	type : 'POST',  
        contentType:"text/plain",  
        url : contextPath+'/cuser/resetPassword',  
        data :   userId,//JSON.stringify(userId) ,
        dataType : 'json',
        success : function(data) {
        	var state = data.state;
        	if(state=='succ')
        	{      		
        		$.messager.show({
	                title:'成功',
	                msg:data.msg,
	                timeout:2000
	            });
        	}
        	else if(state=='fail')
        	{
        		//alert("操作失败,请检查!");
        		$.messager.alert('失败','操作失败,请检查!','error');
        	}	        	
        }
    });
}

function freezeUser()
{	
	var checkedItems = new Object();
	checkedItems = $('#userDataGrid').datagrid('getChecked');
	var userIds = [];
	var validFlag = true;
	$.each(checkedItems, function(index, item){
		if(item.type != '1'){
			userIds.push(item.userId);
			if(item.status == '2')
			{
				validFlag = false;
			}	
		}
	
	});
	
	if(!validFlag)
	{
		$.messager.alert('请检查','已冻结的用户无法再次冻结！','warning');
		return;
	}
	else if(userIds.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}
	else
	{
		jQuery.ajax({  
	    	type : 'POST',  
	        contentType:"application/json",  
	        url : contextPath+'/cuser/freezeUser',  
	        data :   JSON.stringify(userIds) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{      		
	        		$('#userDataGrid').datagrid("reload");
	        		//$('#userDataGrid').datagrid("clearChecked");
	        		$.messager.show({
		                title:'成功',
		                msg:'操作成功',
		                timeout:2000
		            });
	        	}
	        	else if(state=='fail')
	        	{
	        		//alert("操作失败,请检查!");
	        		$.messager.alert('失败','操作失败,请检查!','error');
	        	}	        	
	        }
	    });
	}	
}

function unFreezeUser()
{
	var checkedItems = new Object();
	checkedItems = $('#userDataGrid').datagrid('getChecked');
	var userIds = [];
	var validFlag = true;
	$.each(checkedItems, function(index, item){
		if(item.type != '1'){
			userIds.push(item.userId);
			if(item.status == '1')
			{
				validFlag = false;
			}	
		}
		
	});
	
	if(!validFlag)
	{
		$.messager.alert('请检查','未冻结的用户无法解冻！','warning');
		return;
	}
	else if(userIds.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}
	else
	{
		jQuery.ajax({  
	    	type : 'POST',  
	        contentType:"application/json",  
	        url : contextPath+'/cuser/unFreezeUser',  
	        data :   JSON.stringify(userIds) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{      		
	        		$('#userDataGrid').datagrid("reload");
	        		//$('#userDataGrid').datagrid("clearChecked");	        		
	        		$.messager.show({
		                title:'成功',
		                msg:'操作成功',
		                timeout:2000
		            });
	        	}
	        	else if(state=='fail')
	        	{
	        		//alert("操作失败,请检查!");
	        		$.messager.alert('失败','操作失败,请检查!','error');
	        	}	        	
	        }
	    });
	}
}

function assignRole(index)
{
	var row = $('#userDataGrid').datagrid("getData").rows[index];
	var userId = row.userId;
	var userName = row.userName;
	var contactName = row.contactName;
	jQuery.ajax({  
    	type : 'POST',  
        contentType:"text/plain",  
        url : contextPath+'/crole/queryUserRole', 
        data : userId,
        dataType : 'json',
        success : function(data) {
        	var state = data.state;
        	if(state=='succ')
        	{        		
        		$('#roleDataGrid').datagrid({  
					data:data.rows,
        			fit : true,
        			fitColumns : true,
        			border : false,
        			idField : 'roleId',
        			striped:true,
        			rownumbers:true,
        			checkOnSelect:false,
        			selectOnCheck:false,
        			singleSelect:true,
        			onLoadSuccess:function(data)
        			{
        				var rows = data.rows;
        				$.each(rows,function(index,row)
        			    {
        					if(row.checked != null )
        	                {
        						$('#roleDataGrid').datagrid('checkRow', index);
        	                }
        			    })
        			},
        			columns:[[
        				{field:'roleId',title:'选择',checkbox:true,align:'center',halign:'center'},
        				{field:'roleNo',title:'编码',width:80,align:'center',halign:'center'},
        				{field:'roleName',title:'名称',width:100,align:'center',halign:'center'}          
        			]]
        		});       		
        	}
        	else if(state=='fail')
        	{
        		//alert("操作失败,请检查!");
        		$.messager.alert('失败','加载角色列表失败,请检查!','error');
        	}	        	
        }
    });
	
	//$('#assignRoleId').val(roleId);
	//$('#assignRoleNo').val(roleNo);
	//$('#assignRoleName').val(roleName);
	$('#assignForm').form('clear');
	$('#assignForm').form('load',{'assignUserId':userId,'assignUserName':contactName});
	$('#assignWindow').window('open');
}

function saveUserRole()
{
	var userId = $('#assignUserId').val();
	checkedItems = $('#roleDataGrid').datagrid('getChecked');
	var userRoles = [];
	
	$.each(checkedItems, function(index, item){
		var userRole = new Object();
		userRole.roleId = item.roleId;
		userRole.checked = userId;			
		userRoles.push(userRole);		
	});
	
	if(userRoles.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}
	else
	{
		jQuery.ajax({  
	    	type : 'POST',  
	        contentType:"application/json",  
	        url : contextPath+'/crole/saveUserRole', 
	        data : JSON.stringify(userRoles) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{        		
	        		$('#assignWindow').window('close');
					$.messager.show({
						title:'成功',
						msg:'操作成功',
						timeout:2000
					});
	        	}
	        	else if(state=='fail')
	        	{
	        		//alert("操作失败,请检查!");
	        		$.messager.alert('失败','加载菜单树失败,请检查!','error');
	        	}	        	
	        }
	    });
	}	
}

function closeAssign()
{
	$('#roleDataGrid').datagrid("clearChecked");
}

function assignData(){
	
	var checkedItems = $('#userDataGrid').datagrid('getChecked');
	if(checkedItems && checkedItems.length == 0){
		$.messager.alert('请检查','您还没有选择任何内容','warning');
	} else if(checkedItems && checkedItems.length > 1){
		$.messager.alert('请检查','请选择一行数据！','warning');
	} else {
		var item = checkedItems[0];
		if(item.type == '1'){
			$.messager.alert('请检查','请选择一行数据！','warning');
			return;
		}
		$("#assignDataWindow").find("input[name='userId']").val(item.userId);
		$.ajax({
			type : 'POST',
			 url : contextPath+'/orgInfo/getUserData',
			 data : {userId:checkedItems[0].userId},
			 success : function(data) {
		        	var state = data.state;
		        	if(state=='succ')
		        	{ 
		        		$("#orgTree").tree({
		        			checkbox:true,
		        			data: [{text:'万科物业',state:'open',children:data.nodes
		        		}]
		        		});
		        		$('#orgTree').tree('expandAll');
		        	}
		        	else if(state=='fail')
		        	{
		        		//alert("操作失败,请检查!");
		        		$.messager.alert('失败','操作失败,请检查!','error');
		        	}	        	
			 }
		});
		$('#assignDataWindow').window('open');
	}

	

	
}

function queryMerTree(){
	var merName = $("#merId").val();
	$('#orgTree').tree('doFilter',merName);
}

function saveUserData(){
	//获取所有已选择的节点
	var nodes = $('#orgTree').tree('getChecked');
	var nodes2 = $('#orgTree').tree('getChecked','indeterminate');
	var dn = [];
	var userId = $("#assignDataWindow").find("input[name='userId']").val();
	
	for(var i=0;i<nodes.length;i++){
		var node = nodes[i];
		console.log(node);
		dn.push({userId:userId,authType:node.type,authCode:node.id});
	}
	for(var i=0;i<nodes2.length;i++){
		var node = nodes2[i];

		dn.push({userId:userId,authType:node.type,authCode:node.id});
	}
	if(dn.length == 0){
		dn.push({userId:userId,authType:'del'});
	}
	$.ajax({
		type : 'POST',
		 url : contextPath+'/orgInfo/assignData',
		 data : JSON.stringify(dn),
		 contentType:"application/json",  
		 dataType : "json",
		 success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{ 
	        		$('#assignDataWindow').window('close');
					$.messager.show({
						title:'成功',
						msg:'操作成功',
						timeout:2000
					});
	        		
	        	}
	        	else if(state=='fail')
	        	{
	        		//alert("操作失败,请检查!");
	        		$.messager.alert('失败',data.msg,'error');
	        	}	        	
		 }
	});
}


</script>
</body>
</html>