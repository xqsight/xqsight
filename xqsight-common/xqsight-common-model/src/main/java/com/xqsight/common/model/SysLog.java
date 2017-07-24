package com.xqsight.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangganggang
 * @date 2017年07月24日 11:04
 */
@Data
public class SysLog extends BaseModel {

    private Long id;
    //用户名
    private String username;
    //用户操作
    private String operation;
    //请求方法
    private String method;
    //请求参数
    private String params;
    //执行时长(毫秒)
    private Long time;
    //IP地址
    private String ip;

    @Override
    public Serializable getPK() {
        return this.id;
    }
}
