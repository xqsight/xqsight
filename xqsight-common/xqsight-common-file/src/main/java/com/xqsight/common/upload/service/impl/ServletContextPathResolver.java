package com.xqsight.common.upload.service.impl;

import com.xqsight.common.upload.service.PathResolver;
import com.xqsight.common.upload.service.UploadConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * ServletContext路径获取实现
 *
 * @author wangganggang
 */
@Component
public class ServletContextPathResolver implements PathResolver {


    @Autowired
    private UploadConfig uploadConfig;

    @Override
    public String getPath(String uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(uploadConfig.getStorePath());

        if (StringUtils.isBlank(uri)) {
            return sb.toString();
        }

        if (!StringUtils.startsWith(uri, SEPARATOR)) {
            sb.append(File.separator);
        }
        sb.append(uri.replace('/', File.separatorChar));
        return sb.toString();
    }

    @Override
    public String getPath(String uri, String prefix) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.startsWith(prefix, PREFIX_IS_FILES)) {
            sb.append(prefix.substring(PREFIX_IS_FILES.length()));
        } else {
            sb.append(uploadConfig.getStorePath());
            if (StringUtils.isNotBlank(prefix)) {
                sb.append(prefix.replace('/', File.separatorChar));
            }
        }
        if (StringUtils.isBlank(uri)) {
            return sb.toString();
        }
        if (!StringUtils.startsWith(uri, SEPARATOR)) {
            sb.append(File.separator);
        }
        sb.append(uri.replace('/', File.separatorChar));

        return sb.toString();
    }
}
