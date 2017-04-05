package com.xqsight.common.model.shiro;

import com.xqsight.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

@Deprecated
public class Role extends BaseModel {
    private Long id;                        //编号
    private String role;                      //角色标识 程序中判断使用,如"admin"
    private String description;               //角色描述,UI界面显示使用
    private List<Long> resourceIds;               //拥有的资源
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

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
     * Getter method for property <tt>role</tt>.
     *
     * @return property value of role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter method for property <tt>role</tt>.
     *
     * @param role value to be assigned to property role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for property <tt>description</tt>.
     *
     * @param description value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for property <tt>resourceIds</tt>.
     *
     * @return property value of resourceIds
     */
    public List<Long> getResourceIds() {
        return resourceIds;
    }

    /**
     * Setter method for property <tt>resourceIds</tt>.
     *
     * @param resourceIds value to be assigned to property resourceIds
     */
    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
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

    public String getResourceIdsStr() {
        if (resourceIds == null || resourceIds.size() < 1) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for (Long resourceId : resourceIds) {
            s.append(resourceId);
            s.append(",");
        }
        return s.toString();
    }

    public void setResourceIdsStr(String resourceIdsStr) {
        if (resourceIdsStr == null || "".equals(resourceIdsStr)) {
            return;
        }
        String[] resourceIdStrs = resourceIdsStr.split(",");
        for (String resourceIdStr : resourceIdStrs) {
            if (resourceIdStr == null || "".equals(resourceIdStr)) {
                continue;
            }
            getResourceIds().add(Long.valueOf(resourceIdStr));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", role='" + role + '\'' + ", description='" + description + '\'' + ", resourceIds=" + resourceIds
                + ", available=" + available + '}';
    }

    @Override
    public Serializable getPK() {
        return this.id;
    }
}
