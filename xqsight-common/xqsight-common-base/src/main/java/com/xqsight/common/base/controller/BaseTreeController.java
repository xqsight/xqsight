package com.xqsight.common.base.controller;

import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.support.PropertyFilterSupport;
import com.xqsight.common.model.AbstractTreeModel;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.support.TreeSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * @param <Service>
 * @param <Record>
 * @param <PK>
 * @author wangganggang
 * @Date 2017/3/23
 */
public class BaseTreeController<Service extends ICrudService<Record, PK>, Record extends AbstractTreeModel, PK extends Serializable> {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Service service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object put(Record record) throws Exception {
        prePut(record);

        int iRet = 0;
        if (record.getPK() != null && !"".equals(record.getPK())) {
            iRet = service.editById(record);
        } else {
            if (StringUtils.equals(record.getParentId(), "0")) {
                record.setParentIds(",0,");
            } else {
                Record parentArea = service.getById((PK) record.getParentId());
                record.setParentIds(parentArea.getParentIds() + record.getPK() + Constants.COMMA_SIGN_SPLIT_NAME);
            }
            service.add(record);
        }

        afterPut(record);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable PK id) throws Exception {
        preDelete(id);
        int iRet = service.removeById(id);
        afterDelete(id);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable PK id) {
        Record record = service.getById(id);
        return new BaseResult(record);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object getAll(HttpServletRequest request) {
        List<Record> records = service.getByFilters(getFilter(request));
        return new BaseResult(records);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public Object getTree() {
        List<Record> records = service.getAll();
        records = new TreeSupport<Record>().generateTree(records);
        return new BaseResult(records);
    }

    /**
     * 获取查询的参数
     *
     * @param request
     * @return
     */
    protected List<PropertyFilter> getFilter(HttpServletRequest request) {
        return PropertyFilterSupport.buildPropertyFilters(request);
    }

    protected void prePut(Record record) throws Exception {
    }

    protected void afterPut(Record record) throws Exception {
    }

    protected void preDelete(PK id) throws Exception {
    }

    protected void afterDelete(PK id) throws Exception {
    }
}
