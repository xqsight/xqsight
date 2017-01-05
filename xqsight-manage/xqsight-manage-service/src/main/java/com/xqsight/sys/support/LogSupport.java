package com.xqsight.sys.support;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.model.UserBaseModel;
import com.xqsight.common.model.constants.CacheKeyConstants;
import com.xqsight.commons.exception.Exceptions;
import com.xqsight.commons.web.SpringContextHolder;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.data.ehcache.core.CacheTemplate;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.sys.model.SysLog;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.service.SysLogService;
import com.xqsight.sys.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LogSupport {

	private static Logger logger = LogManager.getLogger(LogSupport.class);

	private static SysLogService sysLogService = SpringContextHolder.getBean(SysLogService.class);
	private static SysMenuService sysMenuService = SpringContextHolder.getBean(SysMenuService.class);
	private static CacheTemplate cacheTemplate = SpringContextHolder.getBean(CacheTemplate.class);
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title){
		saveLog(request, null, null, title);
	}
	
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title){
		UserBaseModel user = SSOUtils.getCurrentUser();
		if (user != null && user.getId() != null){
			SysLog sysLog = new SysLog();
			sysLog.setLogTitle(title);
			sysLog.setLogType(ex == null ? SysLog.TYPE_ACCESS : SysLog.TYPE_EXCEPTION);
			sysLog.setReqIp(WebUtils.getUserIp(request));
			sysLog.setReqData(request.getParameterMap());
			sysLog.setReqMethod(request.getMethod());
			sysLog.setReqUrl(request.getRequestURI());
			sysLog.setAgentUser(request.getHeader("user-agent"));
			sysLog.setCreateUserId(SSOUtils.getCurrentUserId().toString());
			// 异步保存日志
			new SaveLogThread(sysLog, handler, ex).start();
		}
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread{
		
		private SysLog sysLog;
		private Object handler;
		private Exception ex;
		
		public SaveLogThread(SysLog sysLog, Object handler, Exception ex){
			super(SaveLogThread.class.getSimpleName());
			this.sysLog = sysLog;
			this.handler = handler;
			this.ex = ex;
			logger.debug("init save log thread");
		}
		
		@Override
		public void run() {
			logger.debug("run save log thread");
			// 获取日志标题
			if (StringUtils.isBlank(sysLog.getLogTitle())){
				String permission = "";
				if (handler instanceof HandlerMethod){
					Method m = ((HandlerMethod)handler).getMethod();
					RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
					permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
				}
				sysLog.setLogTitle(getMenuNamePath(sysLog.getReqUrl(), permission));
			}
			// 如果有异常，设置异常信息
			sysLog.setException(Exceptions.getStackTraceAsString(ex));
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isBlank(sysLog.getLogTitle()) && StringUtils.isBlank(sysLog.getException())){
				return;
			}

			logger.debug("sysLog={}", JSON.toJSONString(sysLog));
			sysLogService.saveSysLog(sysLog);
		}
	}

	/**
	 * 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
	 */
	public static String getMenuNamePath(String requestUri, String permission){
		String href = requestUri;
		@SuppressWarnings("unchecked")
		Map<String, String> menuMap = (Map<String, String>) cacheTemplate.get(CacheKeyConstants.SYS_MENU_NAME_PATH_MAP);
		if (menuMap == null){
			menuMap =new HashMap();
			List<SysMenu> menuList = sysMenuService.querySysMenu();
			for (SysMenu menu : menuList){
				// 设置菜单名称路径
				if (StringUtils.isNotBlank(menu.getUrl())){
					menuMap.put(menu.getUrl(), menu.getMenuName());
				}
			}
			cacheTemplate.put(CacheKeyConstants.SYS_MENU_NAME_PATH_MAP, menuMap);
		}

		String menuNamePath = menuMap.get(href);

		if (StringUtils.isBlank(menuNamePath)) {
			Iterator<String> iterator = menuMap.keySet().iterator();
			while (iterator.hasNext()){
				String key = iterator.next();
				if(StringUtils.endsWith(href,key)){
					menuNamePath = menuMap.get(key);
					break;
				}
			}
		}
		return menuNamePath;
	}


}