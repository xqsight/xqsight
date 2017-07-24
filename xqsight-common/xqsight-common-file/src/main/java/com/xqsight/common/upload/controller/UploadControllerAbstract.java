package com.xqsight.common.upload.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xqsight.common.upload.GlobalUpload;
import com.xqsight.common.upload.UploadResult;
import com.xqsight.common.upload.Uploader;
import com.xqsight.common.upload.handler.FileHandler;
import com.xqsight.common.upload.service.PathResolver;
import com.xqsight.common.upload.service.UploadService;
import com.xqsight.common.upload.support.UploadSupport;
import com.xqsight.common.web.Servlets;
import com.xqsight.common.web.WebUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * UploadControllerAbstract
 *
 * @author wangganggnag
 */
public abstract class UploadControllerAbstract {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected PathResolver pathResolver;

    @Autowired
    protected UploadService uploadService;

    @Autowired
    private UploadSupport uploadSupport;

    /**
     * ueditor template action，返回空配置，全部配置在前端完整。
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void ueditorConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GlobalUpload globalUpload = uploadSupport.getGlobalUpload();
        // limit是以KB为单位，要乘以1024
        int imageLimit = globalUpload.getImageLimit();
        if (imageLimit <= 0) {
            imageLimit = Integer.MAX_VALUE;
        } else {
            imageLimit *= 1024;
        }
        int videoLimit = globalUpload.getVideoLimit();
        if (videoLimit <= 0) {
            videoLimit = Integer.MAX_VALUE;
        } else {
            videoLimit *= 1024;
        }
        int fileLimit = globalUpload.getFileLimit();
        if (fileLimit <= 0) {
            fileLimit = Integer.MAX_VALUE;
        } else {
            fileLimit *= 1024;
        }
        String imageExtensions = getExtensionsForUeditor(globalUpload.getImageExtensions());
        String videoExtensions = getExtensionsForUeditor(globalUpload.getVideoExtensions());
        String fileExtensions = getExtensionsForUeditor(globalUpload.getFileExtensions());
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"imageMaxSize\":").append(imageLimit).append(",");
        sb.append("\"scrawlMaxSize\":").append(imageLimit).append(",");
        sb.append("\"catcherMaxSize\":").append(imageLimit).append(",");
        sb.append("\"videoMaxSize\":").append(videoLimit).append(",");
        sb.append("\"fileMaxSize\":").append(fileLimit).append(",");

        sb.append("\"imageAllowFiles\":[").append(imageExtensions).append("],");
        sb.append("\"catcherAllowFiles\":[").append(imageExtensions).append("],");
        sb.append("\"videoAllowFiles\":[").append(videoExtensions).append("],");
        sb.append("\"fileAllowFiles\":[").append(fileExtensions).append("],");

        sb.append("\"imageCompressEnable\": true,");
        sb.append("\"catcherLocalDomain\":[\"127.0.0.1\", \"0:0:0:0:0:0:0:1\", \"localhost\"");
        String uploadsDomain = globalUpload.getUploadsDomain();
        if (StringUtils.isNotBlank(uploadsDomain)) {
            sb.append(",\"+uploadsDomain+\"");
        }
        sb.append("]}");
        logger.debug("ueditor template:" + sb.toString());
        response.setHeader("Content-Type", "text/html");
        response.getWriter().print(sb.toString());
        response.flushBuffer();
    }

    /**
     * 将jpg,png,gif后缀格式转换成ueditor的后缀格式.jpg,.png,.gif
     *
     * @param extensions
     * @return
     */
    private String getExtensionsForUeditor(String extensions) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(extensions)) {
            for (String s : StringUtils.split(extensions, ',')) {
                sb.append(".").append(s).append(",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
        }
        return sb.toString();
    }


    /**
     * ueditor 抓取图片
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void ueditorCatchImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GlobalUpload globalUpload = uploadSupport.getGlobalUpload();
        FileHandler fileHandler = uploadSupport.getFileHandler(pathResolver);
        String urlPrefix = uploadSupport.getUrlPrefix();

        StringBuilder result = new StringBuilder("{\"state\": \"SUCCESS\", list: [");
        List<String> urls = new ArrayList<String>();
        List<String> srcs = new ArrayList<String>();

        String[] source = request.getParameterValues("source[]");
        if (source == null) {
            source = new String[0];
        }
        for (int i = 0; i < source.length; i++) {
            String src = source[i];
            String extension = FilenameUtils.getExtension(src);

            // 格式验证
            if (!globalUpload.isExtensionValid(extension, Uploader.IMAGE)) {
                continue;
            }

            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection conn = (HttpURLConnection) new URL(src).openConnection();
            if (conn.getContentType().indexOf("image") == -1) {
                continue;
            }
            if (conn.getResponseCode() != 200) {
                continue;
            }
            String pathname = uploadSupport.getSiteBase(Uploader.getQuickPathname(Uploader.IMAGE, extension));
            InputStream is = null;
            try {
                is = conn.getInputStream();
                fileHandler.storeFile(is, pathname);
            } finally {
                IOUtils.closeQuietly(is);
            }
            String url = urlPrefix + pathname;
            urls.add(url);
            srcs.add(src);
            result.append("{\"state\": \"SUCCESS\",");
            result.append("\"url\":\"").append(url).append("\",");
            result.append("\"source\":\"").append(src).append("\"},");
        }
        if (result.charAt(result.length() - 1) == ',') {
            result.setLength(result.length() - 1);
        }
        result.append("]}");
        logger.debug(result.toString());
        response.getWriter().print(result.toString());
    }

    protected void upload(HttpServletRequest request, HttpServletResponse response, String type) throws IOException {
        upload(request, response, type, null, null, null, null, null, null, null, null);
    }

    protected void upload(HttpServletRequest request, HttpServletResponse response, String type,
                          Boolean scale, Boolean exact, Integer width, Integer height, Boolean thumbnail, Integer thumbnailWidth,
                          Integer thumbnailHeight, Boolean watermark) throws IOException {
        UploadResult result = new UploadResult();
        Locale locale = RequestContextUtils.getLocale(request);
        result.setMessageSource(messageSource, locale);
        Integer userId = uploadSupport.getSystemId();
        String ip = WebUtils.getUserIp(request);
        MultipartFile partFile = getMultipartFile(request);

        uploadService.upload(partFile, type, userId, ip, result, scale, exact, width, height, thumbnail, thumbnailWidth, thumbnailHeight, watermark);

        Map<String, Object> returnMap = new HashMap();
        String editor = StringUtils.trimToEmpty(request.getParameter("editor"));
        switch (editor) {
            case "ckeditor":
                returnMap.put("error", result.getStatus());
                returnMap.put("url", result.getFileUrl());
                Servlets.writeHtml(response, JSON.toJSONString(returnMap));
                break;
            case "ueditor":
                String title = request.getParameter("pictitle");
                returnMap.put("title", title);
                returnMap.put("state", "SUCCESS");
                returnMap.put("url", result.getFileUrl());
                returnMap.put("fileType", "." + result.getFileExtension());
                Servlets.writeHtml(response, JSON.toJSONString(returnMap));
                break;
            case "editormd":
                returnMap.put("success", result.isSuccess() ? 1 : 0);
                returnMap.put("message", result.getMessage());
                returnMap.put("url", result.getFileUrl());
                Servlets.writeHtml(response, JSON.toJSONString(returnMap));
                break;
            case "wangeditor":
                String retMsg;
                if (result.getStatus() == 0) {
                    retMsg = result.getFileUrl();
                } else {
                    retMsg = "error|" + result.getMessage();
                }
                Servlets.writeHtml(response, retMsg);
                break;
            default:
                String json = JSON.toJSON(result).toString();
                Servlets.writeHtml(response, json);
        }
    }

    private MultipartFile getMultipartFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        if (CollectionUtils.isEmpty(fileMap)) {
            throw new IllegalStateException("No upload files found!");
        }
        return fileMap.entrySet().iterator().next().getValue();
    }
}
