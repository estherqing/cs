package com.test.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cg on 2015/11/12.
 */
public class DirNode implements Serializable {
    /**
     * 节点编号
     */
    private Long id;
    /**
     * 父节点编号
     */
    private Long pid;
    /**
     * 节点名称
     */
    private String title;
    /**
     * 是否展示节点
     */
    private Boolean isShow;
    /**
     * 子节点数
     */
    private Integer num;
    /**
     * 显示名称：title+(num)
     */
    private String name;
    /**
     * 该目录存放的文件类型（1：图片，2：视频）
     */
    private String docType;
    /**
     * 子节点
     */
    private List<DirNode> children = new ArrayList<DirNode>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public List<DirNode> getChildren() {
        return children;
    }

    public void setChildren(List<DirNode> childrens) {
        this.children = childrens;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

}
