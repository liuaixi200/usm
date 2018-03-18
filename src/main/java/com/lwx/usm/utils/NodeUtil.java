package com.lwx.usm.utils;

import com.lwx.usm.dto.Node;
import com.lwx.usm.model.SysButton;
import com.lwx.usm.model.SysDictionary;
import com.lwx.usm.model.SysMenu;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class NodeUtil {

	public static List<Node> build4GoodsClassNode(List<SysDictionary> clist){
		List<Node> nodes = new ArrayList<>();
		if(null == clist || clist.size() == 0){
			return nodes;
		} else {
			for(SysDictionary org : clist){
				Node node = new Node();
				node.setId(org.getDicCode());
				node.setText(org.getDicCodeName());
				node.setType(org.getDicType());
				node.setParentId("");
				nodes.add(node);
			}
		}
		
		return nodes;
	}
	
	public static List<Node> build4CloudMenuInfo(List<SysMenu> clist){
		List<Node> nodes = new ArrayList<>();
		if(null == clist || clist.size() == 0){
			return nodes;
		} else {
			for(SysMenu org : clist){
				nodes.add(build4CloudMenuInfo(org));
			}
		}
		
		return nodes;
	}

	public static Node build4CloudMenuInfo(SysMenu munu){
		Node node = new Node();
		node.setId(munu.getMenuId());
		node.setState("open");
		node.setText(munu.getMenuName());
		node.setParentId(munu.getParentId());
		node.setIconCls(munu.getMenuIcon());
		Map<String,Object> attribute = new HashMap<>();
		attribute.put("url",munu.getMenuLink());
		node.setAttributes(attribute);
		node.setType("1");
		return node;
	}
	
	public static List<Node> build4SysButton(List<SysButton> clist){
		List<Node> nodes = new ArrayList<>();
		if(null == clist || clist.size() == 0){
			return nodes;
		} else {
			for(SysButton org : clist){
				Node node = new Node();
				node.setId(org.getButtonId());
				node.setText(org.getButtonName());
				nodes.add(node);
				node.setParentId(org.getParentId());
				node.setType("2");
			}
		}
		
		return nodes;
	}
	
	public static void setChecked(List<String> selected,List<Node> nodes){
		if(selected == null || selected.size() == 0 || nodes == null){
			return;
		} else {
			for(Node node : nodes){
				if(selected.contains(node.getId())){
					node.setChecked(true);
				}
			}
		}
	}
	
	/**
	 * 将子节点放到父节点下
	 * @param pnodes
	 * @param nodes
	 */
	public static void buildTree(List<Node> pnodes,List<Node> nodes){
		if(pnodes == null || nodes == null){
			return;
		} else {
			List<Node> newNode = new ArrayList<>();
			newNode.addAll(pnodes);
			_buildTree(newNode,nodes);
		}
	}
	
	private static void _buildTree(List<Node> pnodes,List<Node> nodes){
		if(pnodes == null || nodes == null){
			return;
		} else {
			int sourceSize = nodes.size();
			Iterator<Node> itNode = nodes.iterator();
			List<Node> newNode = new ArrayList<>();
			while(itNode.hasNext()){
				Node node = itNode.next();
				for(Node pnode : pnodes){
					if(StringUtils.equals(pnode.getId(), node.getParentId())){
						childNode2ParentNode(pnode, node);
						newNode.add(node);
						itNode.remove();
					}
				}
			}
			pnodes.addAll(newNode);
			int currentSize = nodes.size();
			if(sourceSize == currentSize || currentSize == 0){
				return;
			} else {
				_buildTree(pnodes,nodes);
			}
		}
	}
	
	/**
	 * 循环放入父节点下
	 * @param rootNode
	 * @param nodes
	 */
	public static void buildTree(Node rootNode,List<Node> nodes){
		if(nodes == null || nodes == null){
			return;
		} else {
			List<Node> treeNode = new ArrayList<>();
			Iterator<Node> itNode = nodes.iterator();
			while(itNode.hasNext()){
				Node node = itNode.next();
				if(StringUtils.isEmpty(node.getParentId()) || StringUtils.equals(rootNode.getId(),node.getParentId())){
					//空节点放入rootNode节点下面
					childNode2ParentNode(rootNode,node);
					itNode.remove();
					treeNode.add(node);
				}
				
			}
			//对treeNode进行构建
			_buildTree(treeNode, nodes);
		}
	}
	
	private static void childNode2ParentNode(Node p,Node c){
		List<Node> ns = p.getChildren();
		if(ns == null){
			ns = new ArrayList<>();
			p.setChildren(ns);
		}
		ns.add(c);
	}
	
	public static void main(String[] args) {
		List<String> plist = new ArrayList<>();
		plist.add("1");plist.add("2");plist.add("3");plist.add("4");plist.add("5");
		Iterator<String> it = plist.iterator();
		while(it.hasNext()){
			String s = it.next();
			if("2".equals(s)){
				it.remove();
			}
		}
		List<String> clist = new ArrayList<>();
		plist.addAll(clist);
		System.out.println(plist);
	}
}
