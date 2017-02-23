/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>用户信息表实体类</p>
 * <p>Table: sys_user - --> SysUser</p>
 * <p>用户信息表</p>
 * @since 2017-02-22 04:31:43
 * @author wangganggang
 */
public class SysUser extends SysLogin{

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
    private Date userBorn;
    /** from_source - 来源 WECHAT  PC  MOBILE */
    private String fromSource;
    /** img_url - 图片地址 */
    private String imgUrl;
    /** cell_phone - 电话 */
    private String cellPhone;
    /** email - 邮箱 */
    private String email;

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
	public Long getCompanyId(){
        return this.companyId;
    }
    public void setCompanyId(Long companyId){
        this.companyId = companyId;
    }
	public Long getOfficeId(){
        return this.officeId;
    }
    public void setOfficeId(Long officeId){
        this.officeId = officeId;
    }
	public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
	public String getSalt(){
        return this.salt;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }
	public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
	public String getRealName(){
        return this.realName;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
	public String getUserCode(){
        return this.userCode;
    }
    public void setUserCode(String userCode){
        this.userCode = userCode;
    }
	public Byte getSex(){
        return this.sex;
    }
    public void setSex(Byte sex){
        this.sex = sex;
    }
	public Date getUserBorn(){
        return this.userBorn;
    }
    public void setUserBorn(Date userBorn){
        this.userBorn = userBorn;
    }
	public String getFromSource(){
        return this.fromSource;
    }
    public void setFromSource(String fromSource){
        this.fromSource = fromSource;
    }
	public String getImgUrl(){
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
	public String getCellPhone(){
        return this.cellPhone;
    }
    public void setCellPhone(String cellPhone){
        this.cellPhone = cellPhone;
    }
	public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public Serializable getPK() {
        return this.id;
    }
}