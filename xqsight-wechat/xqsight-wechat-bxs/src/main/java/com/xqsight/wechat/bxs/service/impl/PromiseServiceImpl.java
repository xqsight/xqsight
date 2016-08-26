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
import com.xqsight.wechat.bxs.model.WxPromise;
import com.xqsight.wechat.bxs.mysqlmapper.PromiseMapper;
import com.xqsight.wechat.bxs.service.PromiseService;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月25日 下午8:49:07
 */
@Service
public class PromiseServiceImpl implements PromiseService {

	private Logger logger = LogManager.getLogger(PromiseServiceImpl.class);
	
	@Autowired
	private PromiseMapper promiseMapper;
	
	/**
	 * <p>Title: save</p>
	 * <p>Description: </p>
	 * @param wxPromise
	 * @see com.xqsight.wechat.bxs.service.PromiseService#save(WxPromise)
	 */
	@Override
	public void save(WxPromise wxPromise) {
		logger.debug("出入参数:{}", JSON.toJSONString(wxPromise));
		promiseMapper.save(wxPromise);
	}

	/**
	 * <p>Title: update</p>
	 * <p>Description: </p>
	 * @param wxPromise
	 * @see com.xqsight.wechat.bxs.service.PromiseService#update(WxPromise)
	 */
	@Override
	public void update(WxPromise wxPromise) {
		logger.debug("出入参数:{}", JSON.toJSONString(wxPromise));
		promiseMapper.update(wxPromise);
	}

	/**
	 * <p>Title: delete</p>
	 * <p>Description: </p>
	 * @param wxPromise
	 * @see com.xqsight.wechat.bxs.service.PromiseService#delete(WxPromise)
	 */
	@Override
	public void delete(long promiseId) {
		logger.debug("出入参数promiseId:{}", promiseId);
		promiseMapper.delete(promiseId);
	}

	/**
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.wechat.bxs.service.PromiseService#query()
	 */
	@Override
	public List<WxPromise> query() {
		return promiseMapper.query();
	}

	/**
	 * <p>Title: querybyId</p>
	 * <p>Description: </p>
	 * @param promiseId
	 * @return
	 * @see com.xqsight.wechat.bxs.service.PromiseService#querybyId(long)
	 */
	@Override
	public WxPromise querybyId(long promiseId) {
		logger.debug("出入参数promiseId:{}", promiseId);
		return promiseMapper.querybyId(promiseId);
	}

	/**
	 * <p>Title: queryByUserId</p>
	 * <p>Description: </p>
	 * @param wxUserCode
	 * @return
	 * @see com.xqsight.wechat.bxs.service.PromiseService#queryByUserId(String)
	 */
	@Override
	public List<WxPromise> queryByUserId(long wxUserId) {
		logger.debug("出入参数wxUserId:{}", wxUserId);
		return promiseMapper.queryByUserId(wxUserId);
	}

	/**
	 * <p>Title: updateStatus</p>
	 * <p>Description: </p>
	 * @param wxPromise
	 * @see com.xqsight.wechat.bxs.service.PromiseService#updateStatus(WxPromise)
	 */
	@Override
	public void updateStatus(WxPromise wxPromise) {
		logger.debug("出入参数:{}", JSON.toJSONString(wxPromise));
		promiseMapper.updateStatus(wxPromise);
	}

}
