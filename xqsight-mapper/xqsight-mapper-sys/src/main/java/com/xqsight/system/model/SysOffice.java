/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>机构表实体类</p>
 * <p>Table: sys_office - --> SysOffice</p>
 * <p>机构表</p>
 * @since 2017-04-05 05:17:04
 * @author wangganggang
 */
@Data
public class SysOffice extends BaseModel{

	/** 主键 */
    private Long officeId;

    /** area_id - 区域内码 */
    private Long areaId;
    /** parent_id - 父级id */
    private Long parentId;
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
    /** sort - 排序 */
    private Short sort;
    /** icon - 图标 */
    private String icon;

    @Override
    public Serializable getPK() {
        return this.officeId;
    }
}