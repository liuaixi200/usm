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
	<title>会话查询</title>
		
<script type="text/javascript">


$.extend($.fn.validatebox.defaults.rules, {    
    tbgeta: {    
        validator: function(value,param){
        	
        	var d1 = $.trim($("#orderTime1").datebox("getValue"));
        	var d2 = $.trim($("#orderTime2").datebox("getValue"));
        	param[0]=d1;
        	console.log(d1);
        	if(d1 == '' || d2 == '' || d1 == d2){
        		return true;
        	} else {
        		var d11 = new Date(d1.replace(/-/g,"/"));
        		var d21 = new Date(d2.replace(/-/g,"/"));
        		if(d21>=d11){
        			return true;
        		}
        	}
            return false;
        },    
        message: '应该大于等于{0}'
    }    
});


	$(function(){
	  
			$("#supplierTab").datagrid({
					url:getContextPath() +"/getLoginSessionList",
					method: "post",
					pagination:true,
				    fit : true,
					fitColumns : false,
					border : false,
					queryParams:$('#queryForm').serializeObject(),
					striped:true,
					rownumbers:true,
					checkOnSelect:false,
					selectOnCheck:false,
					singleSelect:true,
					columns:[[
					    {field:'remoteIp',title:'远程IP',align:'center',width:100,halign:'center'},
						/* {field:'supplierCode',title:'供应商编码',align:'center',width:100,halign:'center'}, */
						{field:'localIp',title:'本地IP',align:'center',width:100,halign:'center'},
						{field:'loginName',title:'登陆用户名',align:'center',width:100,halign:'center'},
						{field:'jessionid',title:'jessionId',align:'center',width:240,halign:'center'},
						{field:'loginTime',title:'登陆时间',align:'center',width:150,halign:'center'},
						{field:'createTime',title:'创建时间',align:'center',width:150,halign:'center'},
						{field:'updateTime',title:'更新时间',align:'center',width:150,halign:'center'}
						
				       /*  {field:'sessionSize',title:'session大小',align:'center',width:100,halign:'center'} */
					]],
					toolbar:[]
	        });
	        
	        /**
		 	 * 点击查询按钮 
		 	 */
			$('#dategridSearch').click(function(){
				$('#supplierTab').datagrid("reload",$('#queryForm').serializeObject());
				$('#supplierTab').datagrid("clearChecked");
			});

});

</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
		<div  data-options="region:'north'"  style="height: 65px" title="查询条件" >
			<form id="queryForm">
				<table  style="width:100%;" class="datagrid-toolbar">
					<tr>
						<th class="r" width="80">开始时间</th>
						<td width="200">
							<input style="width:180px"  name="startTime" value="" id="orderTime1" class="easyui-datetimebox" data-options="" />
						</td>
						<th class="r" width="80">结束时间</th>
						<td width="200">
							<input style="width:180px"  name="endTime" value="" id="orderTime2" class="easyui-datetimebox" data-options="validType:'tbgeta[]'" />
						</td>
						<th class="r" width="80">登陆名</th>
						<td width="200">
							<input style="width:180px"  name="loginName" value="" id="loginName" class="easyui-textbox" data-options="" />
						</td>
						<td align="left" >
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-search" id="dategridSearch">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						  
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
		           <table id="supplierTab"></table>
		</div>
</div>


</body>

</HTML>
