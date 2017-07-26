package com.tangchao.api.controller;

import com.tangchao.constans.ReservateStatusEnum;
import com.tangchao.house.model.Appointment;
import com.tangchao.house.service.AppointmentService;
import com.tangchao.house.service.SmsService;
import com.xqsight.common.base.controller.BaseController;
import com.xqsight.common.exception.UnAuthcException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午10:03
 */
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController extends BaseController<AppointmentService, Appointment, Long> {

    @Autowired
    private SmsService smsService;

    @Override
    protected void prePut(Appointment appointment) {
        String validateCode = request.getParameter("validCode");
        boolean isValidate = smsService.verifyCode(appointment.getTelphone(), validateCode);
        if (!isValidate) {
            throw new UnAuthcException(ErrorMessageConstants.ERROR_40004, "验证码错误");
        }
        //设置状态为申请
        appointment.setStatus((byte) ReservateStatusEnum.APP.value());
    }

    @Override
    protected void afterPut(Appointment appointment) {
        //TODO 发送消息，通知管家
    }


}
