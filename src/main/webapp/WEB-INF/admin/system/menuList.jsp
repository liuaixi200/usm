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
	<title>菜单管理</title>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false">
		<!--  <div region="west" border="false" title="菜单树" style="width:200px;"> -->
		<div data-options="region:'west',split:true,border:false"  style="width:200px">	
			<div class="easyui-panel"  title="菜单管理" data-options="fit:true,border:false" style="padding:5px">
        		<ul class="easyui-tree" id="menuTree" data-options="animate:true,lines:true"></ul>
    		</div>
		</div>

		<!-- <div region="center" border="false"  style='overFlow-x: hidden; OVERFLOW: auto'>-->
		<div data-options="region:'center',border:false">	
			<div class="easyui-layout" data-options="fit:true">
                <div region="north" title="查询条件" style="height:65px;overflower:hidden;">
                	<form id="queryForm">
						<table class="tabForm datagrid-toolbar" style="height:100%;width:100%">
							<tr>
								<input name="parentId" value="-1" id="parentId" type="hidden" />
								<th class="r" width="60">&nbsp;&nbsp;菜单名称&nbsp;</th>
								<td width="100">
									<input class="easyui-textbox" name="menuName" value="" id="menuName" />
								</td>
								<th class="r" width="70">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态&nbsp;</th>
								<td width="100">
									<select class="easyui-combobox" name="validFlag" id="validFlag" style="width:100px;" data-options="panelHeight:'auto'">
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
                <div region="center" border="false"  style='overFlow-x: hidden; OVERFLOW: auto'>
                	<table id="bizDataGrid"></table>
                </div>
            </div>
			<!-- 查询列表 -->			
	
			<!-- 新增窗口 -->
			<div id="addWindow" class="easyui-window" title="菜单新增" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false"  style="width:400px;">
				<div style="padding:10px 20px 5px 20px">
					<form id="addForm"  method="post">
				 		<input type="hidden" name="menuId" value="" />
				 		<input name="parentId" value="" type="hidden" />
				 		<input name="isParent" value="N" type="hidden" />
				 		<table>
				    		<tr>
				    			<td>序号:</td>
				    			<td><input type="text"  name="menuCode" style="width:160px;" class="easyui-validatebox" required=true  missingMessage="序号必填!"  value="" /></td>
				    		</tr>		    	
				    		<tr>
				    			<td>名称:</td>
				    			<td><input name="menuName" type="text" style="width:160px;" class="easyui-validatebox" required=true  missingMessage="菜单名称必填!"  value="" /></td>
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
				    			<td>链接:</td>
				    			<td><input name="menuLink" style="width:300px;" class="easyui-validatebox"  type="text" value="" /></td>
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
			<div id="editWindow" class="easyui-window" title="菜单编辑" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false"  style="width:400px;">
				<div style="padding:10px 20px 5px 20px">
					<form id="editForm"  method="post">
				 		<input type="hidden" name="menuId" value="" />
				 		<input type="hidden" name="parentId" value="" />
				 		<input type="hidden" name="isParent" value=""  />
				 		<table>
				    		<tr>
				    			<td>序号:</td>
				    			<td><input type="text" name="menuCode" style="width:160px;" class="easyui-validatebox" required=true  missingMessage="序号必填!"  value="" /></td>
				    		</tr>		    	
				    		<tr>
				    			<td>名称:</td>
				    			<td><input name="menuName" type="text" readonly style="width:160px;" class="easyui-validatebox" required=true  missingMessage="名称必填!" value="" /></td>
				    		</tr>
				    		<tr>
				    			<td>状态:</td>
				    			<td>
				    				启用<input type="radio" name="validFlag" disabled value="Y" />
				    				停用<input type="radio" name="validFlag" disabled value="N" />
				    			</td>
				    		</tr>    							    					
				    		<tr>
				    			<td>链接:</td>
				    			<td><input name="menuLink" style="width:300px;" readonly class="easyui-validatebox"  type="text"  value="" /></td>
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
	
	</div> <!-- end layout -->
		
</body>

<script type="text/javascript">
$(function(){ 
	initMenu();
	
	$('#bizDataGrid').datagrid({
		method: "post",
	    url:getContextPath()+'/cmenu/queryList', 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'menuId',
		striped:true,
		rownumbers:true,
		queryParams:$('#queryForm').serializeObject(),
		checkOnSelect:false,
		selectOnCheck:false,
		singleSelect:true,
	    columns:[[
			{field:'menuId',title:'选择',checkbox:true,align:'center',halign:'center'},
			{field:'menuCode',title:'序号',width:50,align:'center',halign:'center'}, 
	        {field:'menuName',title:'菜单名称',width:100,align:'center',halign:'center',
	        	formatter : function(value, row, index) {
	        		return "<vk:btn btncode="101020_XGCD"><a style=\"text-decoration:underline;color:blue;\" href=\"#\" onClick=\"editMenu('"+row.menuId+"')\"></vk:btn>"+value+"<vk:btn btncode="101020_XGCD"></a></vk:btn>";
	        		//return value;
	        	}
	        },              
	        {field:'menuLink',title:'菜单链接',width:250, align:'center',halign:'center'},	        
	        {field:'validFlag',title:'状态',width:50,align:'center',halign:'center',
	        	formatter : function(value, row, index) {
	        	    if(value!=null&&value!="")
	       			return value=='Y'?'启用':'停用';
	        	}
	        },
	        {field:'isParent',hidden:true,align:'center',halign:'center'}
	    ]],
	  	toolbar:[{}]
		<%-- toolbar:[<vk:btn btncode="101020_XZCD">{
			text:"新增",
			iconCls:"icon-add",
			handler:addMenu
		},'-',</vk:btn><vk:btn btncode="101020_SCCD">{
			text:"删除",
			iconCls:"icon-remove",
			handler:deleteMenu
		},'-',</vk:btn><vk:btn btncode="101020_QYCD">{
			text:"启用",
			iconCls:"icon-ok",
			handler:enableMenu
		},'-',</vk:btn><vk:btn btncode="101020_TYCD">{
			text:"停用",
			iconCls:"icon-no",
			handler:disableMenu
		},</vk:btn>{}] 
		--%>
	});
	
	/**
 	 * 点击查询按钮
 	 */
	$('#dategridSearch').click(function(){
		$('#bizDataGrid').datagrid("load",$('#queryForm').serializeObject());
		$('#bizDataGrid').datagrid("clearChecked");	
	});
	
});

function initMenu()
{
	jQuery.ajax({  
    	type : 'POST',  
        contentType:"application/json",  
        url : contextPath+'/cmenu/queryTree', 
        data :'-1',
        dataType : 'json',
        success : function(data) {
        	var state = data.state;
        	if(state=='succ')
        	{        		
        		$('#menuTree').tree({
        			data:data.menuTree,
        			onClick: function(node)
        			{
        				$('#queryForm').form('load',{parentId:node.id});
    					$('#bizDataGrid').datagrid("load",$('#queryForm').serializeObject());
    					$('#bizDataGrid').datagrid("clearChecked");
        				/*
    					if(node.attributes.isParent == 'N')
        				{
        					$('#queryForm').form('load',{parentId:node.id});
        					$('#bizDataGrid').datagrid("load",$('#queryForm').serializeObject());
        					$('#bizDataGrid').datagrid("clearChecked");
        				}
        				else
        				{
        					$('#menuTree').tree('toggle',node);
        				}*/	
        			}
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

function addMenu()
{
	$('#addForm').form('clear');
	if($("#queryForm input[name='parentId']").val() != '-1')
		$('#addForm').form('load',{parentId:$("#queryForm input[name='parentId']").val()});
	$('#addForm').form('load',{isParent:'N',validFlag:'Y'});
	$('#addWindow').window('open');	
}

function deleteMenu()
{
	var checkedItems = new Object();
	checkedItems = $('#bizDataGrid').datagrid('getChecked');
	var menuIds = [];
	var validFlag = true;
	$.each(checkedItems, function(index, item){
		menuIds.push(item.menuId);
		if(item.isParent == 'Y')
		{
			validFlag = false;
		}	
	});
	
	if(menuIds.length==0)
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
		$.messager.confirm('确认', "是否彻底删除所选菜单？", function(r)
		{
			if (r)
			{
				jQuery.ajax({  
			    	type : 'POST',  
			        contentType:"application/json",  
			        url : contextPath+'/cmenu/deleteMenu',  
			        data :   JSON.stringify(menuIds) ,
			        dataType : 'json',
			        success : function(data) {
			        	var state = data.state;
			        	if(state=='succ')
			        	{      		
			        		$('#bizDataGrid').datagrid("reload");
			        		$('#bizDataGrid').datagrid("clearChecked");
			        		initMenu();
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
	        url : contextPath+'/cmenu/addMenuInfo',  
	        data :   JSON.stringify($("#addForm").serializeObject()) ,
	        dataType : 'json',
	        success : function(data) {
	        	var state = data.state;
	        	if(state=='succ')
	        	{        		
	        		$('#addWindow').window('close');
	        		$('#addForm').form('clear');
	        		$('#bizDataGrid').datagrid("reload");
	        		initMenu();
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

function editMenu(menuId)
{
	jQuery.ajax({  
    	type : 'POST',  
        contentType:"text/plain",  
        url : contextPath+'/cmenu/findByPrimaryKey',  
        data :   menuId,//JSON.stringify(userId) ,
        dataType : 'json',
        success : function(data) {
        	var state = data.state;
        	if(state=='succ')
        	{      		
        		$('#editForm').form('load', data.cloudMenu);
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
	        url : contextPath+'/cmenu/updateMenu',  
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

function enableMenu()
{
	var checkedItems = new Object();
	checkedItems = $('#bizDataGrid').datagrid('getChecked');
	var menuIds = [];
	var hasParent = false;
	var validFlag = true;
	var hintMsg = "";
	$.each(checkedItems, function(index, item){
		menuIds.push(item.menuId);
		if(item.isParent == 'Y')
		{
			hasParent = true;
		}	
	});
	
	if(menuIds.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}	
	
	if(hasParent)
	{
		hintMsg = '启用父菜单将会启用其所有子菜单，是否继续?';
	}
	else
	{
		hintMsg = '是否启用所选菜单？';
	}	
		
	$.messager.confirm('确认', hintMsg, function(r)
	{
		if (r)
		{
			jQuery.ajax({  
				type : 'POST',  
				contentType:"application/json",  
				url : contextPath+'/cmenu/enableMenu',  
				data :   JSON.stringify(menuIds) ,
				dataType : 'json',
				success : function(data) {
					var state = data.state;
					if(state=='succ')
					{      		
						$('#bizDataGrid').datagrid("reload");
						$('#bizDataGrid').datagrid("clearChecked");
						//initMenu();
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
	});	
}

function disableMenu()
{
	var checkedItems = new Object();
	checkedItems = $('#bizDataGrid').datagrid('getChecked');
	var menuIds = [];
	var hasParent = false;
	var validFlag = true;
	var hintMsg = "";
	$.each(checkedItems, function(index, item){
		menuIds.push(item.menuId);
		if(item.isParent == 'Y')
		{
			hasParent = true;
		}	
	});
	
	if(menuIds.length==0)
	{
		$.messager.alert('请检查','您还没有选择任何内容！','warning');
		return;
	}
	
	if(hasParent)
	{
		hintMsg = '停用父菜单将会停用其所有子菜单，是否继续?';
	}
	else
	{
		hintMsg = '是否停用所选菜单？';
	}
	
	$.messager.confirm('确认', hintMsg, function(r)
	{
		if (r)
		{
			jQuery.ajax({  
			    type : 'POST',  
				contentType:"application/json",  
				url : contextPath+'/cmenu/disableMenu',  
				data :   JSON.stringify(menuIds) ,
				dataType : 'json',
				success : function(data) {
					var state = data.state;
					if(state=='succ')
					{      		
						$('#bizDataGrid').datagrid("reload");
						$('#bizDataGrid').datagrid("clearChecked");
						//initMenu();
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
	});	
}
</script>

</html>