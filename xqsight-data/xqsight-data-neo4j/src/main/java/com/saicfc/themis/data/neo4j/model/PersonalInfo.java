package com.saicfc.themis.data.neo4j.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * Created by GTaurus on 2016/3/29.
 */
public class PersonalInfo implements Serializable {

    @NotNull(message = "name is invalid")
    private String name;
    @NotNull(message = "certType is invalid")
    @Pattern(regexp = "\\d{3}", message = "certType can only be 3 digits in length")
    private String certType;
    @NotNull(message = "certNo is invalid")
    private String certNo;
    @NotNull(message = "cellPhone is invalid")
    @Pattern(regexp = "\\d{11}", message = "cellPhone can only be 11 digits in length")
    private String cellPhone;
    @Pattern(regexp = "0[\\d]{2,3}-[\\d]{7,8}", message = "phone number is invalid, should be like '021-12345678'")
    private String phone;
    private String bankCardNo;
    /**
     * <pre>
     * PRE_HIGH_SCHOOL	高中以下
     * HIGH_SCHOOL	高中／中专
     * JUNIOR_COLLEGE	大专
     * UNDER_GRADUATE	本科
     * POST_GRADUATE	研究生
     * </pre>
     */
    @Pattern(regexp = "[PRE_HIGH_SCHOOL|HIGH_SCHOOL|JUNIOR_COLLEGE|UNDER_GRADUATE|POST_GRADUATE]{0,1}")
    private String diploma;
    /**
     * <pre>
     * SPINSTERHOOD	未婚
     * MARRIED	已婚
     * DIVORCED	离异
     * WIDOWED	丧偶
     * REMARRY	再婚
     * REMARRY_FORMER	复婚
     * </pre>
     */
    @Pattern(regexp = "[SPINSTERHOOD|MARRIED|DIVORCED|WIDOWED|REMARRY|REMARRY_FORMER]{0,1}")
    private String marriage;
    /* orgnization */
    private String companyName;
    /* physical contact */
    private String regAddress;
    private String homeAddress;
    private String companyAddress;
    private String contactAddress;
    /* social account */
    private String qq;
    private String email;
    private String wechat;
    /* personal relations */
    private PersonalInfo father;
    private PersonalInfo mother;
    private PersonalInfo child;
    private PersonalInfo spouse;
    private PersonalInfo otherRelative;
    private PersonalInfo other;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public PersonalInfo getFather() {
        return father;
    }

    public void setFather(PersonalInfo father) {
        this.father = father;
    }

    public PersonalInfo getMother() {
        return mother;
    }

    public void setMother(PersonalInfo mother) {
        this.mother = mother;
    }

    public PersonalInfo getChild() {
        return child;
    }

    public void setChild(PersonalInfo child) {
        this.child = child;
    }

    public PersonalInfo getSpouse() {
        return spouse;
    }

    public void setSpouse(PersonalInfo spouse) {
        this.spouse = spouse;
    }

    public PersonalInfo getOtherRelative() {
        return otherRelative;
    }

    public void setOtherRelative(PersonalInfo otherRelative) {
        this.otherRelative = otherRelative;
    }

    public PersonalInfo getOther() {
        return other;
    }

    public void setOther(PersonalInfo other) {
        this.other = other;
    }
}
