package com.tangchao.house.service.impl;

import com.tangchao.house.mapper.AppointmentMapper;
import com.tangchao.house.model.Appointment;
import com.tangchao.house.service.AppointmentService;
import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午10:01
 */
@Service
public class AppointmentServiceImpl extends AbstractCrudService<AppointmentMapper,Appointment, Long> implements AppointmentService {
}
