package com.xqsight.common.base.controller;

import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.model.BaseModel;
import com.xqsight.common.model.BaseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * @param <Service>
 * @param <Record>
 * @author wangganggang
 * @Date 2017/3/23
 */
public class BaseController<Service extends ICrudService<Record>, Record extends BaseModel>
        extends CommonController<Service, Record> {

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object put(Record record) throws Exception {
        prePut(record);

        afterPut(record);
        return new BaseResult(0);
    }
}
