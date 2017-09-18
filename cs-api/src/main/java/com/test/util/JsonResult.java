package com.test.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.Serializable;

/* Author:  lijunjun
 *   Time:  2013-5-7
 *  Email:  lizhijun1103@163.com
 */
public class JsonResult implements Serializable {
	private static final long serialVersionUID = 5993286892322436568L;

	private boolean success = false;
	private String message = "";
	private Object value;

	public JsonResult() {
		super();
	}

	public JsonResult(boolean success) {
		super();
		this.success = success;
	}

	public JsonResult(String message) {
		super();
		this.message = message;
	}

	public JsonResult(boolean success, String message, Object value) {
		super();
		this.success = success;
		this.message = message;
		this.value = value;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public JsonResult setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMessage() {
		return this.message;
	}

	public JsonResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getValue() {
		return this.value;
	}

	public JsonResult setValue(Object value) {
		this.value = value;
		return this;
	}

	public String toJsonString() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (Exception e) {
			return "{\"success\":\"false\",\"message\":\"Fail to get json string from object.\"}";
		}
	}
}
