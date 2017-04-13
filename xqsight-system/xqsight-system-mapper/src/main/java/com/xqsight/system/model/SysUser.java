/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * <p>用户信息表实体类</p>
 * <p>Table: sys_user - --> SysUser</p>
 * <p>用户信息表</p>
 * @since 2017-04-06 09:56:36
 * @author wangganggang
 */
@Data
public class SysUser extends BaseModel{

	/** 主键 */
    private Long id;

    /** company_id - 归属公司 */
    private Long companyId;
    /** office_id - 所属部门 */
    private Long officeId;
    /** password - 登录密码 */
    private String password;
    /** salt - 随机数 */
    private String salt;
    /** user_name - 昵称 */
    private String userName;
    /** real_name - 真实姓名 */
    private String realName;
    /** user_code - 用户编号 */
    private String userCode;
    /** sex - 性别 0:未知 1:男 2:女 */
    private Byte sex;
    /** user_born - 生日 */
    private LocalDate userBorn;
    /** from_source - 来源 WECHAT  PC  MOBILE */
    private String fromSource;
    /** img_url - 图片地址 */
    private String imgUrl;
    /** cell_phone - 电话 */
    private String cellPhone;
    /** email - 邮箱 */
    private String email;

    @Override
    public Serializable getPK() {
        return this.id;
    }
}