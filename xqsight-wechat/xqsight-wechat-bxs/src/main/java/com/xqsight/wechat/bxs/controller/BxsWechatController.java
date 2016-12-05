/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.controller;

import com.alibaba.fastjson.JSON;
import com.xqsight.wechat.bxs.component.BxsEventComponent;
import com.xqsight.wechat.bxs.component.BxsLinkComponent;
import com.xqsight.wechat.bxs.component.BxsMessageComponent;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年3月17日 下午7:40:43
  */
@Controller
@RequestMapping("/wechat")
public class BxsWechatController {

	private Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private WxMpConfigStorage wxBxsMpConfigStorage;
	
	@Autowired
	private WxMpService wxBxsMpService;
	
	@Autowired
	private WxMpMessageRouter wxBxsMpMessageRouter;
	
	@Autowired
	private BxsEventComponent BxsEventComponent;
	
	@Autowired
	private BxsMessageComponent BxsMessageComponent;
	
	@Autowired
	private BxsLinkComponent BxsLinkComponent;

	@RequestMapping("/init")
	public void initWechat(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			String signature = request.getParameter("signature");
			String nonce = request.getParameter("nonce");
			String timestamp = request.getParameter("timestamp");
			logger.info("signature:{},nonce:{},timestamp:{}", signature, nonce, timestamp);
			if (!wxBxsMpService.checkSignature(timestamp, nonce, signature)) {
				// 消息签名不正确，说明不是公众平台发过来的消息
				response.getWriter().println("非法请求");
				logger.info("消息验证签名不正确！");
				return;
			}
			String echostr = request.getParameter("echostr");
			logger.info("echostr:{}", echostr);
			if (StringUtils.isNotBlank(echostr)) {
				// 说明是一个仅仅用来验证的请求，回显echostr
				response.getWriter().println(echostr);
				return;
			}
			logger.info("传递的encryptType＝{}", request.getParameter("encrypt_type"));
			String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw" : request.getParameter("encrypt_type");
			logger.info("处理后的encryptType＝{}", encryptType);
			
			wxBxsMpMessageRouter
				.rule().async(false).msgType(WxConsts.XML_MSG_TEXT).matcher(BxsMessageComponent).handler(BxsMessageComponent).end()
				.rule().async(false).msgType(WxConsts.XML_MSG_LINK).matcher(BxsLinkComponent).handler(BxsLinkComponent).end()
				.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).matcher(BxsEventComponent).handler(BxsEventComponent).end();
			
			if ("raw".equals(encryptType)) {
				logger.info("传递的消息为明文消息");
				// 明文传输的消息
				WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
				logger.info("传入的消息[{}]", JSON.toJSONString(inMessage));
				WxMpXmlOutMessage outMessage = wxBxsMpMessageRouter.route(inMessage);
				logger.info("传出的消息[{}]", JSON.toJSONString(outMessage));
				if (outMessage != null) {
					response.getWriter().write(outMessage.toXml());
				}
				return;
			}
			if ("aes".equals(encryptType)) {
				logger.info("传递的消息为加密消息");
				// 是aes加密的消息
				String msgSignature = request.getParameter("msg_signature");
				logger.info("加密的消息[{}]", msgSignature);
				WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxBxsMpConfigStorage, timestamp, nonce, msgSignature);
				logger.info("传入的消息[{}]", JSON.toJSONString(inMessage));
				WxMpXmlOutMessage outMessage = wxBxsMpMessageRouter.route(inMessage);
				logger.info("传出的消息[{}]", JSON.toJSONString(outMessage));
				response.getWriter().write(outMessage.toEncryptedXml(wxBxsMpConfigStorage));
				return;
			}
			logger.info("不可识别的加密类型");
			response.getWriter().println("不可识别的加密类型");
			return;
		} catch (IOException e) {
			logger.info("返回出错，错误:{}", e.getMessage());
		}
	}
	
}
