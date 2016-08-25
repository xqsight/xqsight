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
    public static SysFile uploadFile(MultipartFile file) throws IOException {
        SysFile sysFile = new SysFile();

        String fileName = file.getOriginalFilename();
        String ext = FilenameUtils.getExtension(fileName);
        String fileSize = String.valueOf(file.getSize());
        String fileBaseName = FilenameUtils.getBaseName(fileName);

        /**  文件保存路径 upload + 文件类型 + 日期 **/
        StringBuffer filePath = new StringBuffer("upload").append(separator).append(FileExtSupport.fileKind(ext)).append(separator).append(DateFormatUtils.yyyyMMdd.format(new Date()));
        /**  上传文件的名称 ：原文件名 + 大小 + 随机数**/
        StringBuffer newFileName = new StringBuffer(fileBaseName).append("-").append(fileSize).append("-").append(RandomUtil.randomString(6));
        /**  上传文件的名称 ：原文件名 + 大小 + 随机数 + 扩展名  **/
        String newFullFileName = newFileName.append(Dot).append(ext).toString();
        /** 获取当前请求 request**/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /** 当前项目的根目录 **/
        String projectPath = request.getSession().getServletContext().getRealPath(separator);
        /** 本地上传文件处理 **/
        String localFilePath = projectPath + separator + filePath.append(separator).append(newFullFileName).toString();

        if (StringUtils.equalsIgnoreCase(FileUploadConfig.SAVE_TYPE, "LOCAL")) {
            File newFile = new File(localFilePath);
            FileUtils.touch(newFile);
            //传送文件
            file.transferTo(newFile);
            if (FileExtSupport.checkIsConvertVedio(ext) != 9) {//视频文件

                localUploadVidio(projectPath + separator + filePath.toString(),newFullFileName);

                sysFile.setFileThumbnails(FileUploadConfig.LOCAL_UPLOADURL + filePath.append(separator).append(newFileName).append(Dot).append("jpg").toString());
                sysFile.setFileUrl(FileUploadConfig.LOCAL_UPLOADURL + filePath.append(separator).append(newFileName).append(Dot).append("flv").toString());
            } else {
                sysFile.setFileUrl(FileUploadConfig.LOCAL_UPLOADURL + filePath.append(sysFile).append(newFullFileName).toString());
            }
            sysFile.setFileDomain(FileUploadConfig.LOCAL_UPLOADURL);
        } else {
            if (FileExtSupport.checkIsConvertVedio(ext) != 9) {//视频文件
                File newFile = new File(localFilePath);
                FileUtils.touch(newFile);
                //传送文件
                file.transferTo(newFile);

                localUploadVidio(projectPath + separator + filePath.toString(),newFullFileName);

                File fileUploadFtpPic = new File(projectPath + separator + filePath.append(separator).append(newFileName).append(Dot).append("jpg"));
                File fileUploadFtp = new File(projectPath + separator + filePath.append(separator).append(newFileName).append(Dot).append("flv"));

                ftpUloadFile(filePath.toString(), fileBaseName + Dot + "flv", FileUtil.getInputStream(fileUploadFtp));
                ftpUloadFile(filePath.toString(), fileBaseName + Dot + "jpg", FileUtil.getInputStream(fileUploadFtpPic));

                sysFile.setFileThumbnails(FileUploadConfig.FTP_UPLOADURL + filePath.append(separator).append(newFileName).append(Dot).append("jpg"));
                sysFile.setFileUrl(FileUploadConfig.FTP_UPLOADURL + filePath.append(separator).append(newFileName).append(Dot).append("flv"));
            } else {
                ftpUloadFile(filePath.toString(), newFullFileName, file.getInputStream());
                sysFile.setFileUrl(FileUploadConfig.FTP_UPLOADURL + filePath.toString());
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
            FileUtils.deleteDirectory(new File(filePath));
        } else {
            FlashFtpUtils.FlashFtpConfig flashFtpConfig = new FlashFtpUtils.FlashFtpConfig(FileUploadConfig.FTP_URL, Integer.valueOf(FileUploadConfig.FTP_PORT), FileUploadConfig.FTP_USERNAME, FileUploadConfig.FTP_PASSWORD, false);
            FlashFtpUtils.deleteFile(filePath, flashFtpConfig);
        }
    }





}
