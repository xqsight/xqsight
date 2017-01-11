package com.xqsight.common.upload;

/**
 * FileUploadException 文件上传异常
 *
 * @author wangganggang
 */
public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String[] args;

    public FileUploadException() {
        super();
    }

    public FileUploadException(String s) {
        super(s);
    }

    public FileUploadException(String s, String... args) {
        super(s);
        this.args = args;
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

}
