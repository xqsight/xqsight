package com.xqsight.common.support;

import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.web.WebUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/6/2.
 */
public class MessageSupport {

    private final static int SUCCESS = 0;

    private final static int FAILURE = -1;

    private static final String KEY_STATUS = "status";

    private static final String KEY_CODE = "errCode";

    private static final String KEY_MESSAGE = "msg";

    private static final String KEY_DATA = "data";

    private static final String KEY_ECHO = "sEcho";
    private static final String KEY_TOTAL_PAGE = "iTotalRecords";
    private static final String KEY_PAGE_SIZE = "iTotalDisplayRecords";
    private static final String KEY_PAGE_DATA = "aaData";

    public static Object successMsg(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_STATUS, SUCCESS);
        map.put(KEY_MESSAGE, message);
        return responseBody(map);
    }

    public static Object successDataMsg(Object dataObj, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_STATUS, SUCCESS);
        map.put(KEY_DATA, dataObj);
        map.put(KEY_MESSAGE, message);
        return responseBody(map);
    }

    public static Object successDataMsg(Object objectData) {
        return responseBody(objectData);
    }

    public static Object successDataTableMsg(XqsightPage page,Object dataObj){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_ECHO,page.getsEcho());
        map.put(KEY_TOTAL_PAGE, page.getTotalCount());
        map.put(KEY_PAGE_SIZE, page.getTotalCount());
        map.put(KEY_PAGE_DATA, dataObj);
        return responseBody(map);
    }

    public static Object failureMsg(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_STATUS, FAILURE);
        map.put(KEY_MESSAGE, message);
        return responseBody(map);
    }

    public static Object errorMsg(String errCode,String message){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_STATUS, FAILURE);
        map.put(KEY_CODE, errCode);
        map.put(KEY_MESSAGE, message);
        return responseBody(map);
    }

    private static Object responseBody(Object objectData){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return WebUtils.getResponseBody(request, objectData);
    }
}
