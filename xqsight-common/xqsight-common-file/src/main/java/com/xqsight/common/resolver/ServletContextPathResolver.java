package com.xqsight.common.resolver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * ServletContext路径获取实现
 *
 * @author wangganggang
 */
public class ServletContextPathResolver implements PathResolver, ServletContextAware {

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String getPath(String uri) {
        uri = uri == null ? "" : uri;
        StringBuilder sb = new StringBuilder();
        sb.append(servletContext.getRealPath(""));
        if (!StringUtils.startsWith(uri, "/"))
            sb.append(File.separator);
        sb.append(uri.replace('/', File.separatorChar));
        return sb.toString();
    }

    public String getPath(String uri, String prefix) {
        uri = uri == null ? "" : uri;
        StringBuilder sb = new StringBuilder();
        if (StringUtils.startsWith(prefix, "file:")) {
            sb.append(prefix.substring(5));
        } else {
            sb.append(servletContext.getRealPath(""));
            if (StringUtils.isNotBlank(prefix)) {
                sb.append(prefix.replace('/', File.separatorChar));
            }
        }

        if (!StringUtils.startsWith(uri, "/"))
            sb.append(File.separator);

        sb.append(uri.replace('/', File.separatorChar));
        return sb.toString();
    }
}
