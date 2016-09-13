/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.upload.support;

import com.xiaoleilu.hutool.util.FileUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xqsight.commons.support.FileExtSupport;
import com.xqsight.commons.utils.DateFormatUtils;
import com.xqsight.commons.utils.FlashFtpUtils;
import com.xqsight.upload.config.FileUploadConfig;
import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.model.vo.SysFileVo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年5月9日 上午11:04:01
 */
public class FileUploadSupport {

    private final static String separator = File.separator;
    private final static String Dot = ".";


    /**
     * 上传文件，返回访问地址
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static SysFileVo uploadFile(MultipartFile file) throws IOException {
        SysFileVo sysFile = new SysFileVo();

        String fileName = file.getOriginalFilename();
        String ext = FilenameUtils.getExtension(fileName);
        String fileSize = String.valueOf(file.getSize());
        String fileBaseName = FilenameUtils.getBaseName(fileName);

        /**  文件保存路径 upload + 文件类型 + 日期 **/
        String filePath = new StringBuffer("upload").append(separator).append(FileExtSupport.fileKind(ext)).append(separator).append(DateFormatUtils.yyyyMMdd.format(new Date())).toString();
        /**  上传文件的名称 ：原文件名 + 大小 + 随机数**/
        String newFileName = new StringBuffer(fileBaseName).append("-").append(fileSize).append("-").append(RandomUtil.randomString(6)).toString();
        /**  上传文件的名称 ：原文件名 + 大小 + 随机数 + 扩展名  **/
        String newFullFileName = newFileName + Dot + ext;
        /** 上传完整路径：保存路径 + 处理后文件名 **/
        String fileFulPath = filePath + separator + newFileName + newFullFileName;
        /** 获取当前请求 request**/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /** 当前项目的根目录 **/
        String projectPath = request.getSession().getServletContext().getRealPath(separator);
        /** 本地上传文件处理 **/
        String localFilePath = projectPath + separator + fileFulPath;

        if (StringUtils.equalsIgnoreCase(FileUploadConfig.SAVE_TYPE, "LOCAL")) {
            File newFile = new File(localFilePath);
            FileUtils.touch(newFile);
            //传送文件
            file.transferTo(newFile);
            if (FileExtSupport.checkIsConvertVedio(ext) != 9) {//视频文件
                localUploadVidio(projectPath + separator + filePath,newFullFileName);

                sysFile.setFileThumbnails(filePath + separator + newFileName + Dot + "jpg");
                sysFile.setUploadUrl(filePath + separator + newFileName + Dot + "flv");
                sysFile.setFileUrl(FileUploadConfig.LOCAL_UPLOADURL + filePath + separator + newFileName + Dot + "flv");
            } else {
                sysFile.setUploadUrl(fileFulPath);
                sysFile.setFileUrl(FileUploadConfig.LOCAL_UPLOADURL + fileFulPath);
            }
            sysFile.setFileDomain(FileUploadConfig.LOCAL_UPLOADURL);
        } else {
            if (FileExtSupport.checkIsConvertVedio(ext) != 9) {//视频文件
                File newFile = new File(localFilePath);
                FileUtils.touch(newFile);
                //传送文件
                file.transferTo(newFile);

                localUploadVidio(projectPath + separator + filePath ,newFullFileName);

                File fileUploadFtpPic = new File(projectPath + separator + filePath + separator + newFileName + Dot + "jpg");
                File fileUploadFtp = new File(projectPath + separator + filePath + separator + newFileName + Dot + "flv");

                ftpUloadFile(filePath , fileBaseName + Dot + "flv", FileUtil.getInputStream(fileUploadFtp));
                ftpUloadFile(filePath , fileBaseName + Dot + "jpg", FileUtil.getInputStream(fileUploadFtpPic));

                sysFile.setFileThumbnails(filePath + separator + fileBaseName + Dot + "jpg");
                sysFile.setUploadUrl(filePath + separator + fileBaseName + Dot + "flv");
                sysFile.setFileUrl(FileUploadConfig.FTP_UPLOADURL + filePath + separator + fileBaseName + Dot + "flv");
            } else {
                ftpUloadFile(filePath, newFullFileName, file.getInputStream());
                sysFile.setUploadUrl(fileFulPath);
                sysFile.setFileUrl(FileUploadConfig.FTP_UPLOADURL + fileFulPath);
            }
            sysFile.setFileDomain(FileUploadConfig.FTP_UPLOADURL);
        }

        sysFile.setFileExt(ext);
        sysFile.setFileKind(FileExtSupport.fileKind(ext));
        sysFile.setFileName(fileBaseName);
        sysFile.setFileSize(fileSize);
        return sysFile;
    }


    /**
     * 上传视频文件的处理方法
     *
     * @param filePath
     * @param fileName
     * @return
     */
    private static boolean localUploadVidio(String filePath,String fileName) {
        String fileBaseName = FilenameUtils.getBaseName(fileName);
        String tempFile = filePath + separator + fileBaseName + Dot + "avi";
        String inputFile = filePath + separator + fileName;
        String outFile = filePath + separator + fileBaseName + Dot + "flv";
        String outPicFile = filePath + separator + fileBaseName + Dot + "jpg";
        VideoSupport convertSingleVideo = new VideoSupport(tempFile);
        return convertSingleVideo.convert(inputFile,outFile,outPicFile);
    }

    /**
     * 上传文件到FTP服务器
     *
     * @param path
     * @param filename
     * @param inputStream
     * @throws IOException
     */
    private static void ftpUloadFile(String path, String filename, InputStream inputStream) throws IOException {
        FlashFtpUtils.FlashFtpConfig flashFtpConfig = new FlashFtpUtils.FlashFtpConfig(FileUploadConfig.FTP_URL, Integer.valueOf(FileUploadConfig.FTP_PORT), FileUploadConfig.FTP_USERNAME, FileUploadConfig.FTP_PASSWORD, false);
        FlashFtpUtils.putFile(path, filename, inputStream, flashFtpConfig);
    }

    /**
     * @param @param  filePath
     * @param @throws IOException    设定文件
     * @return void    返回类型
     * @throws
     * @Description: 删除文件
     * @Title: deleteFile
     */
    public static void deleteFile(String filePath) throws IOException {
        if (StringUtils.equalsIgnoreCase(FileUploadConfig.SAVE_TYPE, "LOCAL")) {
            /** 获取当前请求 request**/
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            /** 当前项目的根目录 **/
            String projectPath = request.getSession().getServletContext().getRealPath(separator);
            FileUtils.deleteDirectory(new File(projectPath + filePath));
        } else {
            FlashFtpUtils.FlashFtpConfig flashFtpConfig = new FlashFtpUtils.FlashFtpConfig(FileUploadConfig.FTP_URL, Integer.valueOf(FileUploadConfig.FTP_PORT), FileUploadConfig.FTP_USERNAME, FileUploadConfig.FTP_PASSWORD, false);
            FlashFtpUtils.deleteFile(filePath, flashFtpConfig);
        }
    }





}
