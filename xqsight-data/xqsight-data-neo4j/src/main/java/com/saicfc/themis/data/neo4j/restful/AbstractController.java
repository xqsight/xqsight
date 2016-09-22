package com.saicfc.themis.data.neo4j.restful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by GTaurus on 2016/4/6.
 */
public abstract class AbstractController {

    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    public Object foo(HttpServletRequest request) {
        return request.getRequestURI() + ":bar";
    }
}
