package com.test.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author esther
 * @create 2017-09-11 11:16
 * 常见问题类别
 */

public class CommonQuestionType implements Serializable {
    private Integer id; // 主键ID
    private String name; // 问题类别名称
    private String remark; // 备注
    private Long userId; // 录入人ID
    private String userName; // 录入人手机号
    private String userRealName; // 录入人姓名
    private Short status; // 状态，1启用，0禁用
    private Date createTime; // 创建时间
    private Date modifyTime; // 修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
