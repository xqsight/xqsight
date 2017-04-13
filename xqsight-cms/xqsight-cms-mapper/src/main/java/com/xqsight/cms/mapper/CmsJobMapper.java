/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.mapper;


import com.xqsight.cms.model.CmsJob;
import com.xqsight.common.base.dao.ICrudDao;
import com.xqsight.common.core.orm.Criterion;

import java.util.List;
import java.util.Map;


/**
 * <p>招聘表实现类service</p>
 * <p>Table: cms_job - 招聘表</p>
 * @since 2017-02-23 04:52:11
 * @author wangganggang
*/
public interface CmsJobMapper extends ICrudDao<CmsJob,Long> {

    List<Map> queryJob(Criterion criterion);
}