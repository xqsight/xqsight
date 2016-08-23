package com.xqsight.upload.service;

import com.xqsight.upload.model.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/6/8.
 */
public interface UploadService {

    /**
     * 上传文件
     *
     * @param multipartFileMap
     * @return
     * @throws IOException
     */
    List<SysFile> uploadFile(Map<String, MultipartFile> multipartFileMap) throws IOException;

    /**
     * 删除文件
     * @param path
     * @throws IOException
     */
    void deleteFileByPath(String path) throws IOException;

    void deleteFileByFileId(String fileId) throws IOException;

    List<SysFile> queryFileByFileId(String FileId);
}
