package com.xqsight.common.base.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.support.PropertyFilterSupport;
import com.xqsight.common.exception.ParamsException;
import com.xqsight.common.model.BaseModel;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
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
public class CommonController<Service extends ICrudService<Record, Example>, Record, Example> {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Service service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public Object deleteById(Record record) {
        preDelete(record);
        int iRet = service.removeById(record);
        afterDelete(record);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object getById(Record record) {
        record = service.getById(record);
        return new BaseResult(record);
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object getPage(Record record) {
        Page page = initPage();
        List<Record> records = service.get(record);
        return new BaseResult(getPageInfo(page));
    }

    /**
     * 初始化分页信息
     *
     * @return
     */
    protected Page initPage() {
        int pageNum = StringUtils.isBlank(request.getParameter("iDisplayStart")) ? 1 : Integer.parseInt(request.getParameter("iDisplayStart"));
        int pageSize = StringUtils.isBlank(request.getParameter("iDisplayLength")) ? 15 : Integer.parseInt(request.getParameter("iDisplayLength"));
        return PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * 返回分页信息
     *
     * @param page
     * @return
     */
    protected Map getPageInfo(Page page) {
        Map pageMap = new HashMap(4);
        pageMap.put("sEcho", request.getParameter("sEcho"));
        pageMap.put("iTotalRecords", page.getTotal());
        pageMap.put("iTotalDisplayRecords", page.getTotal());
        pageMap.put("aaData", page.getResult());
        return pageMap;
    }

    protected void prePut(Record record) {
    }

    protected void afterPut(Record record) {
    }

    protected void preDelete(Record record) {
    }

    protected void afterDelete(Record record) {
    }
}
