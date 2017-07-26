/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.api.controller;

import com.tangchao.server.model.Message;
import com.tangchao.server.service.MessageService;
import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>消息 controller</p>
 * <p>Table: message - 消息</p>
 * @since 2017-07-25 06:40:28
 * @author wangganggang
 */
@RestController
@RequestMapping("/api/message")
public class MessageController extends BaseController<MessageService,Message,Long> {

}