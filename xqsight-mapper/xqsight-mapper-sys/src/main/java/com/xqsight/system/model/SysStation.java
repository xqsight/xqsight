/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>岗位信息表实体类</p>
 * <p>Table: sys_station - --> SysStation</p>
 * <p>岗位信息表</p>
 * @since 2017-04-05 05:17:15
 * @author wangganggang
 */
@Data
public class SysStation extends BaseModel{

	/** 主键 */
    private Long stationId;

    /** office_id - 机构内码 */
    private Long officeId;
    /** station_name - 岗位名称 */
    private String stationName;
    /** station_code - 岗位编号 */
    private String stationCode;
    /** station_type - 岗位类型 */
    private Byte stationType;

    @Override
    public Serializable getPK() {
        return this.stationId;
    }
}