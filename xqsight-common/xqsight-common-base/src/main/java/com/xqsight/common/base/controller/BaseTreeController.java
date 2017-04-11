package com.xqsight.common.base.controller;

import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.model.AbstractTreeModel;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.support.TreeSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

/**
 * @param <Service>
 * @param <Record>
 * @param <PK>
 * @author wangganggang
 * @Date 2017/3/23
 */
public class BaseTreeController<Service extends ICrudService<Record, PK>, Record extends AbstractTreeModel, PK extends Serializable>
        extends CommonController<Service, Record, PK> {

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


    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public Object getTree() {
        List<Record> records = service.getAll();
        records = new TreeSupport<Record>().generateTree(records);
        return new BaseResult(records);
    }

}
