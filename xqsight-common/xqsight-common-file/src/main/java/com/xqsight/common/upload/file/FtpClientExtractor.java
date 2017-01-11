package com.xqsight.common.upload.file;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * ftp 文件上传
 *
 * @author wangganggang
 */
public interface FtpClientExtractor {
    void doInFtp(FTPClient client) throws IOException;
}
