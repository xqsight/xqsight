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
 * @param <PK>
 * @author wangganggang
 * @Date 2017/3/23
 */
public class BaseController<Service extends ICrudService<Record, PK>, Record extends BaseModel, PK extends Serializable>
        extends CommonController<Service, Record, PK> {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object put(Record record) throws Exception {
        prePut(record);

        int iRet = 0;
        if (record.getPK() != null && !"".equals(record.getPK())) {
            iRet = service.editById(record);
        } else {
            service.add(record);
        }

        afterPut(record);
        return new BaseResult(iRet);
    }
}
