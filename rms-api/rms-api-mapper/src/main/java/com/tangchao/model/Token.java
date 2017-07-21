package com.tangchao.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangganggang
 * @date 2017年07月21日 16:31
 */
@Data
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 用户ID */
    private String userId;
    /*token */
    private String token;
    /*过期时间 */
    private Date expireTime;
    /*更新时间 */
    private Date updateTime;
}
