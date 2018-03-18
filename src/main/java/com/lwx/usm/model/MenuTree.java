package com.lwx.usm.model;

import com.lwx.usm.dto.Node;

import java.util.List;

public interface MenuTree 
{
	public String getId();
	public <T extends MenuTree> List<T> getChildren();
	public Node getAttributes();
	public void setState(String state);
}
