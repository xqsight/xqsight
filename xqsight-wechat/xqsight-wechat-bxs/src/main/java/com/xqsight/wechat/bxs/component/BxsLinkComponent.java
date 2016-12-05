/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.component;

import com.alibaba.fastjson.JSON;
import com.xqsight.wechat.bxs.service.WxUserInfoService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月22日 下午7:19:22
 */
@Component
public class BxsLinkComponent implements WxMpMessageHandler, WxMpMessageMatcher{

	private Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private WxUserInfoService wechatBxsService;
	
	/**
	 * <p>Title: match</p>
	 * <p>Description: </p>
	 * @param message
	 * @return
	 * @see WxMpMessageMatcher#match(WxMpXmlMessage)
	 */
	@Override
	public boolean match(WxMpXmlMessage message) {
		logger.debug("message:{}",JSON.toJSONString(message));
		return true;
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
		return null;
	}
}
