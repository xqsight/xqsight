package com.xqsight.common.base.controller;

import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.model.AbstractTreeModel;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.support.TreeSupport;
import com.xqsight.common.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @param <Service>
 * @param <Record>
 * @author wangganggang
 * @Date 2017/3/23
 */
public class BaseTreeController<Service extends ICrudService<Record, Example>, Record extends AbstractTreeModel, Example>
        extends CommonController<Service, Record, Example> {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object put(Record record) throws Exception {
        prePut(record);

        int iRet = 0;

        afterPut(record);
        return new BaseResult(iRet);
    }


    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public Object getTree(Record record) {
        List<Record> records = service.get(record);
        records = new TreeSupport<Record>().generateTree(records);
        return new BaseResult(records);
    }

}
