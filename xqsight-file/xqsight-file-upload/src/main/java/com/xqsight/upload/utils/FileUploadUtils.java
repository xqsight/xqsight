/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.upload.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoleilu.hutool.io.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoleilu.hutool.util.RandomUtil;
import com.xqsight.commons.utils.DateFormatUtils;
import com.xqsight.upload.model.SysFile;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年5月9日 上午11:04:01
 *
 */
public class FileUploadUtils {
	
	private static Logger logger = LogManager.getLogger(FileUploadUtils.class);
	
	/**
	 * @throws IOException 
	 * 批量文件上传
	 * @Description: 
	 *
	 * @Title: uploadFile
	 * @param @param files
	 * @param @return    设定文件
	 * @return List<SysFile>    返回类型
	 * @throws
	 */
	public static List<SysFile> uploadFile(MultipartFile[] files,String savePath) throws IOException{
		List<SysFile> fileIdList = new ArrayList<SysFile>();
		for(MultipartFile multipartFile : files){
			fileIdList.add(uploadFile(multipartFile,savePath));
		}
		return fileIdList;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @Description: 单文件上传
	 *
	 * @Title: uploadFile
	 * @param @param files
	 * @param @return    设定文件
	 * @return SysFile    返回类型
	 * @throws
	 */
	public static SysFile uploadFile(MultipartFile file,String savePath) throws IOException{
		logger.debug("传入保存路径:savePath={}",savePath);
		
		String fileName  = file.getOriginalFilename();
		String ext  = FileUtil.extName(fileName);

		StringBuffer filePath = new StringBuffer();
		//文件路径
		filePath.append("/upload/").append(DateFormatUtils.yyyyMMdd.format(new Date()));
		filePath.append("/").append(RandomUtil.randomUUID()).append(".").append(ext);
		
		File newFile = FileUtil.touch(savePath + filePath.toString());
		
		//传送文件
		file.transferTo(newFile);
			
		SysFile sysFile = new SysFile();
		sysFile.setFileDomain(savePath);
		sysFile.setFileExt(ext);
		sysFile.setFileSize("" + file.getSize());
		sysFile.setFileUrl(filePath.toString());
		sysFile.setFileKind(fileKind(ext));
		sysFile.setFileName(fileName);
		return sysFile;
	}
	
	/**
	 * 获取文件类型
	 * 
	 * @Description: TODO
	 *
	 * @Title: fileKind
	 * @param @param ext
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public static String fileKind(String ext){
		String fileKind = "01";
		switch (ext.toLowerCase()) {
			case "mp4":
			case "avi":
				fileKind = "02";
				break;
			case "doc" :
			case "docx" :
			case "xls" :
			case "xlsx" :
			case "ppt" :
			case "pptx" :
			case "cvs" :
			case "pdf" :
				fileKind = "03";
				break;
			default:
				fileKind = "01";
				break;
		}
		return fileKind;
	}

	/**
	 * 
	 * @Description: 删除文件
	 *
	 * @Title: deleteFile
	 * @param @param filePath
	 * @param @throws IOException    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public static void deleteFile(String filePath) throws IOException{
		FileUtil.del(filePath);
	}
}
