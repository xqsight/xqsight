package com.xqsight.upload.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.alibaba.fastjson.JSONObject;


public class FtpClient {

	public static final String CODE_FORMAT = "yyyyMMdd";
	public static FTPClient ftpClient = new FTPClient();
	

	public static String webPath = "";//image/20160516/20160516162318_62.jpg


	private String ADDRESS;
	private int PORT;
	private String USERNAME;
	private String PWD;
	
	public FtpClient(String ADDRESS,int PORT,String USERNAME,String PWD){
		
		this.ADDRESS=ADDRESS;
		this.PORT=PORT;
		this.USERNAME=USERNAME;
		this.PWD=PWD;
		
		
	}
	
	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	

	public int getPORT() {
		return PORT;
	}

	public void setPORT(int pORT) {
		PORT = pORT;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String pWD) {
		PWD = pWD;
	}

	/**
	 * FTP连接
	 * @param connet		FTP的IP/域名
	 * @param port			端口（默认21）
	 * @param username		FTP用户名
	 * @param password		FTP密码
	 * @return
	 */
	public static void connect(String connet, int port, String username, String password){
		try {
			ftpClient.connect(connet, port);
			ftpClient.setControlEncoding("UTF-8");//设置字符集
			ftpClient.login(username, password);
			ftpClient.enterLocalPassiveMode();//设置为passive模式
			ftpClient.setBufferSize(1024);//设置上传文件缓存
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);//设置文件类型为二进制
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向FTP服务器上传文件
	 * @param saveFile		上传到FTP服务器上的文件名
	 * @param localFile		输入文件
	 * @param path			FTP服务器保存目录（/a）
	 * @param isWebPath		是否返回web路径
	 * @return
	 */
	@Deprecated
	public static String uploadFile(String saveFile, File localFile, String path, boolean isWebPath){
		String ftpPath = path;
		path += "/" + fomatCodeNow();
		FileInputStream fis = null;
		String returnPath = "";
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				fis = new FileInputStream(localFile);
				ftpClient.makeDirectory(ftpPath);
				ftpClient.makeDirectory(path);
				ftpClient.changeWorkingDirectory(path);//设置上传文件目录
				ftpClient.storeFile(new String(saveFile.getBytes("UTF-8"),"ISO-8859-1"), fis);
				if(isWebPath)
					returnPath = webPath + path + "/" + saveFile;
				else
					returnPath = path + "/" + saveFile;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//关闭流
			}
			ftpclose();
		}
		
		return returnPath;
	}
	
	/**
	 * 向FTP服务器上传文件（自动生成时间文件夹）
	 * @param saveFile		上传到FTP服务器上的文件名
	 * @param localFile		输入文件
	 * @param path			FTP服务器保存目录（/a）
	 * @param isWebPath		是否返回web路径
	 * @return				{"webpath":"sssss/text/20151218/ContractDemo.doc","code":true}     webpath：返回上传后web路径，code：true上传成功、false上传失败
	 */
	public static String uploadFile(String saveFile, InputStream localFile, String path, boolean isWebPath, String webPath){
		
		boolean code = false;
		String ftpPath = path;
		path = fomatCodeNow();
		//FileInputStream fis = null;
		String returnPath = "";
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
			//	fis = new FileInputStream(localFile);
				ftpClient.makeDirectory(ftpPath);
				ftpClient.changeWorkingDirectory(ftpPath);
				ftpClient.makeDirectory(path);
				//通过远程命令 穿件一个files文件夹  
				ftpClient.changeWorkingDirectory(path);//设置上传文件目录
				if(ftpClient.storeFile(new String(saveFile.getBytes("UTF-8"),"ISO-8859-1"), localFile)){//文件上传FTP，返回结果进行判断
					code = true;
					if(isWebPath){
						returnPath = webPath + ftpPath +"/"+ path + "/" + saveFile;
					}else{
						returnPath = path + "/" + saveFile;
					}
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != localFile){
				try {
					localFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//关闭流
			}
			ftpclose();
		}
		
		return stringTjson(code, returnPath);
	}
	
	/**
	 * 向FTP服务器上传文件
	 * @param saveFile		上传到FTP服务器上的文件名
	 * @param localFile		输入文件
	 * @param path			FTP服务器保存目录（/a）
	 * @param isWebPath		是否返回web路径
	 * @param wjjname       文件夹的名称
	 * @return
	 */
	@Deprecated
	public static String uploadFile(String saveFile, File localFile, String path,String wjjname, boolean isWebPath){
		String ftpPath = path;
		path += "/" + wjjname;
		FileInputStream fis = null;
		String returnPath = "";
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				fis = new FileInputStream(localFile);
				ftpClient.makeDirectory(ftpPath);
				ftpClient.makeDirectory(path);
				ftpClient.changeWorkingDirectory(path);//设置上传文件目录
				ftpClient.storeFile(new String(saveFile.getBytes("UTF-8"),"ISO-8859-1"), fis);
				if(isWebPath)
					returnPath = webPath + path + "/" + saveFile;
				else
					returnPath = path + "/" + saveFile;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//关闭流
			}
			ftpclose();
		}
		
		return returnPath;
	}
	
	/**
	 * 向FTP服务器上传文件（根据指定文件夹创建二级目录）
	 * @param saveFile		上传到FTP服务器上的文件名
	 * @param localFile		输入文件
	 * @param path			FTP服务器保存目录（/a）
	 * @param wjjname		二级目录名称
	 * @param isWebPath		是否返回web路径
	 * @param wjjname       文件夹的名称
	 * @return 				{"webpath":"sssss/text/20151218/ContractDemo.doc","code":true}     webpath：返回上传后web路径，code：true上传成功、false上传失败
	 */
	public static String uploadFile(String saveFile, File localFile, String path,String wjjname, boolean isWebPath, String webPath){
		boolean code = false;
		String ftpPath = path;
		path += "/" + wjjname;
		FileInputStream fis = null;
		String returnPath = "";
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				fis = new FileInputStream(localFile);
				ftpClient.makeDirectory(ftpPath);
				ftpClient.makeDirectory(path);
				ftpClient.changeWorkingDirectory(path);//设置上传文件目录
				if(ftpClient.storeFile(new String(saveFile.getBytes("UTF-8"),"ISO-8859-1"), fis)){//文件上传FTP，返回结果进行判断
					code = true;
					if(isWebPath){
						returnPath = webPath + path + "/" + saveFile;
					}else{
						returnPath = path + "/" + saveFile;
					}
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//关闭流
			}
			ftpclose();
		}
		
		return stringTjson(code, returnPath);
	}
	
	/**
	 * 向FTP服务器上传文件(不创建文件夹)
	 * @param saveFile		上传到FTP服务器上的文件名
	 * @param localFile		输入文件
	 * @param path			FTP服务器保存目录（/a/20151026）
	 * @param isWebPath		是否返回web路径
	 * @return
	 */
	@Deprecated
	public static String uploadFile2(String saveFile, File localFile, String path, boolean isWebPath){
		FileInputStream fis = null;
		String returnPath = "";
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				fis = new FileInputStream(localFile);
				ftpClient.makeDirectory(path);
				ftpClient.changeWorkingDirectory(path);//设置上传文件目录
				ftpClient.storeFile(new String(saveFile.getBytes("UTF-8"),"ISO-8859-1"), fis);
				if(isWebPath)
					returnPath = webPath + path + "/" + saveFile;
				else
					returnPath = path + "/" + saveFile;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//关闭流
			}
			ftpclose();
		}
		
		return returnPath;
	}
	
	/**
	 * 向FTP服务器上传文件（不创建文件夹）
	 * @param saveFile		上传到FTP服务器上的文件名
	 * @param localFile		输入文件
	 * @param path			FTP服务器保存目录（/a/20151026）
	 * @param isWebPath		是否返回web路径
	 * @return				{"webpath":"sssss/text/20151218/ContractDemo.doc","code":true}     webpath：返回上传后web路径，code：true上传成功、false上传失败
	 */
	public static String uploadFile2(String saveFile, File localFile, String path, boolean isWebPath, String webPath){
		boolean code = false;
		FileInputStream fis = null;
		String returnPath = "";
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				fis = new FileInputStream(localFile);
				ftpClient.makeDirectory(path);
				ftpClient.changeWorkingDirectory(path);//设置上传文件目录
				if(ftpClient.storeFile(new String(saveFile.getBytes("UTF-8"),"ISO-8859-1"), fis)){//文件上传FTP，返回结果进行判断
					code = true;
					if(isWebPath){
						returnPath = webPath + path + "/" + saveFile;
					}else{
						returnPath = path + "/" + saveFile;
					}
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//关闭流
			}
			ftpclose();
		}
		
		return stringTjson(code, returnPath);
	}
	
	/**
	 * 从FTP服务器下载文件（原名下载保存）
	 * @param remotePath	FTP服务器上的相对路径
	 * @param fileName		要下载的文件名
	 * @param localPath		下载后保存到本地的路径
	 * @return
	 */
	public static boolean downFile(String remotePath, String fileName, String localPath){
		boolean returnBool = false;
		OutputStream is = null;
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				ftpClient.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
				FTPFile[] fs = ftpClient.listFiles();
				for(FTPFile ff:fs){
					if(ff.getName().equals(fileName)){
						File localFile = new File(localPath + "/" + ff.getName());
						is = new FileOutputStream(localFile);
						ftpClient.retrieveFile(new String(ff.getName().getBytes("UTF-8"),"ISO-8859-1"), is);
						break;
					}    
				}
			}
			returnBool = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != is){
				try {
					is.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ftpclose();
		}
		return returnBool;
	}
	
	/**
	 * 从FTP服务器下载文件（重命名下载保存）
	 * @param remotePath	FTP服务器上的相对路径
	 * @param fileName		要下载的文件名
	 * @param localPath		下载后保存到本地的路径
	 * @param filename		保存文件名称
	 * @return
	 */
	public static boolean downFile(String remotePath, String fileName, String localPath, String filename){
		boolean returnBool = false;
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				ftpClient.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
				FTPFile[] fs = ftpClient.listFiles();
				File file = new File(localPath);
				if(!file.exists()){
					file.mkdir();
				}
				for(FTPFile ff:fs){
					if(ff.getName().equals(fileName)){
						File localFile = new File(localPath + "/" + filename);
						OutputStream is = new FileOutputStream(localFile);
						ftpClient.retrieveFile(new String(ff.getName().getBytes("UTF-8"),"ISO-8859-1"), is);
						is.flush();
						is.close();
						break;
					}    
				}
			}
			returnBool = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ftpclose();
		}
		return returnBool;
	}
	
	/**
	 * 从FTP服务器下载文件
	 * @param remotePath	FTP服务器上的相对路径
	 * @param fileName		要下载的文件名
	 * @param localPath		下载后保存到本地的路径
	 * @return              本地文件名称
	 */
	public static String downFileReturnFileName(String remotePath, String fileName, String localPath){
		
		String localFileName=fileName;
		OutputStream is = null;
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				ftpClient.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
				FTPFile[] fs = ftpClient.listFiles();
				for(FTPFile ff:fs){
					if(ff.getName().equals(fileName)){
						File localFile = new File(localPath + "/" + ff.getName());
						is = new FileOutputStream(localFile);
						ftpClient.retrieveFile(new String(ff.getName().getBytes("UTF-8"),"ISO-8859-1"), is);
					}    
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != is){
				try {
					is.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ftpclose();
		}
		return localFileName;
	}
	
	/**
	 * 从FTP服务器解析文件为流
	 * @param filePath		要下载的文件名（/ht/20150526/S00456R00003.pdf）
	 * @return
	 */
	public static InputStream parseFiletoStream(String filePath){
		InputStream is = null;
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				String[] s = filePath.split("/");
				String remotePath = s[1]+"/"+s[2];
				String fileName = s[3];
				ftpClient.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
				is = ftpClient.retrieveFileStream(new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
//				if (is != null) { 
//					is.close(); 
//		        } 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	
	/**
	 * 从FTP服务器删除文件
	 * @param filePath	FTP服务器上的相对路径  "/ht/20150526/item.jpg"
	 * @return
	 */
	public static boolean deleteFile(String filePath){
		boolean returnBool = false;
		try {
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){//判断FTP是否连接成功
				System.out.println("连接失败");
			}else {
				returnBool = ftpClient.deleteFile(filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return returnBool;
	}
	
	
	/**
	 * 将FTP用户退出、断开连接
	 */
	public static void ftpclose(){
		try {
			ftpClient.logout();
			if (ftpClient.isConnected()) {
				ftpClient.disconnect();//断开连接
			}
		} catch (IOException e) {
			e.printStackTrace();
		}//FTP用户退出
	}
	
	/**
	 * 把当前日期转换成短日期格式字符串   如：20150101
	 * @return
	 */
	public static String fomatCodeNow() {
		String ret = null;
		try {
			ret = formatDate(new Date(), CODE_FORMAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 把日期对象转换成指定日期格式字符串
	 * 
	 * @param date
	 *            格式化日期
	 * @param formatType
	 *            格式化格式
	 * @return
	 */
	public static String formatDate(Date date, String formatType) {
		String ret = null;
		try {
			if (date == null || formatType == null || formatType.equals("")) {
				return ret;
			}
			ret = new SimpleDateFormat(formatType).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 根据指定的字符截取相应的文件夹名称
	 * @param str			web访问路径
	 * @param pathname		上传路径；如：/fj/
	 * @return
	 */
	public static String subPath(String filePath, String pathname){
		if(null != filePath && !filePath.equals("")){
			return filePath.substring(filePath.lastIndexOf(pathname), filePath.lastIndexOf("/"));
		}
		return "";
	}
	
	/***
	 * 根据指定字符截取相应的文件名称
	 * @param filePath	文件路径
	 * @return
	 */
	public static String subFileName(String filePath){
		if(null != filePath && !filePath.equals("")){
			return filePath.substring(filePath.lastIndexOf("/") + 1);
		}
		return "";
	}
	
	public static String stringTjson(boolean code, String returnPath){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("webpath", returnPath);
		return JSONObject.toJSONString(map);
	}
	
	public static void main(String[] args) throws IOException {
		//System.out.println(FtpClient.subPath("http://192.168.1.23:8080/upload/fj/20150526/S00456R00003.docx", "/fj"));
		//System.out.println(FtpClient.subFileName("http://192.168.1.23:8080/upload/fj/20150526/S00456R00003.docx"));
//		FtpClient.connect("192.168.1.10", 21, "shcce", "123456");
		/*FtpClient.downFile(subPath("http://192.168.1.23:8080/upload/fj/20150526/S00456R00003.docx", "/fj/"), "S00456R00003.docx", "D:/");*/
//		FtpClient.connect("192.168.1.23", 21, "hyd", "123456");
//		File srcFile = new File("C:\\导入问题解决.txt");
//		System.out.println(FtpClient.uploadFile("1.txt",srcFile,"/xw",true));
		//FtpClient.downFile("/", "测试电厂-2015-09-SQ009.doc", "D:/");
//		System.out.println(FtpClient.downFile("ht/20150526/", "S00456R00003.pdf", "D:", "ax.pdf"));

//		System.out.println(FtpClient.parseFiletoStream("ht/20150526/S00456R00003.pdf"));
		//FtpClient.deleteFile("/item1.jpg");
//		FtpClient.connect("192.168.119.139", 21, "www", "123456");
//		try {
//			ftpClient.changeWorkingDirectory("news");
//			ftpClient.makeDirectory("aa");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//String wjjname="temp";
	//	System.out.println(uploadFile(saveFile,localFile,path,true,""));
		String urlString = "http://192.168.119.139:8080/haian-star/img.go?path=/home/www/image/20160518/20160518151614_281.png";
		System.out.println(urlString.split("=")[1]);
	}
}

