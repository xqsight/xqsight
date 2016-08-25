package com.xqsight.common.enums;

/**
 * Created by user on 2016/6/3.
 */
public enum UserFromSourceEnum {
    PC("PC"),
    MOBILE("MOBILE"),
    WECHAT("WECHAT");

    UserFromSourceEnum(String fromSource) {
        this.fromSource = fromSource;
    }

    private String fromSource;

    public String value(){
        return this.fromSource;
    }
}
