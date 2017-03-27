package com.xqsight.cms.service;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.common.base.service.ICrudService;

/**
 * @author wangganggang
 * @date 2017/03/27
 */
public interface CmsModelService extends ICrudService<CmsModel, Long> {
    void saveTest(CmsModel cmsModel) throws Exception;
}
