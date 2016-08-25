package com.xqsight.sys.model;

import com.xqsight.common.model.TreeBaseModel;

/**
 * Created by wangganggang on 16/6/16.
 */
public class SysOrg extends TreeBaseModel<SysOrg>{

    /** 组织Id **/
    private Long orgId;
    /** 组织名称 **/
    private String orgName;
    /** 组织类型 **/
    private String orgType;
    /** 排序 **/
    private int sort;
    /** 组织编号 **/
    private String orgCode;
    /** 自定义扩展码 **/
    private String customCode;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    @Override
    public Long getId() {
        return orgId;
    }

    @Override
    public String getName() {
        return orgName;
    }
}
