package com.xqsight.upload.file;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public interface FtpClientExtractor {
	void doInFtp(FTPClient client) throws IOException;
}
