package com.xqsight.ehcache.test;

import com.xqsight.Application;
import com.xqsight.data.ehcache.core.CacheTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class EhcacheTest {

    @Autowired
    private CacheTemplate cacheTemplate;

    @Test
    public void queryTest() throws Exception{
        cacheTemplate.put("aa","bbb");
        System.out.println(cacheTemplate.get("aa"));
    }

}
