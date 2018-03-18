package com.lwx.usm.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 树形节点
 * @author liuax01
 *
 */
public class Node implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String text;
	
	private String iconCls;
	
	private boolean checked;
	
	private Map<String,Object> attributes;
	
	private String state;// 默认打开状态
	
	private List<Node> children;
	
	private String type;
	
	private String parentId;

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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
