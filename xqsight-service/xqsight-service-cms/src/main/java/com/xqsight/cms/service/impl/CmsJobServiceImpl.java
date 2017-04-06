/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsJob;
import com.xqsight.cms.service.CmsJobService;
import com.xqsight.cms.mapper.CmsJobMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>招聘表实现类 service</p>
 * <p>Table: cms_job - 招聘表</p>
 * @since 2017-04-06 02:34:52
 * @author wangganggang
 */
@Service
public class CmsJobServiceImpl extends AbstractCrudService<CmsJobMapper,CmsJob, Long> implements CmsJobService {

    @Override
    public List<Map> queryJob(List<PropertyFilter> propertyFilters) {
        return this.dao.queryJob(new Criterion(propertyFilters));
    }
}