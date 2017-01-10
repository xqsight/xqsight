package com.xqsight.common.upload.resolver;

/**
 * 路径获取接口
 *
 * @author wangganggang
 */
public interface PathResolver {

    public String getPath(String uri);

    public String getPath(String uri, String prefix);
}
