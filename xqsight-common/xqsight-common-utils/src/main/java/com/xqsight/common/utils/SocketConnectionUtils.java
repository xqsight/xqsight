/**
 * 新启信息技术有限责任公司
 * Copyright (c) 1994-2013 All Rights Reserved.
 */
package com.xqsight.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

/**
 * 连接银行的Socket端
 * 
 * @author xqsight-jerry
 * @version $Id: BankClientUtils.java, v 0.1 2013-8-19 下午5:51:41 xqsight-jerry Exp $
 */
public class SocketConnectionUtils {

	private static Log logger = LogFactory.getLog(SocketConnectionUtils.class);
	
	/**
	 * 通过socket连接
	 * 
	 * @param connUrl
	 *            ip地址
	 * @param port
	 *            端口
	 * @param connTimeout
	 *            连接超时时间
	 * @param readTimeout
	 *            读取数据超时时间
	 * @param content
	 *            发送内容
	 * @return 服务端返回内容(BASE64编码字符串)
	 */
	public static String conn(String connUrl, int port, int connTimeout, int readTimeout, String content) throws IOException {
		return conn(connUrl, port, connTimeout, readTimeout, content, "UTF-8");
	}


	/**
	 * 通过socket连接
	 * 
	 * @param connUrl
	 *            ip地址
	 * @param port
	 *            端口
	 * @param connTimeout
	 *            连接超时时间
	 * @param readTimeout
	 *            读取数据超时时间
	 * @param content
	 *            发送内容
	 * @param charSet
	 * 			  编码
	 * @return 服务端返回内容(BASE64编码字符串)
	 * @throws IOException 
	 */
	public static String conn(String connUrl, int port, int connTimeout, int readTimeout, String content, String charSet) throws IOException {

		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		ByteArrayOutputStream baos = null;
		try {
			socket = new Socket();
			socket.setSoTimeout(readTimeout);
			socket.connect(new InetSocketAddress(connUrl, port), connTimeout);
			
			byte[] bytes = content.getBytes(StringUtils.hasLength(charSet) ? charSet : "UTF-8");
			
			dos = new DataOutputStream(socket.getOutputStream()) ;
			dos.write(bytes);
			dos.flush();
			if(logger.isInfoEnabled()){
				logger.info("向服务端发送报文成功，等待服务端返回报文");
			}
			
		    byte[] buff = new byte[1024];
			dis = new DataInputStream(socket.getInputStream());
			baos = new ByteArrayOutputStream();
			int n;
			while((n = dis.read(buff)) > 0){
				baos.write(buff,0, n);
			}
			
			byte[] resultBuff = baos.toByteArray();
			if(logger.isInfoEnabled()){
				logger.info("从服务端接收报文成功");
			}
			return Base64.encodeBase64String(resultBuff);
			
			
		} catch (IOException e) {
			logger.error("Socket连接发生IO错误", e);
			throw e;
		}  finally {
			try {
				if (socket != null) {
					socket.close();
				}
				if (dis != null) {
					dis.close();
				}
				if (dos != null) {
					dos.close();
				}
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				logger.error("Socket操作流关闭时发生IO错误", e);
				throw e;
			}
		}
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//查询
//		BufferedReader br = new BufferedReader(new FileReader(new File("D:\\temp\\6W0100.xml")));
		//文件
//		BufferedReader br = new BufferedReader(new FileReader(new File("D:\\temp\\6W2102.xml")));
		//代发代扣信息查询
		BufferedReader br = new BufferedReader(new FileReader(new File("/Users/iosdev/Documents/temp/fbdl/ccb/6w2104.xml")));
		String tmp = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (tmp != null) {
			sb.append(tmp);
			tmp = br.readLine();
		}
		br.close();
		String content = sb.toString();
		System.out.println("request:" + content);
		System.out.println("*****************************");
		String result = conn("10.116.1.146", 12346, 200000, 200000, content);
		System.out.println("response:" + result);
		System.out.println(Base64.decodeBase64(result));
	}

}
