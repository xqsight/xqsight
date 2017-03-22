package com.xqsight.common.core.controller;

import com.xqsight.common.core.service.ICrudService;
import com.xqsight.common.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by wangganggang on 2017/3/21.
 *
 * @author zy8
 * @date 2017/03/21
 */
public class BaseController<Service extends ICrudService<Record, PK>, Record extends Model, PK extends Serializable> {

    @Autowired
    protected Service service;


}
