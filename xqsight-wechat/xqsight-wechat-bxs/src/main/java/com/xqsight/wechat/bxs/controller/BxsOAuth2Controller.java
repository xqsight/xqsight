/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.controller;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.xqsight.wechat.bxs.common.WechatCommonConfig;
import com.xqsight.wechat.bxs.model.WxUserInfo;
import com.xqsight.wechat.bxs.service.WxUserInfoService;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月26日 下午6:23:49
 */
@Controller
@RequestMapping("/wechat/bxs/")
public class BxsOAuth2Controller {

	private static Logger logger = LogManager.getLogger(BxsOAuth2Controller.class);
	
	@Autowired
	private WxUserInfoService wxUserInfoService;
	
	@Autowired
	private WechatCommonConfig wxBxsCommonConfig;
	
	@Autowired
	private WxMpService wxBxsMpService;
	
	@RequestMapping("menu/{tokens}")
	public String promisePage(HttpServletRequest request,@PathVariable("tokens")String tokens){
		return "redirect:" + wxBxsMpService.oauth2buildAuthorizationUrl(wxBxsCommonConfig.getServerDomain() + "/wechat/bxs/oauth2", WxConsts.OAUTH2_SCOPE_BASE, tokens);
	}
	
//	@RequestMapping("persioncenter")
//	public String persionCenterPage(HttpServletRequest request){
//		return "redirect:" + wxBxsMpService.oauth2buildAuthorizationUrl(wxBxsCommonConfig.getServerDomain() + "/wechat/bxs/oauth2", WxConsts.OAUTH2_SCOPE_BASE, "persioncenter");
//	}
//	
//	@RequestMapping("mypromise")
//	public String myPromisePage(HttpServletRequest request){
//		return "redirect:" + wxBxsMpService.oauth2buildAuthorizationUrl(wxBxsCommonConfig.getServerDomain() + "/wechat/bxs/oauth2", WxConsts.OAUTH2_SCOPE_BASE, "mypromise");
//	}
	
	@RequestMapping("oauth2")
	public String redirectUrl(String code,String state){
		String openId = "";
		try {
			logger.info("OAuth2授权获取code:{},state:{}",code,state);
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxBxsMpService.oauth2getAccessToken(code);
			openId = wxMpOAuth2AccessToken.getOpenId();
			logger.info("OAuth2授权获取OAuth2AccessToken:{}",JSON.toJSONString(wxMpOAuth2AccessToken));
			//wxMpUser = wxBxsMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		} catch (WxErrorException e) {
			logger.error("OAuth2获取用户信息失败，错误原因:{}",JSON.toJSONString(e.getError()));
			e.printStackTrace();
		}
		
		logger.info("OAuth2授权获取openId:{}",openId);
		
		String redirectUrl = null;
		//根据openid查询用户是否绑定，绑定怎预约，未绑定则登陆绑定
		WxUserInfo wxUserInfo = wxUserInfoService.queryByWxUserCode(openId);
		
		if(wxUserInfo !=null && StringUtils.isNotBlank(wxUserInfo.getTelPhone())){
			if(StringUtils.equalsIgnoreCase(state, "promise")){
				redirectUrl = wxBxsCommonConfig.getPermiseUrl() + "?1=1&" + wxUserInfo.toString();
			}else if (StringUtils.equalsIgnoreCase(state, "persioncenter")) {
				redirectUrl = wxBxsCommonConfig.getPersonCenter()+ "?1=1&" + wxUserInfo.toString();
			}else if (StringUtils.equalsIgnoreCase(state, "myPromise")) {
				redirectUrl = wxBxsCommonConfig.getMyPromise()+ "?1=1&" + wxUserInfo.toString();
			}
		}else {
			redirectUrl = wxBxsCommonConfig.getLoginUrl();
		}
		if(StringUtils.isNotBlank(openId))
			redirectUrl = StringUtils.contains(redirectUrl, "?") ? (redirectUrl + "&openid=" + openId) : (redirectUrl + "?openid=" + openId);
		logger.info("OAuth2授权成功redirectUrl:{}",redirectUrl);
		return  "redirect:" + redirectUrl;
	}
}
