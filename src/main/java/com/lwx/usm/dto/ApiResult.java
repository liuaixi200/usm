package com.lwx.usm.dto;

import java.io.Serializable;

import com.lwx.usm.utils.Constant;

public class ApiResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean success;
	
	private String code;
	
	private String msg;
	
	private T result;
	
	public ApiResult(){
		this.success = true;
		this.code = Constant.SUCCESS;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
