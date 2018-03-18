package com.lwx.usm.service;

import java.util.Properties;

public class FceProperties {


	private Properties props;
	
	
	public String getProperty(String prop){
		return props.getProperty(prop);
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}



}
