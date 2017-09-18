package com.test.util;

import java.util.List;

public class DataPage<T> extends Paging {
	private static final long serialVersionUID = -7490360935699028447L;

	/**
	 * 分页获取的数据列表
	 */
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
