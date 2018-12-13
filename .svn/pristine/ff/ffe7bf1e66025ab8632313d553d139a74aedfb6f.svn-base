package com.mibo.common.result;

import com.mibo.common.constant.Global;

public class Response {

	private Integer code = Global.CODE_SUCCESS;

	private String msg;
	private Object data;

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Response(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Response() {
	}

	public Response(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}