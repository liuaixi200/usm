$.fn.extend({
	floatdiv:function(location){
		//判断浏览器版本
		var isIE6=false;
		var ie6Top=0;
		var Sys = {};
	    var ua = navigator.userAgent.toLowerCase();
	    var s;
	    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 0;
		if(Sys.ie && Sys.ie=="6.0"){
			isIE6=true;
		}
		var windowWidth,windowHeight;//窗口的高和宽
		//取得窗口的高和宽
		if (self.innerHeight) {
			windowWidth=self.innerWidth;
			windowHeight=self.innerHeight;
		}else if (document.documentElement&&document.documentElement.clientHeight) {
			windowWidth=document.documentElement.clientWidth;
			windowHeight=document.documentElement.clientHeight;
		} else if (document.body) {
			windowWidth=document.body.clientWidth;
			windowHeight=document.body.clientHeight;
		}
		return this.each(function(){
			var loc;//层的绝对定位位置
			var wrap=$("<div id='popup_wrap'></div>");
			var thisHeight=$(this).css("height");
			var thisWidth=$(this).css("width");
			var wrap2=$("<iframe name='popup_iframe' height='"+thisHeight+"' width='"+thisWidth+"' style='background:#fff; left:0px; position:absolute;top:0px; border:0px;z-index:998;padding:5px'></iframe>");
			var top=-1;
			if(location==undefined || location.constructor == String){
				switch(location){
					case("rightbottom")://右下角
						loc={right:"0px",bottom:"0px"};
						break;
					case("leftbottom")://左下角
						loc={left:"0px",bottom:"0px"};
						break;	
					case("lefttop")://左上角
						loc={left:"0px",top:"0px"};
						top=0;
						break;
					case("righttop")://右上角
						loc={right:"0px",top:"0px"};
						top=0;
						break;
					case("middletop")://居中置顶
						loc={left:windowWidth/2-$(this).width()/2+"px",top:"0px"};
						top=0;
						break;
					case("middlebottom")://居中置低
						loc={left:windowWidth/2-$(this).width()/2+"px",bottom:"0px"};
						break;
					case("leftmiddle")://左边居中
						loc={left:"0px",top:windowHeight/2-$(this).height()/2+"px"};
						top=windowHeight/2-$(this).height()/2;
						break;
					case("rightmiddle")://右边居中
						loc={right:"0px",top:windowHeight/2-$(this).height()/2+"px"};
						top=windowHeight/2-$(this).height()/2;
						break;
					case("middle")://居中
						var l=0;//居左
						var t=0;//居上
						l=windowWidth/2-$(this).width()/2;
						t=windowHeight/2-$(this).height()/2;
						top=t;
						loc={left:l+"px",top:t+"px"};
						break;
					default://默认为右下角
						location="rightbottom";
						loc={right:"0px",bottom:"0px"};
						break;
				}
			}else{
				loc=location;
				alert(loc.bottom);
				var str=loc.top;
				//09-11-5修改：加上top为空值时的判断
				if (typeof(str)!= 'undefined'){
					str=str.replace("px","");
					top=str;
				}
			}
			/*fied ie6 css hack*/
			var myScrollHeight=0;
			var myClientHeight=0;
			if (document.documentElement&&document.documentElement.clientHeight) {
				myScrollHeight=document.documentElement.scrollTop;
				myClientHeight=document.documentElement.clientHeight;
			} 
			if (document.body && document.body.scrollTop>document.documentElement.scrollTop) {
				myScrollHeight=document.body.scrollTop;
				myClientHeight=document.body.clientHeight;
			}
			if (top>=0)
			{
				loc.top=top+myScrollHeight;
				//alert(top+",,,"+document.body.scrollTop);
			}else{
				loc.top=myScrollHeight+myClientHeight-this.offsetHeight;
			}
			
			$("body").append(wrap);
			wrap.css(loc).css({position:"absolute"});
			$(wrap).css("z-index","999");
			if (isIE6)
			{				
				wrap.css("position","absolute");
				//没有加这个的话，ie6使用表达式时就会发现跳动现象
				//$("body").css("background-attachment","fixed").css("background-image","url(n1othing.txt)");
			}
			//将要固定的层添加到固定层里
			$(this).appendTo(wrap);
			if (isIE6){
				$(wrap2).appendTo(wrap);
			}
			showBackGround();
		});
	}
});
function showBackGround(){
	var bWidth=parseInt(document.documentElement.scrollWidth);
	var bHeight=parseInt(document.documentElement.scrollHeight);
	var styleStr="z-index:888;top:0px;left:0px;position:absolute;background:#666;-ms-filter:progid:DXImageTransform.Microsoft.Alpha(Opacity=50); filter:alpha(opacity=50); opacity:.5;width:"+bWidth+"px;height:"+bHeight+"px;";
	var background=$("<div id='popup_background' style='"+styleStr+"'></div>");
	$(background).appendTo("body");
}
$("document").ready(function(){
	$("a[name='popup_close_btn']").click(function(){
		var closeRefesh=$('#mesWindow').attr('closeRefesh');
		if(closeRefesh=='true' || $(this).attr("refresh_after_close")=="true"){
			window.location.reload();
		}else{
			$('div[isCoverScreen="true"]').remove();
			$("#popup_background").remove();
			$(this).parent().parent().unwrap().hide();	
			$("iframe[name='popup_iframe']").remove();
		}
	});
	/**
	$('#standardConsult').click(function(){
		var consultBtnFlag;
		if(consultBtnFlag) return;
		consultBtnFlag=true;
		//显示窗体	
		$("#mesWindowConsult").floatdiv("middle").show();
		consultBtnFlag=false;
		
	});	
	*/
});