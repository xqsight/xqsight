/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.service.FileUploadFTPService;
import com.xqsight.upload.service.UploadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年5月9日 下午1:27:58
 */
@Controller
@RequestMapping("/file/manage/")
public class FileUploadController {

    protected Logger logger = LogManager.getLogger(FileUploadController.class);

    @Autowired
    private UploadService uploadService;
    @Autowired
    private FileUploadFTPService fileUploadFTPService;

    @RequestMapping("upload")
    @ResponseBody
    public Object uploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> multipartFileMap = mRequest.getFileMap();
        List<SysFile> sysFiles = null;
        try {
            sysFiles = uploadService.uploadFile(multipartFileMap);
        } catch (IOException e) {
            logger.error("上传出错，错误原因:{}", e.getMessage());
            e.printStackTrace();
            MessageSupport.failureMsg("上传失败");
        }
        return MessageSupport.successDataMsg(sysFiles, "上传成功");
    }

    @ResponseBody
    @RequestMapping(value = "uploadftp", produces = "text/html;charset=UTF-8")
    public Object saveUploadFtp(HttpServletRequest request, HttpServletResponse response) {

        JSONObject obj = new JSONObject();
        try {
            List<SysFile> ls = fileUploadFTPService.saveFTP(request, response);
            for (SysFile sys : ls) {
                obj.put("url", sys.getFileUrl());
            }
            obj.put("error", 0);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.info("上传失败！" + e.getMessage());
            obj.put("error", 1);
            obj.put("message", e.getMessage());
        }
        return obj.toJSONString();
    }


    @RequestMapping("delete")
    @ResponseBody
    public Object deleteFile(String fileId) {
        logger.debug("删除上传文件 ,参数:fileId={}", fileId);
        try {
            uploadService.deleteFileByFileId(fileId);
        } catch (IOException e) {
            logger.error("删除文件失败，原因:{}", e.getMessage(), e);
            return MessageSupport.failureMsg("删除文件失败");
        }
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @ResponseBody
    public Object queryFile(String fileId) {
        List<SysFile> sysFiles = uploadService.queryFileByFileId(fileId);
        return MessageSupport.successDataMsg(sysFiles, "查询成功");
    }

    @RequestMapping(value = "/ftpimage")
    public void img(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = request.getParameter("path");
            @SuppressWarnings("resource")
            FileInputStream steam = new FileInputStream(fileName);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("image/jpg");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            OutputStream out = response.getOutputStream();
            byte[] bt = new byte[1024];
            while (steam.read(bt) > -1) {
                out.write(bt);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
