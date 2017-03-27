package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsModelService;
import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangganggang
 * @date 2017/03/27
 */
@RestController
@RequestMapping("/cms/model")
public class CmsModelController extends BaseController<CmsModelService,CmsModel,Long> {
}
