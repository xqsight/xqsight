package com.xqsight.common.upload.service;

/**
 * 路径获取接口
 *
 * @author wangganggang
 */
public interface PathResolver {

    public String getPath(String uri);

    public String getPath(String uri, String prefix);
}
