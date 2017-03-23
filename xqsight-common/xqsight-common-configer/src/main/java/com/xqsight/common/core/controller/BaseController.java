package com.xqsight.common.core.controller;

import com.xqsight.common.core.service.ICrudService;
import com.xqsight.common.model.BaseModel;
import com.xqsight.common.model.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangganggang on 2017/3/21.
 */
public class BaseController<Service extends ICrudService<Record, PK>, Record extends BaseModel, PK extends Serializable> {

    @Autowired
    protected Service service;

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public Object save(Record record) {
        int iRet = service.add(record);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public Object update(Record record) {
        int iRet = service.editById(record);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable PK id) {
        int iRet = service.removeById(id);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable PK id) {
        Record record = service.getById(id);
        return new BaseResult(record);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public Object getAll() {
        List<Record> records = service.getAll();
        return new BaseResult(records);
    }
}
