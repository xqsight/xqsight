package com.xqsight.chronic.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.chronic.model.ReportRecord;
import com.xqsight.chronic.mysqlmapper.ReportRecordMapper;
import com.xqsight.chronic.service.ReportRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/5/25.
 */
@Service
public class ReportRecordServiceImpl implements ReportRecordService {

    private static Logger logger = LogManager.getLogger(ReportRecordServiceImpl.class);
    @Autowired
    private ReportRecordMapper reportRecordMapper;

    @Override
    public void saveReportRecord(ReportRecord reportRecord) {
        logger.debug("出入参数:reportRecord={}", JSON.toJSONString(reportRecord));
        reportRecordMapper.saveReportRecord(reportRecord);
    }

    @Override
    public void deleteReportRecord(Long reportId) {
        logger.debug("出入参数:reportId={}", reportId);
        reportRecordMapper.deleteReportRecord(reportId);
    }

    @Override
    public List<ReportRecord> queryReportRecord() {
        return reportRecordMapper.queryReportRecord();
    }

    @Override
    public List<ReportRecord> queryReportRecordByUser(String createOprId) {
        return reportRecordMapper.queryReportRecordByUser(createOprId);
    }

    @Override
    public ReportRecord queryReportRecordById(Long reportId) {
        logger.debug("出入参数:reportId={}", reportId);
        return reportRecordMapper.queryReportRecordById(reportId);
    }
}
