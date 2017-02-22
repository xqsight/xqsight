/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.UserBaseModel;


/**
 * <p>用户登陆表实体类</p>
 * <p>Table: sys_login - --> SysLogin</p>
 * <p>用户登陆表</p>
 * @since 2017-02-22 04:29:47
 * @author wangganggang
 */
public class SysLogin extends UserBaseModel{

    /** status - 用户状态 0:正常 1:查封 2:待审核 */
    private Byte status;

	public Byte getStatus(){
        return this.status;
    }
    public void setStatus(Byte status){
        this.status = status;
    }

}