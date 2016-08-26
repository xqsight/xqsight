package com.xqsight.upload.controller;

import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.support.FileUploadSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by user on 2016/8/26.
 */
public class AbstractFileUploadController {

    protected Logger logger = LogManager.getLogger(getClass());

    public SysFile uploadFile(MultipartFile multipartFile) throws IOException {
        return FileUploadSupport.uploadFile(multipartFile);
    }


    public void deleteFile(String path) throws IOException{
        FileUploadSupport.deleteFile(path);
    }

}
