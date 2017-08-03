package com.tangchao.api.controller;

import com.tangchao.constans.AppointmentStatusEnum;
import com.tangchao.house.model.Reservate;
import com.tangchao.house.service.ReservateService;
import com.tangchao.user.service.SmsService;
import com.xqsight.common.base.controller.BaseController;
import com.xqsight.common.exception.SmsSendException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午11:20
 */
@RestController
@RequestMapping("/api/reservate")
public class ReservateController extends BaseController<ReservateService, Reservate, Long> {

    @Autowired
    private SmsService smsService;

    @Override
    protected void prePut(Reservate reservate) {
        String validateCode = request.getParameter("validCode");
        boolean isValidate = smsService.verifyCode(reservate.getTelphone(), validateCode);
        if (!isValidate) {
            throw new SmsSendException(ErrorMessageConstants.ERROR_50002, "验证码错误");
        }
        //设置状态为申请
        reservate.setStatus((byte) AppointmentStatusEnum.APP.value());
    }

    @Override
    protected void afterPut(Reservate reservate) {
        //TODO 发送消息，通知管家
    }
}
