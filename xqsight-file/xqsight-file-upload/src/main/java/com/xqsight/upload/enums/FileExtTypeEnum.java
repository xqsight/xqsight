package com.xqsight.upload.enums;

/**
 * Created by user on 2016/6/7.
 */
public enum FileExtTypeEnum {
    /**
     *
     */
    IMAGE("IMAGE"),
    /**
     *
     */
    FLASH("FLASH"),
    /**
     *
     */
    MEDIA("MEDIA"),
    /**
     *
     */
    FILE("FILE");

    FileExtTypeEnum(String name) {
        this.name = name;
    }

    private String name;

    public String value() {
        return this.name;
    }
}
