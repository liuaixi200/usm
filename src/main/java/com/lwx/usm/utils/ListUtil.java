package com.lwx.usm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtil {

	public static String getString(List<?> list) {
		if (list == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object o : list) {
			sb.append(o.toString() + " ");
		}
		return sb.toString().trim();
	}

	public static List<String> getSlist(List<?> list,String fe){
		if(list == null || list.size() == 0){
			return null;
		}
		List<String> slist = new ArrayList<>();
		for(Object obj : list){
			Object str = PropertyUtil.getProperty(fe, obj);
			
			slist.add(str.toString());
		}
		return slist;
	}
	
	public static <T> Map<Object,List<T>> groupList(List<T> list,String prop){
		
		if(null != list){
			Map<Object,List<T>> map = new HashMap<>();
			for(T t : list){
				Object obj = PropertyUtil.getProperty(prop, t);
				List<T> mlist = map.get(obj);
				if(null == mlist){
					mlist = new ArrayList<>();
					map.put(obj, mlist);
				}
				mlist.add(t);
			}
			return map;
		}
		return null;
		
	}
	
	public static <T> List<T> intersectionList(List<T> s1,List<T> s2){
		List<T> list = new ArrayList<>();
		if(s1 == null || s2 == null){
			return list;
		} else {
			for(T t : s1){
				if(s2.contains(t)){
					list.add(t);
				}
			}
			return list;
		}
		
	}
}
