/*
		 * 提交修改密码
		 */
        function modifyPwd(flag) 
        {
        	var $pwd = $('#password');
			var $newPwd = $('#newPassword');
            var $rePwd = $('#rePassword');

            if ($pwd.val() == '') {
                $.messager.alert({title:'系统提示ʾ',msg:'请输入旧密码'});
                return false;
            }
            if ($newPwd.val() == '') {
                $.messager.alert({title:'系统提示ʾ',msg:'请输入新密码'});
                return false;
            }
            if ($rePwd.val() == '') {
                msgShow('系统提示', '请再一次输入密码', 'warning');
                return false;
            }
            if ($newPwd.val() != $rePwd.val()) {
                $.messager.alert({title:'系统提示ʾ',msg:'两次新密码不一致，请重新输入'});
                return false;
            }
            var dataCommit = new Object();
            dataCommit.password = $pwd.val();
            dataCommit.newPassword = $newPwd.val();
            //提交请求
            jQuery.ajax({  
		    	type : 'POST',  
		        contentType:"application/json",  
		        url : contextPath+'/cuser/modifyPwd',  
		        data :  JSON.stringify( dataCommit ) , 
		        dataType : 'json',
		        success : function(data) {
		        	var state = data.success;
		        	if(state)
		        	{
		        		//alert(data.msg);
		        		$.messager.show({
		            		title:'成功',
		            		msg:data.msg
		            	});
		        		if('index' == flag){
		        			$('#mp').window('close');
		        		} else if('login' == flag){
		        			self.location.href=contextPath+"/index";
		        		}
		        		
		        	}
		        	else
		        	{
		        		//alert(data.msg);
		        		$.messager.alert({
		            		title:'失败',
		            		msg:data.msg
		            	});
		        	}
		        }
		    });
        }
		
		
        /*
		 *打开修改密码窗口
		 */
        function openPwdWin() 
		{
        	$('#password').val("");
			$('#newPassword').val("");
            $('#rePassword').val("");
        	$('#mp').window('open');
		}
        
