package com.xqsight.common.upload.service;

/**
 * 路径获取接口
 *
 * @author wangganggang
 */
public interface PathResolver {

    String PREFIX_IS_FILES = "file:";

    String SEPARATOR = "/";

    String getPath(String uri);

    String getPath(String uri, String prefix);
}
