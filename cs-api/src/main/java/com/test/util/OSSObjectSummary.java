package com.test.util;

/**
 * oss object 摘要信息
 */
public class OSSObjectSummary {
	private String key; // Object的Key
	private String lastModified; // 最后修改时间
	private String size; // Object的文件字节数

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
