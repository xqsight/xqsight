package com.tangchao.model;

import lombok.Data;

/**
 * Created by wangganggang on 2017/7/17.
 *
 */
@Data
public class BestHouseVO {
    /*房间ID*/
    private Long id;
    /*小区名称*/
    private String projectName;
    /*小区地址*/
    private String projectAddr;
    /*房间大小*/
    private Float roomSpace;
    /*意向租金*/
    private Double rental;
    /*朝向*/
    private String orientation;
    /*附属结构*/
    private String structure;
    /*付款方式*/
    private String payWay;
}
