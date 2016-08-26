/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.wechat.bxs.model.WxPromise;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月25日 下午8:49:41
 */
public interface PromiseMapper {
	
	@Insert("INSERT INTO WX_PROMISE (WX_USER_ID, PROMISE_TIME, PROMISE_ADDRESS,PROMISE_ADDR_DETAIL,STATUS,FROM_SOURCE,ACTIVE, CREATE_TIME, CREATE_OPR_ID, UPDATE_TIME, UPD_OPR_ID, REMARK) VALUES(" 
	+ "#{wxUserId, jdbcType=NUMERIC},#{promiseTime, jdbcType=TIMESTAMP},#{promiseAddress, jdbcType=VARCHAR},#{promiseAddrDetail, jdbcType=VARCHAR},#{status, jdbcType=VARCHAR},#{fromSource, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void save(WxPromise wxPromise);
	
	@Update("UPDATE  WX_PROMISE SET STATUS=#{status, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  PROMISE_ID=#{promiseId, jdbcType=NUMERIC}")
	void update(WxPromise wxPromise);
	
	@Update("UPDATE  WX_PROMISE SET STATUS=#{status, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  PROMISE_ID=#{promiseId, jdbcType=NUMERIC}")
	void updateStatus(WxPromise wxPromise);
	
	@Delete("DELETE FROM WX_PROMISE  WHERE  PROMISE_ID=#{promiseId, jdbcType=NUMERIC}")
	void delete(@Param("promiseId") long promiseId);
	
	@Select("SELECT * FROM WX_PROMISE ORDER BY PROMISE_TIME DESC")
	List<WxPromise> query();
	
	@Select("SELECT * FROM WX_PROMISE WHERE WX_USER_ID=#{wxUserId, jdbcType=NUMERIC} ORDER BY PROMISE_TIME DESC")
	List<WxPromise> queryByUserId(@Param("wxUserId") long wxUserId);
	
	@Select("SELECT * FROM WX_PROMISE WHERE PROMISE_ID=#{promiseId, jdbcType=NUMERIC}")
	WxPromise querybyId(@Param("promiseId") long promiseId);
}
