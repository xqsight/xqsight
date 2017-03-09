package com.xqsight.common.upload.support;

import com.xqsight.common.upload.GlobalUpload;
import com.xqsight.common.upload.file.FtpTemplate;
import com.xqsight.common.upload.handler.FileHandler;
import com.xqsight.common.upload.image.WatermarkParam;
import com.xqsight.common.upload.service.PathResolver;
import com.xqsight.common.upload.service.UploadConfig;
import com.xqsight.common.web.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by wangganggang on 2017/1/11.
 */
@Component
public class UploadSupport {

    @Autowired
    private UploadConfig uploadConfig;

    private final String seperator = "/";

    public WatermarkParam getWatermarkParam(Boolean watermark) {
        return new WatermarkParam(watermark);
    }

    public GlobalUpload getGlobalUpload() {
        return new GlobalUpload();
    }

    public FtpTemplate getFtpTemplate() {
        return new FtpTemplate(uploadConfig.getFtpHostName(), uploadConfig.getFtpPort(), uploadConfig.getFtpUserName(), uploadConfig.getFtpPassword());
    }

    public FileHandler getFileHandler(PathResolver pathResolver) {
        String prefix = uploadConfig.getPrefix();
        FileHandler fileHandler;
        if (uploadConfig.isFtpMethod()) {
            fileHandler = FileHandler.getFileHandler(getFtpTemplate(), prefix);
        } else {
            fileHandler = FileHandler.getLocalFileHandler(pathResolver, prefix);
        }
        return fileHandler;
    }

    public String getUrlPrefix() {
        StringBuilder sb = new StringBuilder();
        String displayPath = uploadConfig.getDisplayPath();
        if (StringUtils.isNotBlank(displayPath)) {
            sb.append(displayPath);
        }
        return sb.toString();
    }

    public int getSystemId() {
        return 1;
    }

    public String getSiteBase(String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator).append(getSystemId());

        if (StringUtils.isNotBlank(path)) {
            if (!StringUtils.startsWith(path, seperator)) {
                sb.append(File.separator);
            }
            sb.append(path);
        }
        return sb.toString();
    }

    public boolean isFtp() {
        return uploadConfig.isFtpMethod();
    }

}
