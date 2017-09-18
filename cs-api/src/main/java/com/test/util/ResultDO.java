package com.test.util;

import java.io.Serializable;

public class ResultDO<T> implements Serializable {
	private static final long serialVersionUID = 5993286892322436568L;
	private boolean success = false;
	private String msg;
	private T module;

	public ResultDO() {

	}

	public ResultDO(boolean success) {
		super();
		this.success = success;
	}

	public ResultDO(String msg) {
		super();

		this.msg = msg;
	}

	public ResultDO(boolean success, T module) {
		super();

		this.success = success;
		this.module = module;
	}

	public ResultDO(boolean success, String msg, T module) {
		super();

		this.success = success;
		this.msg = msg;
		this.module = module;
	}

	/**
	 * 转换成json字符串
	 */
	public String convertToJsonString() {
		return new JsonResult(success, msg, module).toJsonString();
	}

	public boolean isSuccess() {
		return success;
	}

	public ResultDO<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResultDO<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getModule() {
		return module;
	}

	public ResultDO<T> setModule(T module) {
		this.module = module;
		return this;
	}
}
