package com.xqsight.model;

import lombok.Data;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@Data
public class LoginRet {
    private int Result;
    private String Message;
    private String Sid;
    private String Key;
    private String UserId;
    private String Ticket;
}
