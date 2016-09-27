package com.saicfc.themis.data.neo4j.model;

/**
 * acording to GA/T 517-2004
 * <p/>
 * Created by GTaurus on 2016/3/29.
 */
public enum CertType {
    /* GA/T 517-2004 */
    /**
     * 大陆身份证
     */
    NATIONAL_ID("111"),
    /**
     * 中华人民解放军军官证
     */
    MILITARY_OFFICER("114"),
    /**
     * 驾驶证
     */
    DRIVER("335"),
    /**
     * 大陆护照
     */
    PASSPORT("414"),
    /**
     * 香港护照
     */
    PASSPORT_HK("420"),
    /**
     * 澳门护照
     */
    PASSPORT_MC("421"),
    /**
     * 台湾居民往来大陆通行证
     */
    MAINLAND_TRAVEL_PERMIT_TW("511"),
    /**
     * 港澳居民往来大陆通行证
     */
    MAINLAND_TRAVEL_PERMIT_HM("516"),

    /* NOT GA/T 517-2004 */
    /**
     * 香港居民身份证
     */
    RESIDENT_HK("RHK"),
    /**
     * 澳门居民身份证
     */
    RESIDENT_MC("RMC"),
    /**
     * 台湾居民身份证
     */
    RESIDENT_TW("RTW"),
    /**
     * 中华人民解放军士兵证
     */
    MILITARY_SOLDIER("MSD"),
    /**
     * 中华人民解放军文职干部证
     */
    MILITARY_CIVILIAN_STAFF("MCS"),
    /**
     * 组织机构号
     */
    ORG_CODE("ORG"),;

    CertType(String certType) {
        this.certType = certType;
    }

    public String type() {
        return this.certType;
    }

    private String certType;
}
