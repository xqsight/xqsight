/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.wechat.bxs.model.WxUserInfo;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月25日 下午8:49:41
 */
public interface WxUserInfoMapper {
	
	@Insert("INSERT INTO WX_USER_INFO (WX_ID, WX_USER_CODE,WX_NAME,USER_NAME,SEX,TEL_PHONE,QQ,EMAIL,ACTIVE, CREATE_TIME, CREATE_OPR_ID, UPDATE_TIME, UPD_OPR_ID, REMARK) VALUES(" 
	+ "#{wxId, jdbcType=NUMERIC},#{wxUserCode, jdbcType=VARCHAR},#{wxName, jdbcType=VARCHAR},#{userName, jdbcType=VARCHAR},#{sex, jdbcType=NUMERIC},#{telPhone, jdbcType=VARCHAR},#{qq, jdbcType=VARCHAR},#{email, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void save(WxUserInfo wxUserInfo);
	
	@Update("UPDATE  WX_USER_INFO SET USER_NAME=#{userName, jdbcType=VARCHAR},SEX=#{sex, jdbcType=NUMERIC},update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  WX_USER_ID=#{wxUserId, jdbcType=NUMERIC}")
	void updateBase(WxUserInfo wxUserInfo);
	
	@Update("UPDATE  WX_USER_INFO SET TEL_PHONE=#{telPhone, jdbcType=VARCHAR},USER_NAME=#{userName, jdbcType=VARCHAR},SEX=#{sex, jdbcType=NUMERIC},QQ=#{qq, jdbcType=VARCHAR},EMAIL=#{email, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  WX_USER_ID=#{wxUserId, jdbcType=NUMERIC}")
	void update(WxUserInfo wxUserInfo);
	
	@Select("SELECT * FROM WX_USER_INFO ORDER BY PROMISE_TIME DESC")
	List<WxUserInfo> query();
	
	@Select("SELECT * FROM WX_USER_INFO WHERE WX_USER_CODE=#{wxUserCode, jdbcType=VARCHAR}")
	WxUserInfo queryByWxUserCode(@Param("wxUserCode") String wxUserCode);
	
	@Select("SELECT * FROM WX_USER_INFO WHERE WX_ID=#{wxId, jdbcType=NUMERIC}")
	List<WxUserInfo> querybyWxId(@Param("wxId") long wxId);
	
	@Select("SELECT * FROM WX_USER_INFO WHERE WX_USER_ID=#{wxUserId, jdbcType=NUMERIC}")
	WxUserInfo querybyWxUserId(@Param("wxUserId") long wxUserId);
}
