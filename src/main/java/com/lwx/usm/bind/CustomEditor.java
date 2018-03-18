package com.lwx.usm.bind;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class CustomEditor implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// TODO Auto-generated method stub
		binder.registerCustomEditor(Long.class, new StringLongEditor());
	}
	
	class StringLongEditor extends PropertyEditorSupport{

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			super.setAsText(text);
			if(StringUtils.isEmpty(text)){
				this.setValue(0);
			}
			this.setValue(Long.valueOf(text));
		}

	}

}
