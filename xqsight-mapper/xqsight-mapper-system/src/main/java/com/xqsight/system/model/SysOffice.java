/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.AbstractTreeModel;

import java.io.Serializable;


/**
 * <p>机构表实体类</p>
 * <p>Table: sys_office - --> SysOffice</p>
 * <p>机构表</p>
 * @since 2017-02-22 04:29:58
 * @author wangganggang
 */
public class SysOffice extends AbstractTreeModel<SysOffice> {

	/** 主键 */
    private Long officeId;

    /** area_id - 区域内码 */
    private Long areaId;
    /** parent_ids - 所有上级 */
    private String parentIds;
    /** office_name - 名称 */
    private String officeName;
    /** office_code - 编号 */
    private String officeCode;
    /** office_type - 类型 1:公司 2:部门 3:小组 4:其他 */
    private Byte officeType;
    /** address - 联系地址 */
    private String address;
    /** zip_code - 邮政编码 */
    private String zipCode;
    /** master - 负责人 */
    private String master;
    /** phone - 电话 */
    private String phone;
    /** fax - 传真 */
    private String fax;
    /** email - 邮箱 */
    private String email;

    public Long getOfficeId(){
        return this.officeId;
    }
    public void setOfficeId(Long officeId){
        this.officeId = officeId;
        super.setId("" + officeId);
    }
	public Long getAreaId(){
        return this.areaId;
    }
    public void setAreaId(Long areaId){
        this.areaId = areaId;
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getOfficeName(){
        return this.officeName;
    }
    public void setOfficeName(String officeName){
        this.officeName = officeName;
        super.setName(officeName);
    }
	public String getOfficeCode(){
        return this.officeCode;
    }
    public void setOfficeCode(String officeCode){
        this.officeCode = officeCode;
    }
	public Byte getOfficeType(){
        return this.officeType;
    }
    public void setOfficeType(Byte officeType){
        this.officeType = officeType;
    }
	public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
	public String getZipCode(){
        return this.zipCode;
    }
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
	public String getMaster(){
        return this.master;
    }
    public void setMaster(String master){
        this.master = master;
    }
	public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
	public String getFax(){
        return this.fax;
    }
    public void setFax(String fax){
        this.fax = fax;
    }
	public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public Serializable getPK() {
        return this.officeId;
    }
}