package com.xqsight.upload.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.xqsight.upload.config.FileUploadConfig;
import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.service.FileUploadFTPService;
import com.xqsight.upload.utils.FileUploadUtils;
import com.xqsight.upload.utils.FtpClient;

@Service("fileUploadFTPService")
public class FileUploadFTPServiceImpl implements FileUploadFTPService{
	private static Logger logger = LogManager.getLogger(FileUploadFTPServiceImpl.class);
	
	@Override
	public List<SysFile> saveFTP(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String url = FileUploadConfig.FTP_UPLOADURL;
		FtpClient.connect(FileUploadConfig.FTP_URL, Integer.valueOf(FileUploadConfig.FTP_PORT), FileUploadConfig.FTP_USERNAME, FileUploadConfig.FTP_PASSWORD);


		// 定义允许上传的文件扩展名z
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 1000000;

		if (!ServletFileUpload.isMultipartContent(request))
		{
			throw new Exception("请选择文件。");
		}

		String dirName = request.getParameter("dir");
		String path = dirName;

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFileMap = mRequest.getFileMap();
		List<SysFile> sysls = new ArrayList<SysFile>();
		for(String mapKey : multipartFileMap.keySet())
		{
			MultipartFile multipartFile = multipartFileMap.get(mapKey);
			//附件类
			SysFile  sysfile = new SysFile();
			
			String fileName = multipartFile.getOriginalFilename();
			//文件名
			sysfile.setFileName(fileName);
			if (!multipartFile.isEmpty())
			{
				// 检查文件大小
				if (multipartFile.getSize() > maxSize)
				{
					 throw new Exception("上传文件大小超过限制。");
				}
				
				sysfile.setFileSize(multipartFile.getSize()+"");
				// 检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt))
				{
					throw new Exception("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
				}
				//文件扩展名
				sysfile.setFileExt(fileExt);
				//文件前缀
				sysfile.setFileDomain(fileName);
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				String picUrl = "";
				try
				{

					String saveFile = newFileName;

					String jsonMessage = FtpClient.uploadFile(saveFile, multipartFile.getInputStream(), path, true, "");

					JSONObject myJsonObject = JSONObject.parseObject(jsonMessage);
					picUrl = myJsonObject.getString("webpath");
				}
				catch (Exception e)
				{
					logger.error(e.getMessage(), e);
					logger.info("上传失败！" + e.getMessage());
				}
				//文件路径
				sysfile.setFileUrl(url + picUrl);
				//文件种类
				sysfile.setFileKind(FileUploadUtils.fileKind(fileExt));
				
				logger.debug("ftp上传路径：", url + picUrl);
				sysls.add(sysfile);
			}
		}
		return sysls;
	}

	@Override
	public void delFTP(List<String> delurl)throws Exception 
	{
		try{
			FtpClient.connect(FileUploadConfig.FTP_URL, Integer.valueOf(FileUploadConfig.FTP_PORT), FileUploadConfig.FTP_USERNAME, FileUploadConfig.FTP_PASSWORD);
			for(String url : delurl)
			{
				FtpClient.deleteFile(url);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			FtpClient.ftpclose();//关闭ftp
		}
	}

}
