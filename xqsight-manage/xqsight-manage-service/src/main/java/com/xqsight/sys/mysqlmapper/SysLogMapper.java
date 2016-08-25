package com.xqsight.sys.mysqlmapper;


import com.xqsight.sys.model.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>系统日志数据库Mapper类</p>
 * <p>系统日志</p>
 * @since 2016-08-24 05:17:09
 */
public interface SysLogMapper {

	@Insert(" INSERT INTO sys_log(log_type,log_title,log_desc,req_ip,req_url,req_method,req_data,exception,agent_user,create_opr_id,create_time,remark)VALUES(#{logType,jdbcType=VARCHAR},#{logTitle,jdbcType=VARCHAR},#{logDesc,jdbcType=VARCHAR},#{reqIp,jdbcType=VARCHAR},#{reqUrl,jdbcType=VARCHAR},#{reqMethod,jdbcType=VARCHAR},#{reqData,jdbcType=VARCHAR},#{exception,jdbcType=VARCHAR},#{agentUser,jdbcType=VARCHAR},#{createOprId,jdbcType=VARCHAR},#{createTime,jdbcType=TIMESTAMP},#{remark,jdbcType=VARCHAR})")
	void saveSysLog(SysLog sysLog);
	
	@Update(" UPDATE sys_log SET log_type=#{logType,jdbcType=VARCHAR},log_title=#{logTitle,jdbcType=VARCHAR},log_desc=#{logDesc,jdbcType=VARCHAR},req_ip=#{reqIp,jdbcType=VARCHAR},req_url=#{reqUrl,jdbcType=VARCHAR},req_method=#{reqMethod,jdbcType=VARCHAR},req_data=#{reqData,jdbcType=VARCHAR},exception=#{exception,jdbcType=VARCHAR},agent_user=#{agentUser,jdbcType=VARCHAR},create_opr_id=#{createOprId,jdbcType=VARCHAR},create_time=#{createTime,jdbcType=TIMESTAMP},remark=#{remark,jdbcType=VARCHAR} WHERE log_id=#{logId,jdbcType=NUMERIC}")
	void updateSysLog(SysLog sysLog);
	
	@Delete(" DELETE FROM sys_log WHERE log_id=#{logId,jdbcType=NUMERIC}")
	void deleteSysLog(@Param("logId") Long logId);
	
	@Select(" SELECT log_type,log_title,log_desc,req_ip,req_url,req_method,req_data,exception,agent_user,create_opr_id,create_time,remark FROM sys_log WHERE log_id=#{logId,jdbcType=NUMERIC}")
	SysLog querySysLogById(@Param("logId") Long logId);

	@Select(" SELECT log_type,log_title,log_desc,req_ip,req_url,req_method,req_data,exception,agent_user,create_opr_id,create_time,remark FROM sys_log")
	List<SysLog> querySysLog();
}