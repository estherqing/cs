package com.test.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/* Author:  lijunjun
 *   Time:  lizhijun1103@163.com
 *  Email:  2015-11-10
 */
public class ZaRole implements Serializable, Comparable<ZaRole> {
	private static final long serialVersionUID = 5993286892322436568L;
	// 主ID
	private Long id;
	// 角色名称
	private String name;
	// 说明
	private String note;
	// 创建时间
	private Timestamp createTime;
	// 修改时间
	private Timestamp modifyTime;
	// 使用状态
	private Long state;
	// 角色编码
	private String roleCode;

	public ZaRole() {

	}

	// getters and setters

	public Long getId() {
		return this.id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public int compareTo(ZaRole o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}
}
