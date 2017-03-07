package com.xqsight.common.upload.service;

import com.xqsight.common.upload.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 上传处理器
 *
 * @author wangganggang
 */
public interface UploadService {

    public String copyImage(File src, String extension, String formatName, Boolean scale, Boolean exact,
                            Integer width, Integer height, Boolean thumbnail, Integer thumbnailWidth, Integer thumbnailHeight,
                            Boolean watermark, String ip, Integer userId, Integer siteId);

    public String storeImage(File file, String extension, String formatName, String ip, Integer userId);

    public String storeImage(BufferedImage buff, String extension, String formatName, String ip, Integer userId);

    public void upload(String url, String type, Integer userId, String ip, UploadResult result);

    public void upload(MultipartFile partFile, String type, Integer userId, String ip, UploadResult result);

    public void upload(String url, String type, Integer userId, String ip, UploadResult result,
                       Boolean scale, Boolean exact, Integer width, Integer height, Boolean thumbnail, Integer thumbnailWidth,
                       Integer thumbnailHeight, Boolean watermark);

    public void upload(MultipartFile partFile, String type, Integer userId, String ip, UploadResult result,
                       Boolean scale, Boolean exact, Integer width, Integer height, Boolean thumbnail, Integer thumbnailWidth,
                       Integer thumbnailHeight, Boolean watermark);
}
