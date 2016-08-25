package com.xqsight.common.support;

import com.xqsight.common.file.FileHandler;
import com.xqsight.common.file.FtpTemplate;
import com.xqsight.common.web.PathResolver;
import jdk.nashorn.internal.objects.Global;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;

/**
 * Created by user on 2016/8/8.
 */
public class PublishPoint {

    private static final long serialVersionUID = 1L;
    /**
     * 文件系统
     */
    public static int METHOD_FILE = 1;
    /**
     * FTP
     */
    public static int METHOD_FTP = 2;
    /**
     * HTML发布点
     */
    public static int TYPE_HTML = 1;
    /**
     * 附件发布点
     */
    public static int TYPE_UPLOAD = 2;

    private Integer id;

    private String name;
    private String description;
    private String storePath;
    private String displayPath;
    private String ftpHostname;
    private Integer ftpPort;
    private String ftpUsername;
    private String ftpPassword;
    private Integer seq;
    private Integer method;
    private Integer type;

    // @Transient
    // public boolean isPointFile(String filename) {
    // return StringUtils.startsWith(filename, getUrlPrefix());
    // }

    /**
     * 获取显示路径，如为Web上下文发布，则加上上下文路径。如/uploads, /ctx/uploads
     *
     * @return
     */
    public String getUrlPrefix() {
        StringBuilder sb = new StringBuilder();
        if (getMethod() == METHOD_FILE && !StringUtils.startsWith(getStorePath(), "file:")) {
            //String ctx = getGlobal().getContextPath();
//            if (StringUtils.isNotBlank(ctx)) {
//                sb.append(ctx);
//            }
        }
        String displayPath = getDisplayPath();
        if (StringUtils.isNotBlank(displayPath)) {
            sb.append(displayPath);
        }
        return sb.toString();
    }


    public boolean isFtpMethod() {
        return getMethod() == METHOD_FTP;
    }

    public FtpTemplate getFtpTemplate() {
        return new FtpTemplate(getFtpHostname(), getFtpPort(),  getFtpUsername(), getFtpPassword());
    }

    public FileHandler getFileHandler(PathResolver pathResolver) {
        String prefix = getStorePath();
        FileHandler fileHandler;
        if (isFtpMethod()) {
            fileHandler = FileHandler.getFileHandler(getFtpTemplate(), prefix);
        } else {
            fileHandler = FileHandler.getFileHandler(pathResolver, prefix);
        }
        return fileHandler;
    }

    public String getDisplayDomain() {
        URI uri = URI.create(getDisplayPath());
        return uri.getHost();
    }

    public void applyDefaultValue() {
        if (getSeq() == null) {
            setSeq(Integer.MAX_VALUE);
        }
        if (getMethod() == null) {
            setMethod(METHOD_FILE);
        }
        if (getType() == null) {
            setType(TYPE_HTML);
        }
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStorePath() {
        return this.storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getDisplayPath() {
        return this.displayPath;
    }

    public void setDisplayPath(String displayPath) {
        this.displayPath = displayPath;
    }

    public String getFtpHostname() {
        return this.ftpHostname;
    }

    public void setFtpHostname(String ftpHostname) {
        this.ftpHostname = ftpHostname;
    }

    public Integer getFtpPort() {
        return this.ftpPort;
    }

    public void setFtpPort(Integer ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUsername() {
        return this.ftpUsername;
    }

    public void setFtpUsername(String ftpUsername) {
        this.ftpUsername = ftpUsername;
    }

    public String getFtpPassword() {
        return this.ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getMethod() {
        return this.method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
