package com.tangchao.constans;

/**
 * @author wangganggang
 * @date 2017年07月28日 下午11:14
 */
public enum RentTypeEnum {

    RENT_ROOM(0), RENT_HOUSE(1);

    private Integer value;

    RentTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }
}
