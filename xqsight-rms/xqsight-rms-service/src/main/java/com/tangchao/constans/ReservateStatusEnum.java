package com.tangchao.constans;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午10:26
 */
public enum ReservateStatusEnum {

    APP(0), APV(1), PAY_AMOUNT(2), APP_SUCCESS(3),APP_CANCEL(4);

    private int status;

    ReservateStatusEnum(int status) {
        this.status = status;
    }

    public int value() {
        return status;
    }
}
