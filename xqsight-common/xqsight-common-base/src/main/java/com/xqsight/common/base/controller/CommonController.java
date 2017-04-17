package com.xqsight.common.base.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.support.PropertyFilterSupport;
import com.xqsight.common.exception.ParamsException;
import com.xqsight.common.model.BaseModel;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.constants.ErrorMessageConstants;
import com.xqsight.common.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangganggang
 * @date 2017/04/10
 */
public class CommonController<Service extends ICrudService<Record, PK>, Record extends BaseModel, PK extends Serializable> {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Service service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable PK id) throws Exception {
        Record record = service.getById(id);
        preDelete(record);
        int iRet = service.removeById(id);
        afterDelete(record);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/logic/{id}", method = RequestMethod.DELETE)
    public Object logicDeleteById(Record record, @PathVariable PK id) throws Exception {
        Record updRecord = service.getById(id);
        preDelete(updRecord);
        if (updRecord == null || updRecord.getPK() == null) {
            throw new ParamsException(ErrorMessageConstants.ERROR_10001);
        }
        updRecord.setActive((byte) -1);
        updRecord.setUpdateTime(record.getUpdateTime());
        updRecord.setUpdateUserId(record.getUpdateUserId());
        int iRet = service.editById(updRecord);
        afterDelete(updRecord);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable PK id) {
        Record record = service.getById(id);
        return new BaseResult(record);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Object getPage() {
        Page page = initPage();
        List<Record> records = service.getByFilters(getFilter());
        return new BaseResult(getPageInfo(page));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object getAll() {
        List<Record> records = service.getByFilters(getFilter());
        return new BaseResult(records);
    }

    /**
     * 获取查询的参数
     *
     * @return
     */
    protected List<PropertyFilter> getFilter() {
        return PropertyFilterSupport.buildPropertyFilters(request);
    }

    /**
     *初始化分页信息
     * @return
     */
    protected Page initPage() {
        int pageNum = StringUtils.isBlank(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = StringUtils.isBlank(request.getParameter("pageSize")) ? 15 : Integer.parseInt(request.getParameter("pageSize"));
        return PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * 返回分页信息
     *
     * @param page
     * @return
     */
    protected Map getPageInfo(Page page) {
        Map pageMap = new HashMap(3);
        pageMap.put(Constants.TOTAL_SIZE, page.getTotal());
        pageMap.put(Constants.PAGE_SIZE, page.getPageSize());
        pageMap.put(Constants.PAGE_RESULT, page.getResult());
        return pageMap;
    }

    protected void prePut(Record record) throws Exception {
    }

    protected void afterPut(Record record) throws Exception {
    }

    protected void preDelete(Record record) throws Exception {
    }

    protected void afterDelete(Record record) throws Exception {
    }
}
