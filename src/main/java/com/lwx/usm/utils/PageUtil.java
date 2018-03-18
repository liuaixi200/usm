package com.lwx.usm.utils;

import java.util.ArrayList;
import java.util.List;

import com.lwx.usm.dto.SimplePage;

public class PageUtil {

	public static <T> List<T> page(List<T> list,SimplePage sp){
		if(null != list){
			int pageSize = sp.getPageSize();
			int pageNo = sp.getPageNo();
			int total = list.size();
			sp.setTotalCount(total);
			if((pageNo-1)*pageSize>total){
				//不处理
				return new ArrayList<>();
			} else if(pageNo*pageSize>total){
				return list.subList((pageNo-1)*pageSize, total);
			} else {
				return list.subList((pageNo-1)*pageSize, pageNo*pageSize);
			}
		}
		return new ArrayList<>();
	}
	
}
