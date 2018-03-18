/**
 *利用正则表达式格去掉小数点后多余的0
 */
function formatAmount(value)
{
	if(value.indexOf(".") > 0)
	{    
		value = parseFloat(value).toFixed(6)+"";
		value = value.replace(/0+?$/g, "");//去掉多余的0    
		value = value.replace(/[.]$/g, "");//如最后一位是.则去掉    
		return value;
	}else{
		if(parseFloat(value)>0){
			return parseFloat(value);
		}else{
			return 0;
		}
		
	}
}	
 
/**
 * 将表单序列化成object
 */
$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

/**
 * 获取项目根路径 项目根路径可能是空的
 * @returns {} 
 */
function getContextPath(){ 
	/*var pathName = document.location.pathname; 
	var index = pathName.substr(1).indexOf("/"); 
	var result = pathName.substr(0,index+1); */
	if(!CONTEXT){
		alert("取不到CONTEXT，请联系管理员");
	}
	return CONTEXT; 
}
var contextPath=getContextPath();

//获取url中的参数
/*function getUrlParam(name) {
	alert("name=="+name);
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    alert('r=='+r);
    if (r != null) return unescape(r[2]); return null; //返回参数值
}*/

/**
 * 页面加载，判断是登陆还是登出 公共部分
 * @returns {} 
 */
$(function(){

	//获取url中的参数
	(function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    })(jQuery);
 })
 
 $.ajaxSetup({
    statusCode: {
        909: function() {
        	console.log(location.href);
        	if(location.href.indexOf("login") > 0){
        		return;
        	}
        	top.location.href=contextPath+'/login'; 
        }
    },
    beforeSend: function () {
    	load();
    },
   complete:function(){
	   disLoad();
    },
    error : function(XMLResponse) {
   		console.log(XMLResponse.responseText);
   		if(XMLResponse.status == 908){
   			$.messager.alert('失败',XMLResponse.responseText,'error');
   		} else if(XMLResponse.status == 909){
   			//不处理
   		} else {
   			$.messager.alert('失败','服务器错误，请稍后再试或联系管理员'+XMLResponse.responseText,'error');
   		}

    } 
 });

//弹出加载层
function load() {  
    $("<div class=\"datagrid-mask\" style='position:fixed; top:0; z-index:999998;'></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
   $("<div class=\"datagrid-mask-msg\" style='position:fixed; top:0; z-index:999999;'></div>").html("发送中，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
}
//取消加载层  
function disLoad() {  
    $(".datagrid-mask").remove();  
   $(".datagrid-mask-msg").remove();  
}
//验证combobox的值是否为下拉框选取
function comboboxValueValidate(obj, linkObj, clearThis, clearLink){
	var _options = obj.combobox('options');
	var _data = obj.combobox('getData');/* 下拉框所有选项 */
	var _value = obj.combobox('getValue');/* 用户输入的值 */
	var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
	for (var i = 0; i < _data.length; i++) {
		if (_data[i][_options.valueField] == _value) {
			_b=true;
			break;
		}
	}
	if(!_b){
		if(clearThis){
			obj.combobox('setValue', '');
		}
		if(linkObj && clearLink){
			linkObj.val(null);
		}
	}
}

if(!window.console){  
    window.console = {};  
}  
if(!window.console.log){  
    window.console.log = function(msg){};
}  

// 时间格式化
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时 
        "H+" : this.getHours(), //小时    
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}