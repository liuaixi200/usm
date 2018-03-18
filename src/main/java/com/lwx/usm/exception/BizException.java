package com.lwx.usm.exception;

public class BizException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;
	
	public BizException(String code,String msg){
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public BizException(String code,String msg,Exception e){
		super(msg,e);
		this.code = code;
		this.msg = msg;
	}
	
	
	public BizException(String msg){
		super(msg);
		this.msg = msg;
	}
	
	public BizException(String msg,Exception e){
		super(msg, e);
		this.msg = msg;
	}
	
	public BizException(Exception e){
		super(e);

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
