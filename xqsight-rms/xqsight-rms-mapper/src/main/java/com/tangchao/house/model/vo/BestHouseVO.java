package com.tangchao.house.model.vo;

import lombok.Data;

/**
 * Created by wangganggang on 2017/7/17.
 *
 */
@Data
public class BestHouseVO {
    /*房间ID*/
    private String id;
    /*小区名称*/
    private String projectName;
    /*小区地址*/
    private String projectAddr;
    /*房间编号*/
    private String houseNo;
    /*房间数*/
    private Integer decoraStrucRoomNum;
    /*厅数*/
    private Integer decoraStrucCusspacNum;
    /*房间大小*/
    private Float space;
    /*楼层*/
    private Integer houseFloor;
    /*意向租金*/
    private Double rental;
    /*简短描述*/
    private String renshortDesctal;
    /*地理位置*/
    private String shortLocation;
    /*支付方式*/
    private String payWay;
    /*朝向*/
    private String orientation;
    /*出租方式*/
    private Integer rentType;

}
