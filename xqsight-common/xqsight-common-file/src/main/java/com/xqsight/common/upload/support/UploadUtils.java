package com.xqsight.common.upload.support;

import com.xqsight.common.upload.Uploader;
import org.apache.commons.lang3.StringUtils;

import static com.xqsight.common.upload.Uploader.QUICK_UPLOAD;
import static com.xqsight.common.upload.Uploader.THUMBNAIL;

/**
 * UploadUtils
 *
 * @author wangganggang
 */
public class UploadUtils {

    public static String getUrl(Integer appId, String type, String extension) {
        StringBuilder name = new StringBuilder();
        name.append('/').append(appId);
        name.append('/').append(type);
        name.append('/').append(QUICK_UPLOAD);
        name.append(Uploader.randomPathname(extension));
        return name.toString();
    }

    public static String getThumbnailPath(String path) {
        if (StringUtils.isBlank(path))
            return path;

        int index = path.lastIndexOf('.');
        if (index != -1) {
            return path.substring(0, index) + THUMBNAIL + path.substring(index);
        } else {
            return path;
        }
    }
}
