/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.server.service.impl;

import com.tangchao.server.mapper.MessageMapper;
import com.tangchao.server.model.Message;
import com.tangchao.server.service.MessageService;
import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;


/**
 * <p>消息实现类 service</p>
 * <p>Table: message - 消息</p>
 * @since 2017-07-25 06:40:28
 * @author wangganggang
 */
@Service
public class MessageServiceImpl extends AbstractCrudService<MessageMapper,Message, Long> implements MessageService {

}