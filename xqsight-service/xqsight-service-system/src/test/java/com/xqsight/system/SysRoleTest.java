package com.xqsight.system;

import com.xqsight.system.model.SysRole;
import com.xqsight.system.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangganggang on 17/1/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/global-config.xml","classpath:spring/webmvc-config.xml"})

public class SysRoleTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void query(){
        SysRole sysRole = sysRoleService.get(1L);
        System.out.println(sysRole.getRoleCode());
    }
}
