/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.wechat.bxs.model.WxUserInfo;
import com.xqsight.wechat.bxs.mysqlmapper.WxUserInfoMapper;
import com.xqsight.wechat.bxs.service.WxUserInfoService;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月26日 下午3:36:43
 */
@Service
public class WxUserInfoServiceImpl implements WxUserInfoService {
	private Logger logger = LogManager.getLogger(WxUserInfoServiceImpl.class);
	
	@Autowired
	private WxUserInfoMapper xWxUserInfoMapper;
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: </p>
	 * @param wxUserInfo
	 * @see com.xqsight.wechat.bxs.service.WxUserInfoService#save(WxUserInfo)
	 */
	@Override
	public void save(WxUserInfo wxUserInfo) {
		logger.debug("出入参数:{}", JSON.toJSONString(wxUserInfo));
		xWxUserInfoMapper.save(wxUserInfo);
	}

	/**
	 * <p>Title: update</p>
	 * <p>Description: </p>
	 * @param wxUserInfo
	 * @see com.xqsight.wechat.bxs.service.WxUserInfoService#update(WxUserInfo)
	 */
	@Override
	public void update(WxUserInfo wxUserInfo) {
		logger.debug("出入参数:{}", JSON.toJSONString(wxUserInfo));
		xWxUserInfoMapper.update(wxUserInfo);
	}

	/**
	 * <p>Title: delete</p>
	 * <p>Description: </p>
	 * @param wxUserInfo
	 * @see com.xqsight.wechat.bxs.service.WxUserInfoService#delete(WxUserInfo)
	 */
	@Override
	public void delete(WxUserInfo wxUserInfo) {
		logger.debug("出入参数:{}", JSON.toJSONString(wxUserInfo));
		
	}

	/**
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.wechat.bxs.service.WxUserInfoService#query()
	 */
	@Override
	public List<WxUserInfo> query() {
		return xWxUserInfoMapper.query();
	}

	/**
	 * <p>Title: queryByWxUserCode</p>
	 * <p>Description: </p>
	 * @param wxUserCode
	 * @return
	 * @see com.xqsight.wechat.bxs.service.WxUserInfoService#queryByWxUserCode(String)
	 */
	@Override
	public WxUserInfo queryByWxUserCode(String wxUserCode) {
		logger.debug("出入参数wxUserCode:{}", wxUserCode);
		return xWxUserInfoMapper.queryByWxUserCode(wxUserCode);
	}

	/**
	 * <p>Title: queryByWxId</p>
	 * <p>Description: </p>
	 * @param wxId
	 * @return
	 * @see com.xqsight.wechat.bxs.service.WxUserInfoService#queryByWxId(long)
	 */
	@Override
	public List<WxUserInfo> queryByWxId(long wxId) {
		logger.debug("出入参数wxId:{}",wxId);
		return xWxUserInfoMapper.querybyWxId(wxId);
	}

	/**
	 * <p>Title: updateBase</p>
	 * <p>Description: </p>
	 * @param wxUserInfo
	 * @see com.xqsight.wechat.bxs.service.WxUserInfoService#updateBase(WxUserInfo)
	 */
	@Override
	public void updateBase(WxUserInfo wxUserInfo) {
		logger.debug("出入参数:{}", JSON.toJSONString(wxUserInfo));
		xWxUserInfoMapper.updateBase(wxUserInfo);
	}

}
