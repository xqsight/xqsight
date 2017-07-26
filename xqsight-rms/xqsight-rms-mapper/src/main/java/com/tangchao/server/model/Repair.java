/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.server.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>保修实体类</p>
 * <p>Table: repair - --> Repair</p>
 * <p>保修</p>
 * @since 2017-07-25 06:41:03
 * @author wangganggang
 */
@Data
public class Repair extends BaseModel{

	/** 主键 */
    private Long id;

    /** associate_type - 0:房屋，1: 房间 */
    private Byte associateType;
    /** associate_id - 关联Id */
    private String associateId;
    /** user_id - 报修人ID */
    private String userId;
    /** user_name - 报修人 */
    private String userName;
    /** user_mobile - 报修电话 */
    private String userMobile;
    /** repair_mobile - 报修联系电话 */
    private String repairMobile;
    /** expect_repair_time - 期望维修时间 */
    private String expectRepairTime;
    /** contract_id - 合同号 */
    private String contractId;
    /** description - 报修描述 */
    private String description;
    /** keeper - 管家 */
    private String keeper;
    /** keeper_mobile - 管家电话 */
    private String keeperMobile;
    /** status - 状态 */
    private Byte status;

    @Override
    public Serializable getPK() {
        return this.id;
    }
}