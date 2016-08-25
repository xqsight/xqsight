package com.xqsight.common.support;

import com.xqsight.common.upload.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface UploadHandler {
    String copyImage(BufferedImage buff, String extension,
                     String formatName, Boolean scale, Boolean exact,
                     Integer width, Integer height, Boolean thumbnail,
                     Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark,
                     String ip, Integer userId);

    String storeImage(BufferedImage buff, String extension, String formatName, String ip, Integer userId);

    void upload(String url, String type, Integer userId, String ip, UploadResult result);

    void upload(MultipartFile partFile, String type, Integer userId, String ip, UploadResult result);

    void upload(String url, String type, Integer userId,
                String ip, UploadResult result, Boolean scale, Boolean exact,
                Integer width, Integer height, Boolean thumbnail,
                Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark);

    void upload(MultipartFile partFile, String type,
                Integer userId, String ip, UploadResult result, Boolean scale,
                Boolean exact, Integer width, Integer height, Boolean thumbnail,
                Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark);
}
