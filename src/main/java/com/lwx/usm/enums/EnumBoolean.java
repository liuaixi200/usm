package com.lwx.usm.enums;

/**
 * @Author liuax01
 * @Date 2018/1/23 19:51
 */
public enum EnumBoolean {

	YES("1"), NO("0"),
	;

	private String code;

	private EnumBoolean(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
