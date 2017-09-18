package com.test.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author: lijunjun Time: lizhijun1103@163.com Email: 2015-11-20
 */
public class ZaUserRole implements Serializable, Comparable<ZaUserRole> {
	private static final long serialVersionUID = 5993286892322436568L;

	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 姓名
	 */
	private String realname;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 分公司ID
	 */
	private Long companyId;
	/**
	 * 分公司名称
	 */
	private String companyName;
	/**
	 * 业务组ID 仅当为客户经理时有值。
	 */
	private Long businessGroupId;
	/**
	 * 分公司名称
	 */
	private String businessGroupName;
	/**
	 * 说明
	 */
	private String note;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 修改时间
	 */
	private Timestamp modifyTime;

	public ZaUserRole() {

	}

	// getters and setters
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinessGroupName() {
		return businessGroupName;
	}

	public void setBusinessGroupName(String businessGroupName) {
		this.businessGroupName = businessGroupName;
	}

	public Long getBusinessGroupId() {
		return this.businessGroupId;
	}

	public void setBusinessGroupId(Long businessGroupId) {
		this.businessGroupId = businessGroupId;
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
	public int compareTo(ZaUserRole o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}
}
