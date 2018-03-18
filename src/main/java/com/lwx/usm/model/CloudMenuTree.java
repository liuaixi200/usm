package com.lwx.usm.model;

import com.lwx.usm.dto.Node;

import java.util.ArrayList;
import java.util.List;


public class CloudMenuTree implements MenuTree
{
	private Node attributes;
	private String id;
	private String text;
	private String state;
	private Boolean checked;
	private String iconCls;
	private String menuType;
	private List<CloudMenuTree> children;
		
	public CloudMenuTree(Node treeNode)
	{
		this.attributes = treeNode;
		this.id = treeNode.getId();
		this.text = treeNode.getText();
		if(treeNode.getParentId()==null || treeNode.getParentId().equals("Y"))
		{
			this.children = new ArrayList<CloudMenuTree>();
		}
		
		if(treeNode.isChecked()&& treeNode.getParentId().equalsIgnoreCase("N"))
		{
			this.checked = true;
		}
		else
		{
			this.checked = false;
		}	
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Override
	public Node getAttributes() {
		return attributes;
	}

	public void setAttributes(Node attributes) {
		this.attributes = attributes;
	}

	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public List<CloudMenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<CloudMenuTree> children) {
		this.children = children;
	}
}
