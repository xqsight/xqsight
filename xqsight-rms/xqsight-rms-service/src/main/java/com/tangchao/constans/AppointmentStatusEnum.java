package com.tangchao.constans;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午10:26
 */
public enum AppointmentStatusEnum {

    APP(0), APV(1), APP_SUCCESS(2), APP_CANCEL(3);

    private int status;

    AppointmentStatusEnum(int status) {
        this.status = status;
    }

    public int value() {
        return status;
    }
}
