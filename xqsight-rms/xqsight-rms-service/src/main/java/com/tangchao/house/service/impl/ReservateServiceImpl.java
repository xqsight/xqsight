package com.tangchao.house.service.impl;

import com.tangchao.house.mapper.ReservateMapper;
import com.tangchao.house.model.Reservate;
import com.tangchao.house.service.ReservateService;
import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午11:17
 */
@Service
public class ReservateServiceImpl extends AbstractCrudService<ReservateMapper,Reservate,Long> implements ReservateService {
}
