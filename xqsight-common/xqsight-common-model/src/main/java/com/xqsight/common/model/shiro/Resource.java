package com.xqsight.common.model.shiro;

import com.xqsight.common.model.BaseModel;

import java.io.Serializable;

@Deprecated
public class Resource extends BaseModel {
    private Long         id;                            //编号
    private String       name;                          //资源名称
    private int          type; 							//资源类型
    private String       url;                           //资源路径
    private String       permission;                    //权限字符串
    private Long         parentId;                      //父编号
    private String       parentIds;                     //父编号列表
    private Boolean      available = Boolean.FALSE;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>type</tt>.
     * 
     * @return property value of type
     */
    public int getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     * 
     * @param type value to be assigned to property type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>url</tt>.
     * 
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     * 
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter method for property <tt>permission</tt>.
     * 
     * @return property value of permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Setter method for property <tt>permission</tt>.
     * 
     * @param permission value to be assigned to property permission
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Getter method for property <tt>parentId</tt>.
     * 
     * @return property value of parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Setter method for property <tt>parentId</tt>.
     * 
     * @param parentId value to be assigned to property parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Getter method for property <tt>parentIds</tt>.
     * 
     * @return property value of parentIds
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * Setter method for property <tt>parentIds</tt>.
     * 
     * @param parentIds value to be assigned to property parentIds
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * Getter method for property <tt>available</tt>.
     * 
     * @return property value of available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * Setter method for property <tt>available</tt>.
     * 
     * @param available value to be assigned to property available
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public boolean isRootNode() {
        return parentId == 0;
    }

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Resource{" + "id=" + id + ", name='" + name + '\'' + ", type=" + type + ", permission='" + permission + '\'' + ", parentId="
               + parentId + ", parentIds='" + parentIds + '\'' + ", available=" + available + '}';
    }

    @Override
    public Serializable getPK() {
        return this.id;
    }
}
