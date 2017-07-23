package com.xqsight.common.model.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午9:19
 */
@Data
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    /* 用户ID */
    private Long userId;
    /*token */
    private String token;
    /*过期时间 */
    private Date expireTime;
    /*更新时间 */
    private Date updateTime;
}
