package com.xqsight.common.file;

public class DirCommonFileFilter implements CommonFileFilter {
	public boolean accept(CommonFile webFile) {
		return webFile.isDirectory();
	}
}
