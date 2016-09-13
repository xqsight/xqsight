/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.upload.mysqlmapper;

import java.util.List;

import com.xqsight.upload.model.vo.SysFileVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xqsight.upload.model.SysFile;

/**
 * <p>文件表数据库Mapper类</p>
 * <p>文件表</p>
 * @since 2016-05-09 08:16:30
 */
public interface SysFileMapper {

	@Insert(" INSERT INTO SYS_FILE(FILE_NAME,FILE_DOMAIN,FILE_URL,UPLOAD_URL,FILE_EXT,FILE_SIZE,ATTACHMENT_TYPE,FILE_KIND,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK)VALUES(#{fileName,jdbcType=VARCHAR},#{fileDomain,jdbcType=VARCHAR},#{fileUrl,jdbcType=VARCHAR},#{uploadUrl,jdbcType=VARCHAR},#{fileExt,jdbcType=VARCHAR},#{fileSize,jdbcType=VARCHAR},#{attachmentType,jdbcType=VARCHAR},#{fileKind,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{updateTime,jdbcType=TIMESTAMP},#{updOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "fileId")
	void saveSysFile(SysFileVo sysFile);
	
	@Delete(" DELETE FROM SYS_FILE WHERE FILE_ID=#{fileId,jdbcType=NUMERIC}")
	void deleteSysFileById(@Param("fileId") long fileId);
	
	@Delete(" DELETE FROM SYS_FILE WHERE FILE_ID IN (${fileId})")
	void deleteSysFileByFileIds(@Param("fileId") String fileId);

	@Select(" SELECT FILE_ID,FILE_DOMAIN,FILE_NAME,FILE_URL,FILE_EXT,FILE_SIZE,ATTACHMENT_TYPE,FILE_KIND,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM SYS_FILE WHERE FILE_ID=#{fileId,jdbcType=NUMERIC}")
	SysFile querySysFileById(@Param("fileId") long fileId);
	
	@Select(" SELECT FILE_ID,FILE_DOMAIN,FILE_NAME,FILE_URL,FILE_EXT,FILE_SIZE,ATTACHMENT_TYPE,FILE_KIND,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM SYS_FILE WHERE FILE_ID IN (${fileId})")
	List<SysFile> querySysFileByIds(@Param("fileId") String fileId);
}