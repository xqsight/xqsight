/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>文件表实体类</p>
 * <p>Table: sys_file - --> SysFile</p>
 * <p>文件表</p>
 * @since 2017-04-05 05:21:18
 * @author wangganggang
 */
@Data
public class SysFile extends BaseModel{

	/** 主键 */
    private Long fileId;

    /** file_name - 文件名称 */
    private String fileName;
    /** file_url - 附件URL */
    private String fileUrl;
    /** file_domain - 文件域 */
    private String fileDomain;
    /** file_ext - 扩展名 */
    private String fileExt;
    /** file_size - 大小 */
    private String fileSize;
    /** attachment_type - 附件类型 01:普通图 02:缩略图 03:LOGO */
    private String attachmentType;
    /** file_kind - 附件种类 Image Vedio Doc Excel Ppt */
    private String fileKind;

    @Override
    public Serializable getPK() {
        return this.fileId;
    }
}