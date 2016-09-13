package com.xqsight.upload.service.impl;

import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.model.vo.SysFileVo;
import com.xqsight.upload.mysqlmapper.SysFileMapper;
import com.xqsight.upload.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2016/6/8.
 */
@Service
public class UploadServiceImpl implements UploadService {

    private static Logger logger = LogManager.getLogger(UploadServiceImpl.class);

    @Autowired
    private SysFileMapper sysFileMapper;

    @Override
    public void saveSysFile(SysFileVo sysFile) {
        sysFileMapper.saveSysFile(sysFile);
    }

    @Override
    public void deleteSysFile(long fileId) {
        sysFileMapper.deleteSysFileById(fileId);
    }

    @Override
    public SysFile querySysFile(long fileId) {
        return sysFileMapper.querySysFileById(fileId);
    }

    @Override
    public List<SysFile> queryFileByFileId(String fileIds) {
        fileIds = StringUtils.join(StringUtils.split(fileIds,","),",");
        return sysFileMapper.querySysFileByIds(fileIds);
    }
}
