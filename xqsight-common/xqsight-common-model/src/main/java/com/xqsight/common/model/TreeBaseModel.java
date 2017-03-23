package com.xqsight.common.model;

import java.util.List;

/**
 * Created by wangganggang on 16/6/15.
 */
public abstract class TreeBaseModel<T> extends BaseModel {

    private String id = (String) getPK();
    /** parent_id - 父级id */
    private String parentId;
    private String name;
    private String text;
    /** icon - 图标 */
    private String icon;
    /** sort - 排序 */
    private Short sort;
    private List<T> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.text = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
