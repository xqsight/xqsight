/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.api.controller;

import com.tangchao.server.model.Questions;
import com.tangchao.server.service.QuestionsService;
import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>问答 controller</p>
 * <p>Table: questions - 问答</p>
 * @since 2017-07-25 06:40:36
 * @author wangganggang
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController extends BaseController<QuestionsService,Questions,Long> {

}