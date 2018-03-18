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
	<title>角色管理</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div  data-options="region:'north'"  style="height: 65px" title="查询条件" >
			<form id="queryForm">
				<table class="tabForm datagrid-toolbar" style="height:100%;width:100%">
					<tr>
						<th class="r" width="60">&nbsp;&nbsp;关键字&nbsp;</th>
						<td width="100">
							<input name="roleName"  class="easyui-textbox" prompt="编码/名称" value="" id="roleName" />
						</td>
						<th class="r" width="70">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态&nbsp;</th>
						<td width="100">
							<select class="easyui-combobox" name="validFlag" style="width:100px;" data-options="panelHeight:'auto'">
        						<option value="">全部</option>
        						<option value="Y">启用</option>
        						<option value="N">停用</option>
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
			<table id="bizDataGrid"></table>
	
			<!-- 新增窗口 -->
			<div id="addWindow" class="easyui-window" title="角色新增" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false"  style="width:400px;">
				<div style="padding:10px 20px 5px 20px">
					<form id="addForm"  method="post">
				 		<input type="hidden" name="roleId" value="" />
				 		<input type="hidden" name="type" value="" />
				 		<table>
				    		<tr>
				    			<td>编码:</td>
				    			<td><input name="roleNo" type="text" style="width:160px;" class="easyui-validatebox" required=true  missingMessage="编号必填!"  value="" /></td>
				    		</tr>		    	
				    		<tr>
				    			<td>名称:</td>
				    			<td><input name="roleName" type="text" style="width:160px;" class="easyui-validatebox" required=true  missingMessage="名称必填!"  value="" /></td>
				    		</tr>
				    		<tr>
				    			<td>状态:</td>
				    			<td>
				    				启用<input type="radio" checked name="validFlag" value="Y" />
				    				停用<input type="radio" name="validFlag" value="N" />
				    			</td>
				    		</tr>    								    		
				    		<!-- <tr>
				    			<td>图标:</td>
				    			<td>
				    				<input name="tel" style="width:160px;"  type="text" name="tel"  value="" />
				    			</td>
				    		</tr> -->
				    		<tr>
				    			<td>描述:</td>
				    			<td><input name="roleDesc" style="width:300px;" class="easyui-validatebox"  type="text" value="" /></td>
				    		</tr>	  					 					    					    					    					    					    					    					    					
				    	</table>
				    	<div style="text-align:center;padding:5px">
			            	<a id="btn_addCommit" class="easyui-linkbutton" onclick="addCommit();">新增</a>
			            	<a id="btn_addClose" class="easyui-linkbutton" onclick="$('#addWindow').window('close');">关闭</a>
			        	</div>
				 	</form>
			 	</div>
			</div>	
						 
			<!-- 编辑窗口 -->
			<div id="editWindow" class="easyui-window" title="角色编辑" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false"  style="width:400px;">
				<div style="padding:10px 20px 5px 20px">
					<form id="editForm"  method="post">
				 		<input type="hidden" name="roleId" value="" />
				 		<input type="hidden" name="type" value="" />
				 		<table>
				    		<tr>
				    			<td>编号:</td>
				    			<td><input name="roleNo" type="text"  style="width:160px;" class="easyui-validatebox" required=true  missingMessage="编号必填!"  value="" /></td>
				    		</tr>		    	
				    		<tr>
				    			<td>名称:</td>
				    			<td><input name="roleName" type="text" style="width:160px;" class="easyui-validatebox" required=true  missingMessage="名称必填!" value="" /></td>
				    		</tr>
				    		<tr>
				    			<td>状态:</td>
				    			<td>
				    				启用<input type="radio" name="validFlag" value="Y" />
				    				停用<input type="radio" name="validFlag" value="N" />
				    			</td>
				    		</tr>    							    					
				    		<tr>
				    			<td>描述:</td>
				    			<td><input name="roleDesc" style="width:300px;" class="easyui-validatebox"  type="text"  value="" /></td>
				    		</tr>	  					 					    					    					    					    					    					    					    					
				    	</table>
				    	<div style="text-align:center;padding:5px">
			            	<a id="btn_editCommit" class="easyui-linkbutton" onclick="editCommit();">更新</a>
			            	<a id="btn_editClose" class="easyui-linkbutton" onclick="$('#editWindow').window('close');">关闭</a>
			        	</div>
				 	</form>
			 	</div>
			</div>  
		</div> <!-- end center -->
		
		<!-- 分配菜单窗口
		<div class="easyui-window" id="assignPanle" title="菜单分配" style="width:500px;height:500px" data-options="closed:true, fit:true, border:false, footer:'#assignFooter'">
			<ul class="easyui-tree" id="menuTree" data-options="animate:true,lines:true,checkbox:true"></ul>
		</div>
		<div id="assignFooter" style="padding:5px;">
			Footer Content	
		</div> -->
		
		<div id="assignPanle" class="easyui-window" title="角色菜单分配" data-options="closed:true, fit:true, border:false, minimizable:false, maximizable:false, collapsible:false, draggable:false, modal:true" style="width:500px;height:200px;padding:5px;">
	        <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'north',split:true" style="height:33px">
	            	<form id="assignForm">
						<table class="tabForm datagrid-toolbar" style="height:100%;width:100%">
							<tr>
								<th class="r" width="60">&nbsp;&nbsp;角色编号&nbsp;&nbsp;</th>
								<td width="100">
									<input type="hidden" name="assignRoleId" id="assignRoleId" value="" />
									<input name="assignRoleNo" value="" id="assignRoleNo" readonly />
								</td>
								<th class="r" width="60">&nbsp;&nbsp;角色名称&nbsp;&nbsp;</th>
								<td width="100">
									<input name="assignRoleName" value="" id="assignRoleName" readonly />
								</td>
							</tr>					
						</table>
					</form>	            	
	            </div>	            
	            <div data-options="region:'center'" style="padding:10px;">	       
	                <ul class="easyui-tree" id="menuTree" data-options="animate:true,lines:true,checkbox:true"></ul>
	            </div>
	            <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="#" onclick="saveRoleMenu()" style="width:80px">保存</a>
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="#" onclick="$('#assignPanle').window('close');" style="width:80px">取消</a>
	            </div>
	        </div>
		</div>
		
		
	</div> <!-- end layout -->
	
	
</body>

<script type="text/javascript">
var dg;
var flag ;		//undefined 判断新增和修改方法 
$(function(){ 
	$('#bizDataGrid').datagrid({  
		method: "post",
		url:getContextPath()+'/crole/queryRoleInfo', 
		fit : true,
		fitColumns : true,
		pagination:true,
		border : false,
		idField : 'roleId',
		striped:true,
		rownumbers:true,
		queryParams:$('#queryForm').serializeObject(),
		checkOnSelect:false,
		selectOnCheck:false,
		singleSelect:true,
		onLoadSuccess:function(data){    
			$('.assigncls').linkbutton({text:'分配菜单',iconCls:'icon-filter'});  
		},
		columns:[[
			{field:'roleId',title:'选择',checkbox:true,align:'center',halign:'center'},
			{field:'roleNo',title:'编码',width:80,align:'center',halign:'center',
				formatter : function(value, row, index) {
					return "<vk:btn btncode="101030_XGYS"><a style=\"text-decoration:underline;color:blue;\" href=\"#\" onClick=\"editRole('"+row.roleId+"')\"></vk:btn>"+value+"<vk:btn btncode="101030_XGYS"></a></vk:btn>";
				}
			},
			{field:'roleName',title:'名称',width:100,align:'center',halign:'center'},          
			{field:'roleDesc',title:'描述',width:150,align:'center',halign:'center'},      
			{field:'validFlag',title:'状态',width:50,align:'center',halign:'center',
				formatter : function(value, row, index) {
					if(value!=null&&value!="")
					return value=='Y'?'启用':'停用';
				}
			},
			{field:'isParent',title:'操作',width:50,align:'center',halign:'center',
				formatter : function(value, row, index) {
					var display = "";
					if(row.roleId!=null&&row.roleId!="")
					{        					
						if(row.validFlag == 'Y')
						{        			
							display += "<vk:btn btncode="101030_FPCD"><a href=\"#\" class=\"assigncls\" onclick=\"assignMenu('"+index+"')\" iconCls=\"icon-filter\">分配菜单</a>&nbsp;&nbsp;</vk:btn>";
						}
						else
						{
							//display += "<a href='#' class='unfreezecls' onclick='editRow("+row.roleId+")' iconCls='icon-ok'>解冻</a>&nbsp;&nbsp;";
						}	
					}	
					return display;
				}
			}
		]],
		toolbar:[<vk:btn btncode="101030_XZJS">
			{
				text:"新增",
				iconCls:"icon-add",
				handler:addRole
			},'-',</vk:btn><vk:btn btncode="101030_SCJS">
			{
				text:"删除",
				iconCls:"icon-remove",
				handler:deleteRole
			},'-',</vk:btn><vk:btn btncode="101030_QYJS">
			{
				text:"启用",
				iconCls:"icon-ok",
				handler:enableRole
			},'-',</vk:btn><vk:btn btncode="101030_TYJS">
			{
				text:"停用",
				iconCls:"icon-no",
				handler:disableRole
			},</vk:btn>{}
		]
	});
	
	/**
 	 * 点击查询按钮
 	 */
	$('#dategridSearch').click(function(){
		$('#bizDataGrid').datagrid("load",$('#queryForm').serializeObject());
		$('#bizDataGrid').datagrid("clearChecked");	
	});
	
});

function addRole()
{
	$('#addForm').form('clear');
	$('#addForm').form('load',{type:'3',validFlag:'Y'});
	$('#addWindow').window('open');	
}

function deleteRole()
{
	var checkedItems = new Object();
	checkedItems = $('#bizDataGrid').datagrid('getChecked');
	var roleIds = [];
	var validFlag = true;
	$.each(checkedItems, function(index, item){
		roleIds.push(item.roleId);
		if(item.isParent == 'Y')
		{
			validFlag = false;
		}	
	});
	
	if(roleIds.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}	
	else if(!validFlag)
	{
		$.messager.alert('请检查','父菜单无法直接删除，请先删除子菜单！','warning');
		return;
	}
	else
	{
		$.messager.confirm('确认', "是否彻底删除所选角色？", function(r)
		{
			if (r)
			{
				jQuery.ajax({  
			    	type : 'POST',  
			        contentType:"application/json",  
			        url : contextPath+'/crole/deleteRole',  
			        data :   JSON.stringify(roleIds) ,
			        dataType : 'json',
			        success : function(data) {
			        	var state = data.state;
			        	if(state=='succ')
			        	{      		
			        		$('#bizDataGrid').datagrid("reload");
			        		$('#bizDataGrid').datagrid("clearChecked");
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
		});
	}	
}

function addCommit()
{
	if($('#addForm').form('validate'))
	{
		jQuery.ajax({  
	    	type : 'POST',  
	        contentType:"application/json",  
	        url : contextPath+'/crole/addRoleInfo',  
	        data :   JSON.stringify($("#addForm").serializeObject()) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{        		
	        		$('#addWindow').window('close');
	        		$('#addForm').form('clear');
	        		$('#bizDataGrid').datagrid("reload");
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

function editRole(roleId)
{
	jQuery.ajax({  
    	type : 'POST',  
        contentType:"text/plain",  
        url : contextPath+'/crole/findByPrimaryKey',  
        data :   roleId,//JSON.stringify(roleId) ,
        dataType : 'json',
        success : function(data) {
        	var state = data.state;
        	if(state=='succ')
        	{      		
        		$('#editForm').form('load', data.cloudRole);
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

function editCommit()
{
	if($('#editForm').form('validate'))
	{
		jQuery.ajax({  
	    	type : 'POST',  
	        contentType:"application/json",  
	        url : contextPath+'/crole/updateRole',  
	        data :   JSON.stringify($("#editForm").serializeObject()) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{        		
	        		$('#editWindow').window('close');
	        		$('#editForm').form('clear');
	        		$('#bizDataGrid').datagrid("reload");
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

function enableRole()
{
	var checkedItems = new Object();
	checkedItems = $('#bizDataGrid').datagrid('getChecked');
	var roleIds = [];
	var hasParent = false;
	var validFlag = true;
	var hintMsg = "";
	$.each(checkedItems, function(index, item){
		roleIds.push(item.roleId);
		if(item.isParent == 'Y')
		{
			hasParent = true;
		}	
	});
	
	if(roleIds.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}	
	else
	{
		jQuery.ajax({  
			type : 'POST',  
			contentType:"application/json",  
			url : contextPath+'/crole/enableRole',  
			data :   JSON.stringify(roleIds) ,
			dataType : 'json',
			success : function(data) {
				var state = data.state;
				if(state=='succ')
				{      		
					$('#bizDataGrid').datagrid("reload");
					$('#bizDataGrid').datagrid("clearChecked");
					$.messager.show({
						title:'成功',
						msg:'操作成功',
						timeout:2000
					});
				}
				else if(state=='fail')
				{
					$.messager.alert('失败','操作失败,请检查!','error');
				}	        	
			},  
			error : function(XMLResponse) {  
				$.messager.alert('失败','服务器错误，请联系管理员！','error');
				alert(XMLResponse.responseText);
			}  
		});
	}		
}

function disableRole()
{
	var checkedItems = new Object();
	checkedItems = $('#bizDataGrid').datagrid('getChecked');
	var roleIds = [];
	var hasParent = false;
	var validFlag = true;
	var hintMsg = "";
	$.each(checkedItems, function(index, item){
		roleIds.push(item.roleId);
		if(item.isParent == 'Y')
		{
			hasParent = true;
		}	
	});
	
	if(roleIds.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}	
	else
	{
		jQuery.ajax({  
			type : 'POST',  
			contentType:"application/json",  
			url : contextPath+'/crole/disableRole',  
			data :   JSON.stringify(roleIds) ,
			dataType : 'json',
			success : function(data) {
				var state = data.state;
				if(state=='succ')
				{      		
					$('#bizDataGrid').datagrid("reload");
					$('#bizDataGrid').datagrid("clearChecked");
					$.messager.show({
						title:'成功',
						msg:'操作成功',
						timeout:2000
					});
				}
				else if(state=='fail')
				{
					$.messager.alert('失败','操作失败,请检查!','error');
				}	        	
			},  
			error : function(XMLResponse) {  
				$.messager.alert('失败','服务器错误，请联系管理员！','error');
				alert(XMLResponse.responseText);
			}  
		});
	}	
}

function assignMenu(index)
{
	var row = $('#bizDataGrid').datagrid("getData").rows[index];
	var roleId = row.roleId;
	var roleNo = row.roleNo;
	var roleName = row.roleName;
	jQuery.ajax({  
    	type : 'POST',  
        contentType:"text/plain",  
        url : contextPath+'/cmenu/queryRoleMenuTree', 
        data : roleId,
        dataType : 'json',
        success : function(data) {
        	var state = data.state;
        	if(state=='succ')
        	{        		
        		$('#menuTree').tree({ 			
        			data:data.menuTree,
        			cascadeCheck:false, //1 用户可能有菜单权限，没有下面的按钮权限，用级联无法实现，因为点中菜单，按钮必定全部被选中
        			onClick: function(node)
        			{
        				if(node.checked)
        					$('#menuTree').tree('uncheck',node.target);	
        				else
        					$('#menuTree').tree('check',node.target);	
        			}
        		});
        		$('#menuTree').tree('expandAll');	       		
        	}
        	else if(state=='fail')
        	{
        		//alert("操作失败,请检查!");
        		$.messager.alert('失败','加载菜单树失败,请检查!','error');
        	}	        	
        }
    });
	
	//$('#assignRoleId').val(roleId);
	//$('#assignRoleNo').val(roleNo);
	//$('#assignRoleName').val(roleName);
	$('#assignForm').form('clear');
	$('#assignForm').form('load',{'assignRoleId':roleId,'assignRoleNo':roleNo,'assignRoleName':roleName});
	$('#assignPanle').window('open');
}

function saveRoleMenu()
{
	var roleId = $('#assignRoleId').val();
	var nodes = $('#menuTree').tree('getChecked', ['checked','indeterminate']);	
	var roleMenus = [];
	
	$.each(nodes, function(index, node){
		if( (''+node.id) != '-1')
		{
			var roleMenu = new Object();
			roleMenu.menuId = node.id;
			roleMenu.checked = roleId;
			roleMenu.menuType = node.type;
			roleMenus.push(roleMenu);
		}		
	});
	if(roleMenus.length == 0){
		var roleMenu = new Object();
		roleMenu.checked = roleId;
		roleMenus.push(roleMenu);
	}

	jQuery.ajax({  
    	type : 'POST',  
        contentType:"application/json",  
        url : contextPath+'/cmenu/saveRoleMenu', 
        data : JSON.stringify(roleMenus) ,
        dataType : 'json',
        success : function(data) {
        	var state = data.state;
        	if(state=='succ')
        	{        		
        		$('#assignPanle').window('close');
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
</script>
</html>