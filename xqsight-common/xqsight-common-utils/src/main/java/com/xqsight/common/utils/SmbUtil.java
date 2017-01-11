package com.xqsight.common.utils;

import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

/**
 * SMB远程文件存取实用类
 * @author xqsight-jerry
 * @version $Id: SmbUtil.java, v 0.1 2013-9-6 下午1:47:51 xqsight-jerry Exp $
 */
public class SmbUtil {

	private static Log logger = LogFactory.getLog(SmbUtil.class);

	/**
	 * Description: 从共享目录拷贝文件到本地
	 * 
	 * @param remoteUrl
	 *            共享目录上的文件路径
	 * @param localDir
	 *            本地目录
	 */
	public static String smbGet(String remoteUrl, String localDir) {
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile remoteFile = new SmbFile(remoteUrl);
			String fileName = remoteFile.getName();
			localDir = localDir + File.separator + fileName;
			File localFile = new File(localDir);
			in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			byte[] buffer = new byte[1];
			while (in.read(buffer) != -1) {
				out.write(buffer);
			}
			out.flush();
		} catch (Exception e) {
			logger.error("读取远端文件异常", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return localDir;
	}

	/**
	 * Description: 从本地上传文件到共享目录
	 * 
	 * @param remoteUrl
	 *            共享文件目录
	 * @param localFilePath
	 *            本地文件绝对路径
	 */
	public static void smbPut(String remoteUrl, String localFilePath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			File localFile = new File(localFilePath);
			String fileName = localFile.getName();
			SmbFile remoteFilePath = new SmbFile(remoteUrl);
			if (!remoteFilePath.exists()) {
				remoteFilePath.mkdirs();
			}
			SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1];
			while (in.read(buffer) != -1) {
				out.write(buffer);
			}
			out.flush();
		} catch (Exception e) {
			logger.error("SMB写入异常", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	/**
	 *  Description: 从本地上传文件内容到共享目录
	 *  
	 * @param remoteUrl
	 * 				共享文件目录
	 * @param fileName
	 * 				生成文件名
	 * @param fileContent
	 * 				文件内容
     * @param charset
     * 				目标文件的字符编码
	 */
	public static void smbPut(String remoteUrl, String fileName, String fileContent,String charset) {
		OutputStream out = null;
		InputStream is = null;

		try {
			is = new ByteArrayInputStream(fileContent.getBytes(charset));
			SmbFile remoteFilePath = new SmbFile(remoteUrl);
            if (!remoteFilePath.exists()) {

                remoteFilePath.mkdirs();
            }
            SmbFile remoteFile = null;
			//判断远程路径是否已/结尾


			if(remoteUrl.matches(".+/")){
				remoteFile = new SmbFile(remoteUrl + fileName);
			} else {
				remoteFile = new SmbFile(remoteUrl + "/" + fileName);
			}
			
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1];
			while (is.read(buffer) != -1) {
				out.write(buffer);
			}
			out.flush();
		} catch (Exception e) {
			logger.error("SMB写入异常", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		String remoteUrl = "smb://administrator:aaaa@10.116.1.146/smbUpload";
		String fileName = "text.txt";
		String fileContent = "hello, world.";
		smbPut(remoteUrl, fileName, fileContent,"GBK");
	}
}
