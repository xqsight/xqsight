package com.xqsight.common.upload.controller;

import com.xqsight.common.upload.Uploader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UploadController
 *
 * @author wangganggang
 */
@RestController
@RequestMapping("/files/core/")
public class UploadController extends UploadControllerAbstract {

    @RequestMapping(value = "upload_image", method = RequestMethod.POST)
    public void uploadImage(Boolean scale, Boolean exact, Integer width, Integer height, Boolean thumbnail,
                            Integer thumbnailWidth, Integer thumbnailHeight, Boolean watermark, HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        upload(request, response, Uploader.IMAGE, scale, exact, width, height, thumbnail, thumbnailWidth, thumbnailHeight, watermark);
    }

    @RequestMapping(value = "upload_flash", method = RequestMethod.POST)
    public void uploadFlash(HttpServletRequest request, HttpServletResponse response) throws IOException {
        upload(request, response, Uploader.FLASH);
    }

    @RequestMapping(value = "upload_file", method = RequestMethod.POST)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        upload(request, response, Uploader.FILE);
    }

    @RequestMapping(value = "upload_video", method = RequestMethod.POST)
    public void uploadVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        upload(request, response, Uploader.VIDEO);
    }

    @RequestMapping(value = "upload_doc", method = RequestMethod.POST)
    public void uploadDocument(HttpServletRequest request, HttpServletResponse response) throws IOException {
        upload(request, response, Uploader.DOC);
    }

    @RequestMapping(value = "editor")
    public void ueditor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("core".equals(action)) {
            super.ueditorConfig(request, response);
        } else if ("uploadimage".equals(action)) {
            uploadImage(null, null, null, null, null, null, null, null, request, response);
        } else if ("catchimage".equals(action)) {
            ueditorCatchImage(request, response);
        } else if ("uploadvideo".equals(action)) {
            uploadVideo(request, response);
        } else if ("uploadfile".equals(action)) {
            uploadFile(request, response);
        } else if ("uploadscrawl".equals(action)) {
            uploadFile(request, response);
        } else if ("listimage".equals(action)) {
            uploadFile(request, response);
        } else if ("listfile".equals(action)) {
            uploadFile(request, response);
        } else {
            uploadFile(request, response);
        }
    }

    @Override
    public void ueditorCatchImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.ueditorCatchImage(request, response);
    }
}
