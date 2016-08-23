/**
 * 
 */
package com.xqsight.commons.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 一些与{@linkplain HttpServletRequest HttpServletRequest}、{@linkplain HttpServletResponse HttpServletResponse}相关的操作
 * @author xqsight-jerry
 *
 */
public class WebUtils {
    
    private final static Logger logger = LogManager.getLogger(WebUtils.class);
    
    /** 空的fastjson序列化特性数组,当需要返回JSON对象且使用系统默认序列化配置时，作为放弃个性配置的标志参数 */
    public final static SerializerFeature[] EMPTY_SERALIZER_FEATURE_ARRAY = new SerializerFeature[0];
    
    /**
     * 根据content-type来转换{@linkplain org.springframework.web.bind.annotation.ResponseBbody ResponseBody}声明的方法的返回对象<br/>
     * 若是text/html 或者 application/json则直接返回该对象，若是application/jsonp，则封装一个{@linkplain JSONPObject JSONPObject}返回。
     * 使用系统默认的序列化特性来序列化对象。<br/><br/>
     * 
     * 若需要返回JSONP对象，请保证{@linkplain HttpServletRequest request}参数中带有callback参数
     * @param request
     * @param obj
     * @return
     */
    public static Object getResponseBody(HttpServletRequest request, Object obj){
        return getResponseBody(request, obj, EMPTY_SERALIZER_FEATURE_ARRAY);
    }
    
    /**
     * 根据content-type来转换{@linkplain org.springframework.web.bind.annotation.ResponseBbody ResponseBody}声明的方法的返回对象<br/>
     * 若是text/html 或者 application/json则直接返回该对象，若是application/jsonp，则封装一个{@linkplain JSONPObject JSONPObject}返回。
     * 若参数{@linkplain com.alibaba.fastjson.serializer.SerializerFeature feature} 不为空，则使用<code>feature</code>进行JSON对象的序列化
     * 来替换系统默认配置<br/><br/>
     * 
     * 若需要返回JSONP对象，请保证{@linkplain HttpServletRequest request}参数中带有callback参数
     * @param request
     * @param obj
     * @param feature
     * @return
     */
    public static Object getResponseBody(HttpServletRequest request, Object obj, SerializerFeature... feature){
        String accept = request.getHeader("Accept");
        if(StringUtils.hasLength(accept)){
            List<MediaType> mediaTypes = MediaType.parseMediaTypes(accept);
            MediaType.sortBySpecificityAndQuality(mediaTypes);
            String fullType = mediaTypes.get(0).getType() + "/" + mediaTypes.get(0).getSubtype();
            logger.info("full Type is {}", fullType);
            switch(fullType){
                case "text/html":
                case "text/xml":
                case "application/xml":
                    return obj;
                case "application/jsonp":
                    Assert.hasLength(request.getParameter("callback"), "JSONP **callback** parameter can not be null!");
                default:
                    JSONPObject jsonpObj = new JSONPObject();
                    jsonpObj.setFeatue(feature);
                    jsonpObj.setCallBackFunction(request.getParameter("callback"));
                    jsonpObj.setJsonObject(obj);
                    return jsonpObj; 
            }
        } else {
            JSONPObject jsonpObj = new JSONPObject();
            jsonpObj.setFeatue(feature);
            jsonpObj.setCallBackFunction(request.getParameter("callback"));
            jsonpObj.setJsonObject(obj);
            return jsonpObj;
        }
        
    }

    /**
     * 获取用户IP
     * @param request
     * @return
     */
    public static String getUserIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(StringUtils.hasLength(ip)){
            String[] segmentIps = ip.split(",");
            if(segmentIps.length > 1){
                for(int i = 0; i < segmentIps.length; i++){
                    String tmp = segmentIps[i];
                    if(!StringUtils.hasLength(tmp) || "unknown".equalsIgnoreCase(tmp.trim())){
                        continue;
                    }
                    ip = tmp;
                }
            }
        }
        if(!StringUtils.hasLength(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(!StringUtils.hasLength(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(!StringUtils.hasLength(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * Retrieve the first cookie with the given name. Note that multiple
     * cookies can have the same name but different paths or domains.
     * @param request current servlet request
     * @param name cookie name
     * @return the first cookie with the given name, or {@code null} if none is found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request, "Request must not be null");
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * Determine the session id of the given request, if any.
     * @param request current HTTP request
     * @return the session id, or {@code null} if none
     */
    public static String getSessionId(HttpServletRequest request) {
        Assert.notNull(request, "Request must not be null");
        HttpSession session = request.getSession(false);
        return (session != null ? session.getId() : null);
    }

    /**
     * 是否是移动端请求
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 是否是移动端请求
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request) {
        String ua = getUserAgent(request);
        logger.debug("Request, User-Agent is {}", ua);
        return ua != null && ua.matches(".*(Android|iPhone|Nokia|iPod|webOS|BlackBerry).*");
    }

    /**
     * 从{@link HttpServletRequest}中取值，若为null，则返回空字符串
     * @param request
     * @param key
     * @return
     */
    public static String getNotNullParameter(HttpServletRequest request, String key) {
        return request.getParameter(key) == null || "null".equals(request.getParameter(key)) ? "" : request.getParameter(key);
    }
    
}
