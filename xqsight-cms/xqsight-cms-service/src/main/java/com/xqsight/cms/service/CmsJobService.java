/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.cms.model.CmsJob;
import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.core.orm.PropertyFilter;

import java.util.List;
import java.util.Map;

/**
* <p>招聘表 service</p>
* <p>Table: cms_job - 招聘表</p>
* @since 2017-04-06 02:34:52
* @author wangganggang
*/
public interface CmsJobService extends ICrudService<CmsJob, Long> {

    List<Map> queryJob(List<PropertyFilter> propertyFilters);
}