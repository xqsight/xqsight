package com.xqsight.upload.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqsight.upload.model.SysFile;

public interface FileUploadFTPService {
	
	List<SysFile> saveFTP(HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	void delFTP(List<String> delurl)throws Exception;
}
