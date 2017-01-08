package com.xqsight.system.enums;

/**
 * Created by wangganggang on 17/1/7.
 */
public enum MenuTypeEnum {
    MENU(0),FUNTION(1);

    private int value;

    MenuTypeEnum(int value){
        this.value = value;
    }

    public int getValuel() {
        return value;
    }
}
