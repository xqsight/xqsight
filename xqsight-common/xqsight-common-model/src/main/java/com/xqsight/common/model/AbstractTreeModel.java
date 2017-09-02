package com.xqsight.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author wangganggang
 * @date 2017/3/23
 */
@Data
public abstract class AbstractTreeModel<T> extends BaseModel {

    private String id;
    /** parent_id - 父级id */
    private String parentId;
    /** parent_id - 父级ids */
    private String parentIds;
    private String name;
    private String text;
    /** icon - 图标 */
    private String icon;
    /** sort - 排序 */
    private Short sort;
    private List<T> children;

    public void setName(String name) {
        this.name = name;
        this.text = name;
    }
}
