package com.xqsight.common.upload.service.impl;


import com.google.common.net.HttpHeaders;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.upload.GlobalUpload;
import com.xqsight.common.upload.UploadDoc;
import com.xqsight.common.upload.UploadResult;
import com.xqsight.common.upload.Uploader;
import com.xqsight.common.upload.file.FilesEx;
import com.xqsight.common.upload.handler.FileHandler;
import com.xqsight.common.upload.handler.LocalFileHandler;
import com.xqsight.common.upload.image.Images;
import com.xqsight.common.upload.image.ScaleParam;
import com.xqsight.common.upload.image.ThumbnailParam;
import com.xqsight.common.upload.image.WatermarkParam;
import com.xqsight.common.upload.service.ImageServices;
import com.xqsight.common.upload.service.PathResolver;
import com.xqsight.common.upload.service.UploadService;
import com.xqsight.common.upload.support.UploadSupport;
import com.xqsight.common.upload.support.UploadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class UploadServiceImpl implements UploadService {

    protected static Logger logger = LogManager.getLogger(UploadServiceImpl.class);

    @Resource(name = "imageScalrService")
    protected ImageServices imageScalrService;

    @Autowired
    protected PathResolver pathResolver;

    @Override
    public String copyImage(File src, String extension, String formatName, Boolean scale, Boolean exact,
                            Integer width, Integer height, Boolean thumbnail, Integer thumbnailWidth, Integer thumbnailHeight,
                            Boolean watermark, String ip, Integer userId, Integer siteId) {

        GlobalUpload gu = UploadSupport.getGlobalUpload();
        ScaleParam scaleParam = gu.getScaleParam(scale, exact, width, height);
        scale = scaleParam.isScale();

        ThumbnailParam thumbnailParam = new ThumbnailParam(thumbnail, thumbnailWidth, thumbnailHeight);
        thumbnail = thumbnailParam.isThumbnail();

        WatermarkParam watermarkParam = UploadSupport.getWatermarkParam(watermark);
        watermark = watermarkParam.isWatermark();

        FileHandler fileHandler = UploadSupport.getFileHandler(pathResolver);

        String pathname = UploadUtils.getUrl(UploadSupport.getSystemId(), Uploader.IMAGE, extension);

        String urlPrefix = UploadSupport.getUrlPrefix();
        try {
            File copy = FilesEx.getTempFile();
            try {
                FileUtils.copyFile(src, copy);
                storeImage(copy, scaleParam, thumbnailParam, watermarkParam, formatName, pathname, fileHandler, ip, userId, siteId);
                return urlPrefix + pathname;
            } finally {
                FileUtils.deleteQuietly(copy);
            }
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String storeImage(File file, String extension, String formatName, String ip, Integer userId) {

        FileHandler fileHandler = UploadSupport.getFileHandler(pathResolver);
        String urlPrefix = UploadSupport.getUrlPrefix();
        String filename = UploadUtils.getUrl(UploadSupport.getSystemId(), Uploader.IMAGE, extension);
        try {
            fileHandler.storeFile(file, filename);
            long length = file.length();
            //attachmentService.save(filename, length, ip, userId, site.getId());
            return urlPrefix + filename;
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public String storeImage(BufferedImage buff, String extension, String formatName, String ip, Integer userId) {
        FileHandler fileHandler = UploadSupport.getFileHandler(pathResolver);
        String urlPrefix = UploadSupport.getUrlPrefix();
        String filename = UploadUtils.getUrl(UploadSupport.getSystemId(), Uploader.IMAGE, extension);
        try {
            fileHandler.storeImage(buff, formatName, filename);
            long length = buff.getWidth() * buff.getHeight() / 3;
            // attachmentService.save(filename, length, ip, userId, site.getId());
            return urlPrefix + filename;
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }

    @Override
    public void upload(String url, String type, Integer userId, String ip, UploadResult result) {
        upload(url, type, userId, ip, result, null, null, null, null, null, null, null, null);
    }

    @Override
    public void upload(MultipartFile partFile, String type, Integer userId, String ip, UploadResult result) {
        upload(partFile, type, userId, ip, result, null, null, null, null, null, null, null, null);
    }

    @Override
    public void upload(String url, String type, Integer userId, String ip, UploadResult result, Boolean scale, Boolean exact, Integer width,
                       Integer height, Boolean thumbnail, Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark) {
        try {
            URL source = new URL(url);
            // files（下载）支持重定向支持，其他的不支持。
            if (Uploader.FILE.equals(type)) {
                HttpURLConnection.setFollowRedirects(true);
            } else {
                HttpURLConnection.setFollowRedirects(false);
            }
            HttpURLConnection conn = (HttpURLConnection) source.openConnection();
            conn.setRequestProperty("User-Agent", Constants.USER_ANGENT);
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                result.setError("URL response error:" + responseCode);
                return;
            }
            if (Uploader.IMAGE.equals(type)) {
                String contentType = conn.getContentType();
                if (!validateImageContentType(contentType, result)) {
                    return;
                }
            }
            String disposition = conn.getHeaderField(HttpHeaders.CONTENT_DISPOSITION);
            String fileName = StringUtils.substringBetween(disposition, "filename=\"", "\"");
            if (StringUtils.isBlank(fileName)) {
                fileName = FilenameUtils.getName(source.getPath());
            }
            String ext = FilenameUtils.getExtension(fileName);
            File temp = FilesEx.getTempFile(ext);
            InputStream is = conn.getInputStream();
            try {
                FileUtils.copyInputStreamToFile(is, temp);
                doUpload(temp, fileName, type, userId, ip, result, scale, exact, width, height, thumbnail,
                        thumbnailWidth, thumbnailHeight, watermark);
            } finally {
                IOUtils.closeQuietly(is);
                FileUtils.deleteQuietly(temp);
            }
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return;
    }

    @Override
    public void upload(MultipartFile partFile, String type, Integer userId, String ip, UploadResult result, Boolean scale, Boolean exact, Integer width,
                       Integer height, Boolean thumbnail, Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark) {
        try {
            if (!validateFile(partFile, result)) {
                return;
            }
            String fileName = partFile.getOriginalFilename();
            String ext = FilenameUtils.getExtension(fileName);
            File temp = FilesEx.getTempFile(ext);
            partFile.transferTo(temp);
            try {
                doUpload(temp, fileName, type, userId, ip, result, scale, exact, width, height, thumbnail, thumbnailWidth, thumbnailHeight, watermark);
            } finally {
                FileUtils.deleteQuietly(temp);
            }
        } catch (Exception e) {
            result.setError(e.getMessage());
            logger.error(e);
        }
        return;
    }

    private UploadResult doUpload(File file, String fileName, String type, Integer userId, String ip, UploadResult result, Boolean scale, Boolean exact,
                                  Integer width, Integer height, Boolean thumbnail, Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark) throws Exception {

        Integer siteId = UploadSupport.getSystemId();
        long fileLength = file.length();
        String ext = FilenameUtils.getExtension(fileName).toLowerCase();
        GlobalUpload gu = UploadSupport.getGlobalUpload();
        // 后缀名是否合法
        if (!validateExt(ext, type, gu, result)) {
            return result;
        }
        // 文库是否开启
       /* if (type == Uploader.DOC && !isDocEnabled(result, site.getGlobal())) {
            return result;
        }*/

        String urlPrefix = UploadSupport.getUrlPrefix();
        FileHandler fileHandler = UploadSupport.getFileHandler(pathResolver);

        String pathname = UploadSupport.getSiteBase(Uploader.getQuickPathname(type, ext));
        String fileUrl = urlPrefix + pathname;
        String pdfUrl = null;
        String swfUrl = null;
        if (Uploader.IMAGE.equals(type)) {
            WatermarkParam wp = UploadSupport.getWatermarkParam(watermark);
            doUploadImage(fileHandler, file, pathname, scale, exact, width, height, thumbnail, thumbnailWidth, thumbnailHeight, watermark, gu, wp, ip, userId, siteId);
        } else if (StringUtils.equals(Uploader.DOC,type)) {
            if (!"swf".equals(ext)) {
                String swfPathname = UploadSupport.getSiteBase(Uploader.getQuickPathname(type, "swf"));
                swfUrl = urlPrefix + swfPathname;
                String pdfPathname = null;
                if (!"pdf".equals(ext)) {
                    pdfPathname = UploadSupport.getSiteBase(Uploader.getQuickPathname(type, "pdf"));
                    pdfUrl = urlPrefix + pdfPathname;
                } else {
                    pdfUrl = fileUrl;
                }
                //UploadDoc.exec(attachmentService, fileHandler, pathname, ext, pdfPathname, swfPathname, files, ip,userId, siteId);
                UploadDoc.exec(fileHandler, pathname, ext, pdfPathname, swfPathname, file, ip, userId, siteId);
            } else {
                swfUrl = fileUrl;
                fileHandler.storeFile(file, pathname);
            }
        } else {
            fileHandler.storeFile(file, pathname);
        }
        //attachmentService.save(pathname, fileLength, ip, userId, siteId);
        result.set(fileUrl, fileName, ext, fileLength, pdfUrl, swfUrl);
        return result;
    }

    private void doUploadImage(FileHandler fileHandler, File file, String pathname, Boolean scale, Boolean exact,
                               Integer width, Integer height, Boolean thumbnail, Integer thumbnailWidth, Integer thumbnailHeight,
                               Boolean watermark, GlobalUpload gu, WatermarkParam wp, String ip, Integer userId, Integer siteId)
            throws IOException {
        ScaleParam scaleParam = gu.getScaleParam(scale, exact, width, height);
        scale = scaleParam.isScale();

        ThumbnailParam thumbnailParam = new ThumbnailParam(thumbnail, thumbnailWidth, thumbnailHeight);
        thumbnail = thumbnailParam.isThumbnail();

        watermark = wp.isWatermark();

        String formatName = null;
        if (watermark || scale || thumbnail) {
            formatName = Images.getFormatName(file);
        }
        if (StringUtils.isNotBlank(formatName)) {
            // 可以且需要处理的图片
            storeImage(file, scaleParam, thumbnailParam, wp, formatName, pathname, fileHandler, ip, userId, siteId);
        } else {
            // 不可处理的图片
            fileHandler.storeFile(file, pathname);
        }
    }

    private void storeImage(File src, ScaleParam scaleParam, ThumbnailParam thumbnailParam, WatermarkParam watermarkParam, String formatName,
                            String pathname, FileHandler fileHandler, String ip, Integer userId, Integer siteId) throws IOException {
        String srcPath = src.getAbsolutePath();
        if (scaleParam.isScale()) {
            imageScalrService.resize(srcPath, srcPath, scaleParam);
        }
        if (thumbnailParam.isThumbnail()) {
            File thumbnailFile = FilesEx.getTempFile();
            String thumbnailPath = thumbnailFile.getAbsolutePath();
            Integer thumbnailWidth = thumbnailParam.getWidth();
            Integer thumbnailHeight = thumbnailParam.getHeight();
            if (imageScalrService.resize(srcPath, thumbnailPath, thumbnailWidth, thumbnailHeight, false)) {
                try {
                    String thumbnailName = Uploader.getThumbnailName(pathname);
                    fileHandler.storeFile(thumbnailFile, thumbnailName);
                    // 新产生的缩略图要单独保存到附件，原图在doUpload里面保存到附件
                    // attachmentService.save(thumbnailName, thumbnailFile.length(), ip, userId, siteId);
                } finally {
                    // 确保删除临时文件
                    FileUtils.deleteQuietly(thumbnailFile);
                }
            }
        }
        if (watermarkParam.isWatermark()) {
            String imagePath = watermarkParam.getImagePath();
            LocalFileHandler localFileHandler = FileHandler.getLocalFileHandler(pathResolver, Constants.TEMPLATE_STORE_PATH);
            File overlay = localFileHandler.getFile(imagePath);
            String overlayPath = overlay.getAbsolutePath();
            imageScalrService.composite(overlayPath, srcPath, srcPath, watermarkParam);
        }
        fileHandler.storeFile(src, pathname);
    }

    private boolean validateExt(String extension, String type, GlobalUpload gu, UploadResult result) {
        if (!gu.isExtensionValid(extension, type)) {
            logger.debug("image extension not allowed: " + extension);
            result.setErrorCode("imageExtensionNotAllowed", new String[]{extension});
            return false;
        }
        return true;
    }

    private boolean validateFile(MultipartFile partFile, UploadResult result) {
        if (partFile == null || partFile.isEmpty()) {
            logger.debug("files is empty");
            result.setError("no files upload!");
            return false;
        }
        return true;
    }

    private boolean validateImageContentType(String contentType, UploadResult result) {
        if (!StringUtils.contains(contentType, "image")) {
            logger.debug("ContentType not contain Image: " + contentType);
            result.setError("ContentType not contain Image: " + contentType);
            return false;
        }
        return true;
    }
}
