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

    @Insert("insert into wx_user_info (wx_id, wx_user_code,wx_name,user_name,sex,tel_phone,qq,email,active, create_time, create_opr_id, update_time, upd_opr_id, remark) values("
            + "#{wxId, jdbcType=NUMERIC},#{wxUserCode, jdbcType=VARCHAR},#{wxName, jdbcType=VARCHAR},#{userName, jdbcType=VARCHAR},#{sex, jdbcType=NUMERIC},#{telPhone, jdbcType=VARCHAR},#{qq, jdbcType=VARCHAR},#{email, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
    void save(WxUserInfo wxUserInfo);

    @Update("update  wx_user_info set user_name=#{userName, jdbcType=VARCHAR},sex=#{sex, jdbcType=NUMERIC},update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} where  wx_user_id=#{wxUserId, jdbcType=NUMERIC}")
    void updateBase(WxUserInfo wxUserInfo);

    @Update("update  wx_user_info set tel_phone=#{telPhone, jdbcType=VARCHAR},user_name=#{userName, jdbcType=VARCHAR},sex=#{sex, jdbcType=NUMERIC},qq=#{qq, jdbcType=VARCHAR},email=#{email, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} where  wx_user_id=#{wxUserId, jdbcType=NUMERIC}")
    void update(WxUserInfo wxUserInfo);

    @Select("select * from wx_user_info order by promise_time desc")
    List<WxUserInfo> query();

    @Select("select * from wx_user_info where wx_user_code=#{wxUserCode, jdbcType=VARCHAR}")
    WxUserInfo queryByWxUserCode(@Param("wxUserCode") String wxUserCode);

    @Select("select * from wx_user_info where wx_id=#{wxId, jdbcType=NUMERIC}")
    List<WxUserInfo> querybyWxId(@Param("wxId") long wxId);

    @Select("select * from wx_user_info where wx_user_id=#{wxUserId, jdbcType=NUMERIC}")
    WxUserInfo querybyWxUserId(@Param("wxUserId") long wxUserId);
}
