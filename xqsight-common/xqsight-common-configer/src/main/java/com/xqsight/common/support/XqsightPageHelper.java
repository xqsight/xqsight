/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.common.support;

import java.math.BigDecimal;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author wangganggang
 * @version PageHelper.java, v 0.1 2015年7月2日 上午8:38:55 wangganggang
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class XqsightPageHelper extends PageHelper {
    
    /**
     * @param firstRecordIdx 当页首条记录的在总记录中序号
     * @param pageSize 每页展示的条数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Page startPageWithPageIndex(int firstRecordIdx, int pageSize) {
        int pageNum = BigDecimal.valueOf(firstRecordIdx).divide(BigDecimal.valueOf(pageSize), BigDecimal.ROUND_UP).intValue() + 1;
        return com.github.pagehelper.PageHelper.startPage(pageNum, pageSize);
    }

}
