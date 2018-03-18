$(function(){   
	//首页右上角菜单
	$(".help li").eq(0).mouseover(function(){	
		$(this).addClass("hover");
	});
	$(".help li").eq(0).mouseout(function(){	
		$(this).removeClass("hover");
	});
	
 
 
 	//下拉列表值回填
 	$(".help .sel dl dd").click(function(){	
		$(this).addClass("hover");
		$("#zhi").html($(this).html());
		$(".help .bg_no dl dd").hide();
	});
	
	$(".rj_con_1 .hyzx_3").mouseover(function(){	
		$(this).css("background","#f5f5f5");
	});
 	$(".rj_con_1 .hyzx_3").mouseout(function(){	
		$(this).css("background","#ffffff");
	});
		
		
	//弹框
	$("#modifyAccountButton1").click(function(){
			$("#modifyAccountDiv1").floatdiv("middle").show();
	});
	
	$("#modifyAccountButton2").click(function(){
			$("#modifyAccountDiv2").floatdiv("middle").show();
	});	

	//首页变色小图标
	$(".yun_ul li").mouseover(function(){	
		$(this).addClass("curr");	
	});
	$(".yun_ul li").mouseout(function(){	
		$(this).removeClass("curr");
	});
	
	$(".can_l .a4").mouseover(function(){	
		$(this).addClass("curr");	
	});
	$(".can_l .a4").mouseout(function(){	
		$(this).removeClass("curr");
	});
	
	$(".huo_2 dd").mouseover(function(){	
		$(this).addClass("curr");	
	});
	$(".huo_2 dd").mouseout(function(){	
		$(this).removeClass("curr");
	});
		
	
	//左边栏
 	$(".left_menu dl dt").click(function(){
 		$(this).parent().toggleClass("kai");
  	});
	
	/*字体大中小*/
    function doZoom(size) {
        var zoom = document.all ? document.all['Zoom'] : document.getElementById('Zoom');
        zoom.style.fontSize = size + 'px';
		alert("ss");
    }
});

 
//选项卡滑动
var  timeoutid;
  $(function (){
    $(".menu li" ).each( function (index){
     $(this).mouseover(function (){
          var  liObj=$(this);
          timeoutid=setTimeout(function (){
          $(".menu li.tabFocus" ).removeClass("tabFocus");
          $(".content dd" ).removeClass();
		  if(index>0){
			  $(".content dd" ).eq(1).addClass( "conFocus" );
				liObj.addClass("tabFocus" );
			 
			  }else{
				  $(".content dd" ).eq(index).addClass( "conFocus" );
					liObj.addClass("tabFocus" );
				 
			  }
	   		},100);
          
      })
	  .mouseout(function (){
          clearTimeout(timeoutid);
      });
   });
  });
