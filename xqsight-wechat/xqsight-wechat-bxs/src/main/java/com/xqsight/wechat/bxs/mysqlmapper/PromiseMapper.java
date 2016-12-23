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
	
	@Insert("insert into wx_promise (wx_user_id, promise_time, promise_address,promise_addr_detail,status,from_source,active, create_time, create_opr_id, update_time, upd_opr_id, remark) values("
	+ "#{wxUserId, jdbcType=NUMERIC},#{promiseTime, jdbcType=TIMESTAMP},#{promiseAddress, jdbcType=VARCHAR},#{promiseAddrDetail, jdbcType=VARCHAR},#{status, jdbcType=VARCHAR},#{fromSource, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void save(WxPromise wxPromise);
	
	@Update("update  wx_promise set status=#{status, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} where  promise_id=#{promiseId, jdbcType=NUMERIC}")
	void update(WxPromise wxPromise);
	
	@Update("update  wx_promise set status=#{status, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} where  promise_id=#{promiseId, jdbcType=NUMERIC}")
	void updateStatus(WxPromise wxPromise);
	
	@Delete("delete from wx_promise  where  promise_id=#{promiseId, jdbcType=NUMERIC}")
	void delete(@Param("promiseId") long promiseId);
	
	@Select("select * from wx_promise order by promise_time desc")
	List<WxPromise> query();
	
	@Select("select * from wx_promise where wx_user_id=#{wxUserId, jdbcType=NUMERIC} order by promise_time desc")
	List<WxPromise> queryByUserId(@Param("wxUserId") long wxUserId);
	
	@Select("select * from wx_promise where promise_id=#{promiseId, jdbcType=NUMERIC}")
	WxPromise querybyId(@Param("promiseId") long promiseId);
}
