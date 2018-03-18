package com.lwx.usm.dto;

import java.io.Serializable;

public class SimplePage implements Serializable{

	private static final long serialVersionUID = 1L;

	private int pageSize;
	
	private int pageNo;
	
	private int totalCount;
	
	public SimplePage(){
		this.pageNo = 1;
		this.pageSize = 10;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
