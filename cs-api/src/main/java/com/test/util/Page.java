package com.test.util;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pageNow = 1; // 当前页
	private Integer pageSize = 20; // 页大小
	private Integer pageCount = 0; // 总页数
	private Integer dataCount = 0; // 数据总行数
	private Map<String, Object> conditions = new HashMap<String, Object>(); // 分页查询其他条件
	private String sortField; // 排序字段
	private List<T> datas = new ArrayList<T>(); // 分页查询结果

	public Page() {
		super();
	}

	public Page(Integer pageNow, Integer pageSize) {
		super();

		if (pageNow != null && pageNow > 0) {
			this.pageNow = pageNow;
		}
		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 是否保留字
	 */
	private boolean isKeyWord(String key) {
		return key.equals("pageNow") || key.equals("pageSize") || key.equals("pageCount") || key.equals("dataCount")
				|| key.equals("sortField");

	}

	/**
	 * 添加一个条件
	 */
	public Page<T> addCondition(String key, Object value) {
		if (isKeyWord(key)) {
			return null;
		}
		if ("".equals(value)) {
			value = null;
		}

		this.dataCount = null;
		this.datas = null;
		conditions.put(key, value);
		return this;
	}

	/**
	 * 添加多个条件
	 */
	public void addConditions(Map<String, Object> conditions) {
		if (conditions != null && conditions.size() > 0) {
			Iterator<Entry<String, Object>> conditionsIt = conditions.entrySet().iterator();
			while (conditionsIt.hasNext()) {
				Entry<String, Object> condition = conditionsIt.next();
				String key = condition.getKey();
				Object value = condition.getValue();

				addCondition(key, value);
			}
		}
	}

	/**
	 * 删除一个条件
	 */
	public Object removeCondition(String key) {
		if (isKeyWord(key)) {
			return null;
		}

		this.dataCount = null;
		this.datas = null;
		return conditions.remove(key);
	}

	/**
	 * 获取一个条件
	 */
	public Object getCondition(String key) {
		if (isKeyWord(key)) {
			return null;
		}

		return conditions.get(key);
	}

	/**
	 * 将当前页、页大小、排序放入到搜索条件中
	 */
	public Map<String, Object> getQueryMap() {
		Map<String, Object> map = new HashMap<String, Object>(conditions);
		map.put("sortField", sortField);
		map.put("limitStart", (pageNow - 1) * pageSize);
		map.put("limitNum", pageSize);

		return map;
	}

	/**
	 * 转换成一个map
	 */
	public Map<String, Object> convertToMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNow", pageNow);
		map.put("pageSize", pageSize);
		map.put("pageCount", pageCount);
		map.put("dataCount", dataCount);
		map.put("conditions", conditions);
		map.put("sortField", sortField);
		map.put("datas", datas);

		return map;
	}

	/**
	 * 转换成json字符串
	 */
	public String convertToJsonString() {
		if (datas != null && datas.size() > 0) {
			return new JsonResult(true, null, convertToMap()).toJsonString();
		} else {
			return new JsonResult("未查询到符合条件的数据").setSuccess(true).toJsonString();
		}
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		if (pageNow == null || pageNow < 0) {
			pageNow = 0;
		}
		this.pageNow = pageNow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize < 0) {
			pageSize = 0;
		}
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		if (pageCount == null || pageCount < 0) {
			pageCount = 0;
		}
		this.pageCount = pageCount;
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		if (dataCount == null || dataCount < 0) {
			dataCount = 0;
		}
		this.dataCount = dataCount;

		if (pageSize > 0) {
			pageCount = this.dataCount / pageSize;
			if (this.dataCount % pageSize != 0) {
				pageCount++;
			}
		} else {
			pageCount = 0;
		}

		if (pageCount == 0) {
			pageNow = 1;
			pageCount = 1;
			this.dataCount = 0;
			datas = new ArrayList<T>();
		} else if (pageNow > pageCount) {
			pageNow = pageCount;
		}
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = new HashMap<String, Object>();
		if (conditions != null && conditions.size() > 0) {
			Iterator<Entry<String, Object>> conditionsIt = conditions.entrySet().iterator();
			while (conditionsIt.hasNext()) {
				Entry<String, Object> condition = conditionsIt.next();
				String key = condition.getKey();
				Object value = condition.getValue();

				addCondition(key, value);
			}
		}
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		if ("".equals(sortField)) {
			sortField = null;
		}
		this.sortField = sortField;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		if (datas == null) {
			datas = new ArrayList<T>();
		}
		this.datas = datas;
	}

}
