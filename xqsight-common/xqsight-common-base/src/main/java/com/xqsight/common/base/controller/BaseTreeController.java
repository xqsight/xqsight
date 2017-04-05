package com.xqsight.common.base.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.support.PropertyFilterSupport;
import com.xqsight.common.model.AbstractTreeModel;
import com.xqsight.common.model.BaseModel;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <Service>
 * @param <Record>
 * @param <PK>
 * @author wangganggang
 * @Date 2017/3/23
 */
public class BaseTreeController<Service extends ICrudService<Record, PK>, Record extends AbstractTreeModel, PK extends Serializable> {

    @Autowired
    protected Service service;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object save(Record record) {
        if (StringUtils.equals(record.getParentId(), "0")) {
            record.setParentIds(",0,");
        } else {
            Record parentArea = service.getById((PK) record.getParentId());
            record.setParentIds(parentArea.getParentIds() + record.getPK() + Constants.COMMA_SIGN_SPLIT_NAME);
        }
        int iRet = service.add(record);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object update(Record record) {
        int iRet = service.editById(record);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable PK id) {
        int iRet = service.removeById(id);
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

    /**
     * 获取查询的参数
     *
     * @param request
     * @return
     */
    protected List<PropertyFilter> getFilter(HttpServletRequest request){
        return PropertyFilterSupport.buildPropertyFilters(request);
    }
}
