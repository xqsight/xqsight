package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangganggang
 * @date 2017/03/27
 */
@RestController
@RequestMapping("/cms/ad")
public class CmsAdController extends BaseController<CmsAdService,CmsAd,Long> {
}
