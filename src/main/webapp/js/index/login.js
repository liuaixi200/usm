/**
 * 首页登陆
 * 
 */
$(function(){
	var taken;
/*	jQuery.ajax({  //暂时不使用
    	type : 'POST',  
        url : contextPath+'/login/safety?a'+new Date().getTime(),
        dataType : 'json',
        cache:false,
        success : function(data) {
        	if(data != null || data != "" ){
        		taken = data.token;
        		$('#taken').val(taken) ;
        	}
        }
    });*/
	
	//国际化 1、从cookie中取值判断用户选择的语言
	var defaultLanguage = $("#zhi").attr("option");
	var cooklanguage= $.cookie('language');
	if($.trim(cooklanguage)!=""){
		if($.trim(cooklanguage)=="zh"){
 			$("#zhi").attr("option","zh");
 			$("#zhi").html("中文");
 			
 		}
	 	if($.trim(cooklanguage)=="english"){
	 		$("#zhi").attr("option","english");	
	 		$("#zhi").html("English");
	 	}
		$.cookie('language', cooklanguage);
		//alert("不为空");
		//alert("cooklanguage=="+$.trim(cooklanguage));
	}else{
		$.cookie('language', defaultLanguage);
		//alert("为空");
		//alert("cooklanguage"+$.trim(cooklanguage));
	}
	
	//3、点击事件，当前语言更改事件处理
	$('#english').bind('click',function(){
		$("#zhi").attr("option","english");
		$.cookie('language', "english");	
	})
	$('#zh').bind('click',function(){
		$("#zhi").attr("option","zh");
		$.cookie('language', "zh");
	})
	
	
	var cookname= $.cookie('username');
	if(cookname!=""){
		$('#username').val(cookname);
		$('#username').attr("option","1");
	}else{
		$('#username').val("");
		$('#password').val("");
		$('#username').attr("option","0");
	}
	
 	//点击登陆按钮
 	//提交按钮buttonsub
	$('#login-btn').click(function(){
		 formSubmit()
	})
	
	//回车登陆
	$("#loginfrm").keypress(function(e) { 

		if (e.which == 13) { 

			formSubmit();
		} 
	});
	
	//表单提交登陆
	function formSubmit(){
		
		if($.trim($("#username").val()) == ""){
		//	$.messager.alert("错误信息","用户名不能为空！","error");
			$('#passwordtip').html("请输入用户名");
			return;
		}
		if($.trim($("#password").val()) == ""){
			//$.messager.alert("错误信息","密码不能为空！","error");
			$('#passwordtip').html("请输入密码");
			return;
		}

    	var data = $.toJSON($('#loginfrm').serializeObject());
		jQuery.ajax({  
	    	type : 'POST',  
            contentType:"application/json",  
            url : contextPath+'/login/loginValite',  
            data : data, 
            dataType : 'json',
            success : function(data) {
            	if(data.state=="succ"){

/*            		if($('#rmbname').attr("checked")){
            			$.cookie('username', data.result.userName, { expires: 7, path: '/' });
            			$('#username').val(data.result.userName);
            		}else{
            			 $.cookie('username', "");
            			 $('#username').val(""); 
            		} 
            		$('#password').val("");*/
            		self.location.href=contextPath+"/index";
            	}
            	if(data.state=="fail"){
            	//	$('.err').css('display','inline');
            		$('#passwordtip').html(data.msg); 
            	}  
            }
        });
        
	}
 })
 
 
