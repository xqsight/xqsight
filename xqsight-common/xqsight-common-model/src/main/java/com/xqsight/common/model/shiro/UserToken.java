package com.xqsight.common.model.shiro;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午9:19
 */
@Data
public class UserToken extends BaseModel {
    private static final long serialVersionUID = 1L;

    /* 用户ID */
    private Long userId;
    /*token */
    private String token;
    /*过期时间 */
    private LocalDate expireTime;

    @Override
    public Serializable getPK() {
        return this.userId;
    }
}
