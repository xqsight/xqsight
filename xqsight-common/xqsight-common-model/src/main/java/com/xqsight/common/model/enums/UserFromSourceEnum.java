package com.xqsight.common.model.enums;

/**
 * Created by user on 2016/6/3.
 */
@Deprecated
public enum UserFromSourceEnum {
    PC("0"),
    MOBILE("1"),
    WECHAT("2");

    UserFromSourceEnum(String fromSource) {
        this.fromSource = fromSource;
    }

    private String fromSource;

    public String value() {
        return this.fromSource;
    }
}
