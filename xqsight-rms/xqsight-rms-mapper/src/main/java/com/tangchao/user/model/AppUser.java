/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.user.model;

import com.xqsight.common.model.BaseModel;
import com.xqsight.common.model.shiro.BaseUserModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * <p>实体类</p>
 * <p>Table: app_user - --> AppUser</p>
 * <p></p>
 * @since 2017-07-31 10:07:33
 * @author wangganggang
 */
@Data
public class AppUser extends BaseUserModel{

    /** 主键 */
    private Long id;

    /** real_name - 真实姓名 */
    private String realName;
    /** user_code - 用户编号 */
    private String userCode;
    /** sex - 性别 0:未知 1:男 2:女 */
    private Byte sex;
    /** identity_code - 身份证号码 */
    private String identityCode;
    /** identity_front_pic - 身份证正面 */
    private String identityFrontPic;
    /** identity_back_pic - 身份证反面 */
    private String identityBackPic;
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