package com.xqsight.common.freemarker;

import java.io.File;

/**
 * Created by wangganggang on 2017/3/7.
 *
 * @author zy8
 * @date 2017/03/07
 */
public class PathSupport {

    public static String packagePathToFilePath(String packagepath) {
        String result = null;
        if (packagepath != null) {
            result = packagepath.replace(".", File.separator);
        }
        return result;
    }
}
