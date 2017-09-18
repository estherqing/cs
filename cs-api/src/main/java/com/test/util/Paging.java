package com.test.util;

import java.io.Serializable;

public class Paging implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195443430620757064L;

	private Integer start;

	private Integer end;

	private Integer pageSize;

	private Integer currentPage;

	private Integer totalItem;

	private String orderBy;

	public Paging() {

	}

	public Paging(Integer start, Integer end) {
		if (start == null)
			start = 0;
		if (end == null)
			end = 10;
		this.start = start;
		this.end = end;
		this.currentPage = start / (end - start + 1);
		this.pageSize = end - start + 1;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPageSize() {
		if (start != null && end != null) {
			pageSize = end - start + 1;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		if (start != null && pageSize != null) {
			currentPage = start / pageSize;
		}
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}