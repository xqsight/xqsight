package com.saicfc.themis.data.neo4j.restful;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GTaurus on 2016/4/7.
 */
@ControllerAdvice(basePackages = "com.sacifc.themis")
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces="application/json")
    @ResponseBody
    Object dealWithResponseBody(Exception e, HttpServletRequest request) {
        Map<String, String> resp = new HashMap<>();
        resp.put("returnCode", "0");
        resp.put("returnDesc", e.getMessage());
        return resp;
    }
}
