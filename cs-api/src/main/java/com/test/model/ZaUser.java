package com.test.model;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Author: lijunjun Time: lizhijun1103@163.com Email: 2015-11-18
 */
public class ZaUser implements Serializable, Comparable<ZaUser> {
	private static final long serialVersionUID = 5993286892322436568L;
	/**
	 * 主ID
	 */
	private Long uid;
	/**
	 * 用户名，现统一为手机号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 密码加密盐值
	 */
	private String salt;
	/**
	 * 姓名
	 */
	private String realname;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 保留字段，特殊权限列表，英文逗号分隔
	 */
	private String permissions;
	/**
	 * 保留字段，用户级别
	 */
	private Long level;
	/**
	 * 分公司ID，因为使用场景较多，故加之
	 */
	private Long companyId;
	/**
	 * 所属部门ID
	 */
	private Long departmentId;
	/**
	 * 所属业务组ID
	 */
	private Long bzGroupId;
	/**
	 * 所属分公司名称
	 */
	private String companyName;
	/**
	 * 所属部门名称
	 */
	private String departmentName;
	/**
	 * 所属业务组名称
	 */
	private String bzGroupName;
	/**
	 * 省份code(别问我为什么是 String 而不是Long，我也不知道)
	 */
	private String addrProvince;
	/**
	 * 城市code
	 */
	private String addrCity;
	/**
	 * 区域code
	 */
	private String addrArea;
	/**
	 * 详情地址，带上省市区
	 */
	private String addrDetail;
	/**
	 * 当前状态：NORMAL,LOCKED,DEAD
	 */
	private String status;
	/**
	 * 注册时间
	 */
	private Timestamp registerTime;
	/**
	 * 最近修改时间
	 */
	private Timestamp modifyTime;
	/**
	 * 上次登录时间
	 */
	private Timestamp lastLoginTime;

	/**
	 * 角色列表
	 */
	private List<ZaRole> roleList;

	/**
	 * 用户角色列表
	 */
	private List<ZaUserRole> userRoleList;

	/**
	 * 用户系统范围
	 */
	private String sysType;

	/**
	 * 用户编号
	 */
	private String userCode;

	private Long sex;

	private Long age;

	 /**
	  * 外部资产合作伙伴名称
	  */
	private String externalAssetsPartnerName;
	/**
	 * 外部资产合作伙伴ID
	 */
	private Long externalAssetsPartnerId;

	/**
	 * 业务类型编码，新车还是二手车;1:新车,2:二手车,3:贴息,4:滴滴
	 */
	private String carTypeKey;
	/**
	 * 合作银行id
	 */
	private Long coBankId;

	public String getCarTypeKey() {
		return carTypeKey;
	}

	public void setCarTypeKey(String carTypeKey) {
		this.carTypeKey = carTypeKey;
	}

	public ZaUser() {

	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	// getters and setters
	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Long getLevel() {
		return this.level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrArea() {
		return addrArea;
	}

	public void setAddrArea(String addrArea) {
		this.addrArea = addrArea;
	}

	public String getAddrDetail() {
		return this.addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<ZaRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<ZaRole> roleList) {
		this.roleList = roleList;
	}

	public List<ZaUserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<ZaUserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public Long getBzGroupId() {
		return bzGroupId;
	}

	public void setBzGroupId(Long bzGroupId) {
		this.bzGroupId = bzGroupId;
	}

	public String getBzGroupName() {
		return bzGroupName;
	}

	public void setBzGroupName(String bzGroupName) {
		this.bzGroupName = bzGroupName;
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
	public int compareTo(ZaUser o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}

	public String getExternalAssetsPartnerName() {
		return externalAssetsPartnerName;
	}

	public void setExternalAssetsPartnerName(String externalAssetsPartnerName) {
		this.externalAssetsPartnerName = externalAssetsPartnerName;
	}

	public Long getExternalAssetsPartnerId() {
		return externalAssetsPartnerId;
	}

	public void setExternalAssetsPartnerId(Long externalAssetsPartnerId) {
		this.externalAssetsPartnerId = externalAssetsPartnerId;
	}

	public Long getCoBankId() {
		return coBankId;
	}

	public void setCoBankId(Long coBankId) {
		this.coBankId = coBankId;
	}
}
