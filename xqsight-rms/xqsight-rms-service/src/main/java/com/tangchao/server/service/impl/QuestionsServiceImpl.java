/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.server.service.impl;

import com.tangchao.server.mapper.QuestionsMapper;
import com.tangchao.server.model.Questions;
import com.tangchao.server.service.QuestionsService;
import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;


/**
 * <p>问答实现类 service</p>
 * <p>Table: questions - 问答</p>
 * @since 2017-07-25 06:40:36
 * @author wangganggang
 */
@Service
public class QuestionsServiceImpl extends AbstractCrudService<QuestionsMapper,Questions, Long> implements QuestionsService {

}