/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.component;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年3月17日 下午7:55:00
  */
@Component
public class BxsMessageComponent implements WxMpMessageHandler, WxMpMessageMatcher {

	private Random random = new Random();
	
	private Pattern pattern = Pattern.compile("\\d+");
	
	/**
	 * <p>Title: match</p>
	 * <p>Description: </p>
	 * @param message
	 * @return
	 * @see WxMpMessageMatcher#match(WxMpXmlMessage)
	 */
	@Override
	public boolean match(WxMpXmlMessage message) {
		return isUserAnswering(message) || isUserWantGuessNum(message);
	}
	
	private boolean isUserWantGuessNum(WxMpXmlMessage message){
		return message.getContent().contains("猜数字");
	}
	
	
	private boolean isUserAnswering(WxMpXmlMessage message){
		return pattern.matcher(message.getContent()).matches();
	}

	protected WxMpXmlOutMessage letsGo(WxMpXmlMessage wxMessage, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
	    WxSession session = sessionManager.getSession(wxMessage.getFromUser());
		WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
	    		.fromUser(wxMessage.getToUser())
	    		.toUser(wxMessage.getFromUser()).build();
	    if (session.getAttribute("guessing") == null) {
	    	m.setContent("请猜一个100以内的数字");
	    } else {
	    	m.setContent("放弃了吗？那请重新猜一个100以内的数字");
	    }

	    session.setAttribute("guessing", Boolean.TRUE);
	    session.setAttribute("number", random.nextInt(100));
	    return m;
	  }

	protected WxMpXmlOutMessage giveHint(WxMpXmlMessage wxMessage, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

	    WxSession session = sessionManager.getSession(wxMessage.getFromUser());
	    WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
	    		.fromUser(wxMessage.getToUser())
	    		.toUser(wxMessage.getFromUser()).build();
	    if (session.getAttribute("guessing") == null) {
	      return null;
	    }
	    boolean guessing = (Boolean) session.getAttribute("guessing");
	    if (!guessing) {
	      return null;
	    }

	    int answer = (Integer) session.getAttribute("number");
	    int guessNumber = Integer.valueOf(wxMessage.getContent());
	    if (guessNumber < answer) {
	    	m.setContent("小了");
	    } else if (guessNumber > answer) {
	    	m.setContent("大了");
	    } else {
	    	m.setContent("Bingo!");
	    	session.removeAttribute("guessing");
	    }
	    return m;
	  }

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
		if (isUserWantGuessNum(wxMpXmlMessage)) {
			return letsGo(wxMpXmlMessage, wxMpService, wxSessionManager);
		}

		if (isUserAnswering(wxMpXmlMessage)) {
			return giveHint(wxMpXmlMessage, wxMpService, wxSessionManager);
		}
		return null;
	}
}
