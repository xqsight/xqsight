/**
 * 新启信息技术有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.StringTokenizer;

import com.alibaba.fastjson.JSON;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 一次性(S)FTP操作类，每个方法都会进行连接、登陆、退出操作
 * @author xqsight-jerry
 * @version FlashFtpUtils.java, v 0.1 2015年9月9日 上午11:07:47 xqsight-jerry
 */
public class FlashFtpUtils {

	private static Logger logger = LogManager.getLogger(FlashFtpUtils.class);

    /**
     * 上传目录下所有内容到FTP/SFTP
     * @param remote
     * @param path
     * @param conf
     * @throws Exception
     */
    public static void putPath(String remote, Path path, FlashFtpConfig conf) throws Exception {
        if (conf.isSftp) {
            putPathWithSftp(remote, path, conf);
        } else {
            putPathWithftp(remote, path, conf);
        }
    }


    /**
     * FTP 上传文件
     * @param path
     * @param filename
     * @param inputStream
     * @param conf
     * @return
     * @throws IOException
     */
    public static boolean putFile( String path, String filename, InputStream inputStream ,FlashFtpConfig conf) throws IOException {
        logger.debug("上传文件结果path={},filename={},conf={}",path,filename, JSON.toJSONString(conf));
        boolean result;
        FTPClient ftpClient = connectAndLoginftp(conf);
        if (ftpClient.isConnected()) {
            ftpClient.setCharset(Charset.defaultCharset());
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            createDir(path,ftpClient);
            result = ftpClient.storeFile(new String(filename.getBytes("UTF-8"),"ISO-8859-1"), inputStream);
            logger.debug("上传文件结果result={}",result);
        } else {
            throw new IllegalStateException("Ftp Client not connected");
        }
        logoutAndDisconnectftp(ftpClient);
        return result;
    }

    /**
     * 循环创建目录
     * @param path
     * @param ftpClient
     * @return
     * @throws IOException
     */
    public static void createDir(String path,FTPClient ftpClient) throws IOException {
        StringTokenizer dirs = new StringTokenizer(path, File.separator);
        while (dirs.hasMoreElements()){
            String temp = dirs.nextElement().toString();
            if(ftpClient.changeWorkingDirectory(temp)) {
                ftpClient.changeWorkingDirectory(temp);
            }else{
                ftpClient.makeDirectory(temp);
                ftpClient.changeWorkingDirectory(temp);
            }
        }
    }



    /**
     * 删除 FTP 文件
     * @param pathName
     * @param conf
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String pathName,FlashFtpConfig conf) throws IOException {
        logger.debug("上传文件结果pathName={},conf={}",pathName,JSON.toJSONString(conf));
        boolean result = false;
        FTPClient ftpClient = connectAndLoginftp(conf);
        if (ftpClient.isConnected()) {
            result = ftpClient.deleteFile(pathName);
        } else {
            throw new IllegalStateException("Ftp Client not connected");
        }
        logoutAndDisconnectftp(ftpClient);
        return result;
    }






    /**
     * 上传目录下所有内容到FTP
     * @param remote
     * @param path
     * @param conf
     * @throws SocketException
     * @throws IOException
     */
    private static void putPathWithftp(String remote, Path path, FlashFtpConfig conf) throws SocketException, IOException {
        FTPClient ftpClient = connectAndLoginftp(conf);
        if (ftpClient.isConnected()) {
            ftpClient.changeWorkingDirectory(remote);
            ftpClient.appendFile(remote, Files.newInputStream(path));
        } else {
            throw new IllegalStateException("Ftp Client not connected");
        }
        logoutAndDisconnectftp(ftpClient);
    }

    /**
     * 上传目录下所有内容到SFTP
     * @param remote
     * @param path
     * @param conf
     * @throws SocketException
     * @throws IOException
     * @throws SftpException
     * @throws JSchException
     */
    private static void putPathWithSftp(String remote, Path path, FlashFtpConfig conf) throws SocketException, IOException, SftpException,
                                                                                       JSchException {
        Session sshSession = connectAndLoginSftp(conf);
        ChannelSftp sftp = (ChannelSftp) sshSession.openChannel("sftp");
        sftp.connect();
        if (sftp.isConnected()) {
            sftp.cd(remote);
            putAllFileToSftp(path, sftp);
        } else {
            throw new IllegalStateException("SFtp Client not connected");
        }
        logoutAndDisconnectSftp(sshSession);
    }

    /**
     * 递归操作，将一个目录下的文件（含目录结构）全部上传到SFTP上
     * @param baseDirPath
     * @param sftp
     * @throws SftpException
     * @throws IOException
     */
    private static void putAllFileToSftp(Path baseDirPath, ChannelSftp sftp) throws SftpException, IOException {

        File[] listFiles = baseDirPath.toFile().listFiles();
        for (File file : listFiles) {
            Path subPath = file.toPath();
            if (Files.isDirectory(subPath)) {
                String subPathName = baseDirPath.relativize(subPath).toString();

                // store initial base remote dir
                String baseRemote = sftp.pwd();
                try {
                    sftp.cd(subPathName);
                } catch (SftpException e) {
                    if (ChannelSftp.SSH_FX_NO_SUCH_FILE == e.id) {
                        sftp.mkdir(subPathName);
                        sftp.cd(subPathName);
                    }
                }
                putAllFileToSftp(subPath, sftp);
                // come back to initial base remote dir
                sftp.cd(baseRemote);
            } else {
                sftp.put(Files.newInputStream(subPath), subPath.getFileName().toString());
            }
        }

    }

    /**
     * 连接SFTP，并返回{@linkplain Session Session}
     * @param conf
     * @return
     */
    public static Session connectAndLoginSftp(FlashFtpConfig conf) {
        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(conf.getUserName(), conf.getHost(), conf.getPort());
            sshSession.setPassword(conf.getUserPwd());
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");

            sshSession.setConfig(sshConfig);
            sshSession.setTimeout(120000);// 超时120秒
            sshSession.connect();
            logger.info("Connected SFTP!Host={},Port={},UserName={}", conf.getHost(), conf.getPort(), conf.getUserName());
            return sshSession;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连接FTP，并返回{@linkplain FTPClient FTPClient}
     * @param conf
     * @return
     * @throws IOException
     */
    public static FTPClient connectAndLoginftp(FlashFtpConfig conf) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(conf.getHost(), conf.getPort());
        if (ftpClient.isConnected()) {
            ftpClient.login(conf.getUserName(), conf.getUserPwd());
        }
        logger.info("Connected SFTP!Host={},Port={},UserName={}", conf.getHost(), conf.getPort(), conf.getUserName());
        return ftpClient;
    }

    /**
     * 退出并断开FTP
     * @param ftpClient
     * @throws IOException
     */
    private static void logoutAndDisconnectftp(FTPClient ftpClient) throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
    }

    /**
     * 退出并断开SFTP
     * @param sshSession
     */
    private static void logoutAndDisconnectSftp(Session sshSession) {
        sshSession.disconnect();
    }

    public static class FlashFtpConfig {
        private String  host;     // 服务器IP地址
        private int     port = 0;     // 端口号
        private String  userName; // 用户名
        private String  userPwd;  // 密码
        private boolean isSftp = false;   // 是否SFTP

        public FlashFtpConfig(String host, int port, String userName, String userPwd){
            this.host = host;
            this.port = port;
            this.userName = userName;
            this.userPwd = userPwd;
            this.isSftp = false;
        }

        public FlashFtpConfig(String host, int port, String userName, String userPwd, boolean isFtp) {
            this.host = host;
            this.port = port;
            this.userName = userName;
            this.userPwd = userPwd;
            this.isSftp = isFtp;
        }

        /**
         * Getter method for property <tt>ip</tt>.
         *
         * @return property value of ip
         */
        public String getHost() {
            return host;
        }

        /**
         * Setter method for property <tt>ip</tt>.
         *
         * @param host value to be assigned to property ip
         */
        public void setHost(String host) {
            this.host = host;
        }

        /**
         * Getter method for property <tt>port</tt>.
         *
         * @return property value of port
         */
        public int getPort() {
            return port;
        }

        /**
         * Setter method for property <tt>port</tt>.
         *
         * @param port value to be assigned to property port
         */
        public void setPort(int port) {
            this.port = port;
        }

        /**
         * Getter method for property <tt>userName</tt>.
         *
         * @return property value of userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         * Setter method for property <tt>userName</tt>.
         *
         * @param userName value to be assigned to property userName
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        /**
         * Getter method for property <tt>userPwd</tt>.
         *
         * @return property value of userPwd
         */
        public String getUserPwd() {
            return userPwd;
        }

        /**
         * Setter method for property <tt>userPwd</tt>.
         *
         * @param userPwd value to be assigned to property userPwd
         */
        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        /**
         * Getter method for property <tt>isSftp</tt>.
         *
         * @return property value of isSftp
         */
        public boolean isSftp() {
            return isSftp;
        }

        /**
         * Setter method for property <tt>isSftp</tt>.
         *
         * @param isSftp value to be assigned to property isSftp
         */
        public void setSftp(boolean isSftp) {
            this.isSftp = isSftp;
        }

    }

}
