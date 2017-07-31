package com.tangchao.api.controller;

import com.tangchao.constans.ReservateStatusEnum;
import com.tangchao.house.model.Appointment;
import com.tangchao.house.service.AppointmentService;
import com.tangchao.user.service.SmsService;
import com.xqsight.common.base.controller.BaseController;
import com.xqsight.common.exception.UnAuthcException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.security.annontation.AuthIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @AuthIgnore
    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object put(Appointment appointment) throws Exception {
        return super.put(appointment);
    }

    @AuthIgnore
    @Override
    @RequestMapping(value = "/logic/{id}", method = RequestMethod.DELETE)
    public Object logicDeleteById(Appointment appointment, @PathVariable Long id) {
        return super.logicDeleteById(appointment, id);
    }


    @AuthIgnore
    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @AuthIgnore
    @Override
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Object getPage() {
        return super.getPage();
    }


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
