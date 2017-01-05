/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.xqsight.commons.support.MessageSupport;
import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.model.vo.SysFileVo;
import com.xqsight.upload.service.FileUploadFTPService;
import com.xqsight.upload.service.UploadService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangganggang
 * @date 2016年5月9日 下午1:27:58
 */
@RestController
@RequestMapping("/file/manage/")
public class FileUploadController extends AbstractFileUploadController{

    @Autowired
    private UploadService uploadService;
    @Autowired
    private FileUploadFTPService fileUploadFTPService;

    @RequestMapping("upload")
    public Object uploadFile(HttpServletRequest request) throws IOException {
        if (!ServletFileUpload.isMultipartContent(request))
            return MessageSupport.failureMsg("请选择上传文件");

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> multipartFileMap = mRequest.getFileMap();

        List<SysFile> sysFiles = new ArrayList<>();
        for(String key : multipartFileMap.keySet()){
            MultipartFile multipartFile = multipartFileMap.get(key);
            SysFileVo sysFile = uploadFile(multipartFile);
            uploadService.saveSysFile(sysFile);
            sysFiles.add(sysFile);
        }
        return MessageSupport.successDataMsg(sysFiles, "上传成功");
    }

    @RequestMapping(value = "uploadftp")
    public Object saveUploadFtp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ServletFileUpload.isMultipartContent(request))
            return MessageSupport.failureMsg("请选择上传文件");

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> multipartFileMap = mRequest.getFileMap();

        for(String key : multipartFileMap.keySet()){
            MultipartFile multipartFile = multipartFileMap.get(key);
            SysFileVo sysFile = uploadFile(multipartFile);
            uploadService.saveSysFile(sysFile);
            JSONObject obj = new JSONObject();
            obj.put("url", sysFile.getFileUrl());
            obj.put("error", 0);
            return obj.toJSONString();
        }
        /*return MessageSupport.successDataMsg(sysFiles, "上传成功");*/

       /* JSONObject obj = new JSONObject();
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
        return obj.toJSONString();*/
        return null;
    }


    @RequestMapping("delete")
    public Object deleteFile(Long fileId) throws IOException {
        logger.debug("删除上传文件 ,参数:fileId={}", fileId);
        if(fileId == null)
            return MessageSupport.failureMsg("请传递要删除文件的fileId");

        SysFile sysFile = uploadService.querySysFile(fileId);
        deleteFile(sysFile.getFileUrl());
        uploadService.deleteSysFile(fileId);

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
