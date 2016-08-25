package com.xqsight.upload.service.impl;

import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.mysqlmapper.SysFileMapper;
import com.xqsight.upload.service.UploadService;
import com.xqsight.upload.support.FileUploadSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/6/8.
 */
@Service
public class UploadServiceImpl implements UploadService {

    private final static String DEFAULT_SEPERITE = ",";

    private static Logger logger = LogManager.getLogger(UploadServiceImpl.class);

    @Autowired
    private SysFileMapper sysFileMapper;

    @Override
    public List<SysFile> uploadFile(Map<String, MultipartFile> multipartFileMap) throws IOException {
        List<SysFile> sysFiles = new ArrayList<>();
        for (String mapKey : multipartFileMap.keySet()) {
            MultipartFile multipartFile = multipartFileMap.get(mapKey);
            SysFile sysFile = FileUploadSupport.uploadFile(multipartFile);
            sysFiles.add(sysFile);
            sysFileMapper.saveSysFile(sysFile);
        }
        return sysFiles;
    }

    @Override
    public void deleteFileByPath(String path) throws IOException {
        logger.debug("传入参数:path={}", path);
        if(StringUtils.contains(path,"="))
            path = StringUtils.split(path,"=",2)[1];

        FileUploadSupport.deleteFile(path);
    }

    @Override
    public void deleteFileByFileId(String fileId) throws IOException {
        String fileIds = StringUtils.join(StringUtils.split(fileId, DEFAULT_SEPERITE), ",");
        List<SysFile> sysFiles = sysFileMapper.querySysFileByIds(fileIds);
        sysFileMapper.deleteSysFileByFileIds(fileIds);
        for(SysFile sysFile : sysFiles){
            deleteFileByPath(sysFile.getFileUrl());
        }
    }

    @Override
    public List<SysFile> queryFileByFileId(String fileId) {
        String fileIds = StringUtils.join(StringUtils.split(fileId, DEFAULT_SEPERITE), ",");
        return sysFileMapper.querySysFileByIds(fileIds);
    }
}
