/**
 * linkbutton方法扩展
 * @param {Object} jq
 */
$.extend($.fn.linkbutton.methods, {
    /**
     * 激活选项（覆盖重写）
     * @param {Object} jq
     */
    enable: function(jq){
        return jq.each(function(){
            var state = $.data(this, 'linkbutton');
            if ($(this).hasClass('l-btn-disabled')) {
                var itemData = state._eventsStore;
                //恢复超链接
                if (itemData.href) {
                    $(this).attr("href", itemData.href);
                }
                //回复点击事件
                if (itemData.onclicks) {
                    for (var j = 0; j < itemData.onclicks.length; j++) {
                        $(this).bind('click', itemData.onclicks[j]);
                    }
                }
                //设置target为null，清空存储的事件处理程序
                itemData.target = null;
                itemData.onclicks = [];
                $(this).removeClass('l-btn-disabled');
            }
        });
    },
    /**
     * 禁用选项（覆盖重写）
     * @param {Object} jq
     */
    disable: function(jq){
        return jq.each(function(){
            var state = $.data(this, 'linkbutton');
            if (!state._eventsStore)
                state._eventsStore = {};
            if (!$(this).hasClass('l-btn-disabled')) {
                var eventsStore = {};
                eventsStore.target = this;
                eventsStore.onclicks = [];
                //处理超链接
                var strHref = $(this).attr("href");
                if (strHref) {
                    eventsStore.href = strHref;
                    $(this).attr("href", "javascript:void(0)");
                }
                //处理直接耦合绑定到onclick属性上的事件
                var onclickStr = $(this).attr("onclick");
                if (onclickStr && onclickStr != "") {
                    eventsStore.onclicks[eventsStore.onclicks.length] = new Function(onclickStr);
                    $(this).attr("onclick", "");
                }
                //处理使用jquery绑定的事件
                var eventDatas = $(this).data("events") || $._data(this, 'events');
                if (eventDatas["click"]) {
                    var eventData = eventDatas["click"];
                    for (var i = 0; i < eventData.length; i++) {
                        if (eventData[i].namespace != "menu") {
                            eventsStore.onclicks[eventsStore.onclicks.length] = eventData[i]["handler"];
                            $(this).unbind('click', eventData[i]["handler"]);
                            i--;
                        }
                    }
                }
                state._eventsStore = eventsStore;
                $(this).addClass('l-btn-disabled');
            }
        });
    }
});

$(function() {
	var themeName = $.cookie('easyuiTheme');
	if(themeName != null )
	{
		var $easyuiTheme = $('#easyuiTheme');
    	var url = $easyuiTheme.attr('href');
    	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    	$easyuiTheme.attr('href', href);
	}	
});

/**
 * datagrid editor方法扩展
 * @param {Object} jq
 */
$.extend($.fn.datagrid.methods, {    
    addEditor : function(jq, param) {    
        if (param instanceof Array) {    
            $.each(param, function(index, item) {    
                var e = $(jq).datagrid('getColumnOption', item.field);    
                e.editor = item.editor;    
            });    
        } else {    
            var e = $(jq).datagrid('getColumnOption', param.field);    
            e.editor = param.editor;    
        }    
    },    
    removeEditor : function(jq, param) {    
        if (param instanceof Array) {    
            $.each(param, function(index, item) { 
                var e = $(jq).datagrid('getColumnOption', item);    
                e.editor = {};    
            });    
        } else {    
            var e = $(jq).datagrid('getColumnOption', param);    
            e.editor = {};    
        }    
    }    
});
/**
 * 动态设置列标题的的扩展方法
 */
$.extend($.fn.datagrid.methods, {    
    setColumnTitle: function(jq, option){    
        if(option.field){  
            return jq.each(function(){    
                var $panel = $(this).datagrid("getPanel");  
                var $field = $('td[field='+option.field+']',$panel);  
                if($field.length){  
                    var $span = $("span",$field).eq(0);  
                    $span.html(option.text);  
                }  
            });  
        }  
        return jq;        
    }    
});
