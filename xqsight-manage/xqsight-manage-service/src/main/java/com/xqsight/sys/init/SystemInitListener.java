package com.xqsight.sys.init;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.cache.CacheKeyConstants;
import com.xqsight.data.ehcache.core.CacheTemplate;
import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import com.xqsight.sys.service.SysDictService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/9/6.
 */
public class SystemInitListener implements ServletContextListener {

    private static Logger logger = LogManager.getLogger(SystemInitListener.class);

    private  SysDictService sysDictService;

    private  CacheTemplate cacheTemplate;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //spring 上下文
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        sysDictService = appContext.getBean(SysDictService.class);
        cacheTemplate = appContext.getBean(CacheTemplate.class);

        logger.info("初始化数据字典开始。。。。");
        initDict();
        logger.info("初始化数据字典结束。。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public void initDict(){
        Map<String, Map<String,String>> dictMap = (Map<String, Map<String,String>>) cacheTemplate.get(CacheKeyConstants.SYS_DICT_MAP);
        if(dictMap == null){
            dictMap = new HashMap<>();
            List<SysDict> sysDicts = sysDictService.querySysDictByDictName("");
            for (SysDict sysDict :sysDicts ){
                List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(sysDict.getDictId());
                Map<String,String> detailMap = new HashMap<>();
                for(SysDictDetail sysDictDetail : sysDictDetails){
                    detailMap.put(sysDictDetail.getDictValue(),sysDictDetail.getDictDesp());
                }
                dictMap.put(sysDict.getDictCode(),detailMap);
            }
            cacheTemplate.put(CacheKeyConstants.SYS_DICT_MAP, dictMap);
            logger.debug("dict vale : {}", JSON.toJSONString(dictMap));
        }
    }
}
