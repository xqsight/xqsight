/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.controller;

import com.xqsight.common.support.MessageSupport;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月22日 下午7:15:39
 */
@Controller
@RequestMapping("/wechat/menu/")
public class BxsMenuController {

	private static Logger logger = LogManager.getLogger(BxsMenuController.class);

	@Autowired
	private WxMpService wxBxsMpService;

	@RequestMapping("publishmenu")
	public Object publishMenu(HttpServletRequest request){
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		WxMenu menu = new WxMenu();
	    WxMenuButton button1 = new WxMenuButton();
	    button1.setType("click");
	    button1.setName("今日热点");
	    button1.setKey("V1001_TODAY_FOCUS");
	    
	    WxMenuButton button2 = new WxMenuButton();
	    button2.setType("view");
	    button2.setName("在线预约");
	    button2.setUrl("http://bxswechat.xqsight.cn/wechat/bxs/menu/promise");
	    
	    WxMenuButton button3 = new WxMenuButton();
	    button3.setName("半小时科技");
	    
	    menu.getButtons().add(button1);
	    menu.getButtons().add(button2);
	    menu.getButtons().add(button3);
	    
	    WxMenuButton button31 = new WxMenuButton();
	    button31.setType("view");
	    button31.setName("关于我们");
	    button31.setUrl("http://bxswechat.xqsight.cn/banxiaoshi/page/aboutus.html");
	    
	    WxMenuButton button32 = new WxMenuButton();
	    button32.setType("view");
	    button32.setName("个人中心");
	    button32.setUrl("http://bxswechat.xqsight.cn/wechat/bxs/menu/persioncenter");
	    
	    WxMenuButton button34 = new WxMenuButton();
	    button34.setType("view");
	    button34.setName("我的预约");
	    button34.setUrl("http://bxswechat.xqsight.cn/wechat/bxs/menu/myPromise");
	    
	    WxMenuButton button33 = new WxMenuButton();
	    button33.setType("click");
	    button33.setName("联系我们");
	    button33.setKey("V3003_CONTACT_US");
	    
	    button3.getSubButtons().add(button31);
	    button3.getSubButtons().add(button32);
	    button3.getSubButtons().add(button34);
	    button3.getSubButtons().add(button33);
		    
		try {
			wxBxsMpService.getMenuService().menuCreate(menu);
			logger.debug("发布成功！");
			return MessageSupport.successMsg("发布成功");

		} catch (WxErrorException e) {
			logger.error("发布菜单出错，错误信息:{}",e.getMessage());
			e.printStackTrace();
			return MessageSupport.failureMsg("发布失败，失败原因");
		}
	}
}
