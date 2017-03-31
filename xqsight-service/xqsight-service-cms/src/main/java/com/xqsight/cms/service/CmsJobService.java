/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.cms.mapper.CmsJobMapper;
import com.xqsight.cms.model.CmsJob;
import com.xqsight.cms.support.GenerateTemplate;
import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.service.DefaultEntityService;
import com.xqsight.common.freemarker.TemplateEngineException;
import com.xqsight.common.utils.MapKeyHandle;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>招聘表实现类service</p>
 * <p>Table: cms_job - 招聘表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:52:11
 */
@Service
public class CmsJobService extends DefaultEntityService<CmsJob, Long> {

    @Autowired
    private CmsJobMapper cmsJobMapper;

    @Autowired
    private GenerateTemplate generateTemplate;

    @Override
    protected Dao<CmsJob, Long> getDao() {
        return cmsJobMapper;
    }

    public void generateHtmlContent() throws TemplateEngineException {

        Map modelMap = new HashMap();
        List<Map> jobs = cmsJobMapper.queryJob(new Criterion());
        Map position = new HashMap();

        List<Map> jobMaps = jobs.parallelStream().map(map -> {
            position.put(MapUtils.getString(map, "position_id"),MapUtils.getString(map, "position_name"));
            return MapKeyHandle.keyToJavaProperty(map);
        }).collect(Collectors.toList());



        modelMap.put("position", position);
        modelMap.put("jobs", jobMaps);
        generateTemplate.generate(modelMap, "template/joinus.html", "joinus.html");
    }
}