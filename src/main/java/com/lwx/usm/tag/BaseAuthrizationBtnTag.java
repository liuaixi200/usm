package com.lwx.usm.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.lwx.usm.utils.Constant;


public class BaseAuthrizationBtnTag extends SimpleTagSupport{
	
	/** 按钮code */
	private String btncode;
	
	private String projectAuthCode;

	@Override
	public void doTag() throws JspException, IOException {
		//获取用户的按钮权限
		
		Object obj = getJspContext().getAttribute(Constant.SESSION_FUNC_BTN,PageContext.SESSION_SCOPE);
		
		if(null != obj){
			List<String> list = (List<String>)obj;
			if(list.contains(btncode)){
				//拥有该按按钮权限
				getJspBody().invoke(null);
			}
		
		}
		
	}

	public String getBtncode() {
		return btncode;
	}

	public void setBtncode(String btncode) {
		this.btncode = btncode;
	}

	public String getProjectAuthCode() {
		return projectAuthCode;
	}

	public void setProjectAuthCode(String projectAuthCode) {
		this.projectAuthCode = projectAuthCode;
	}

	
}
