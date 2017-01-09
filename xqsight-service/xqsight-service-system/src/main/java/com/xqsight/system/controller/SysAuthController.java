package com.xqsight.system.controller;

import com.xqsight.commons.support.MessageSupport;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysAuthService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年1月8日 上午9:29:25
 */
@RestController
@RequestMapping("/sys/auth/")
public class SysAuthController {

    @Resource(name = "userAuthcService")
    private SysAuthService sysAuthService;

    @RequestMapping("saveroleuser")
    @RequiresPermissions("sys:auth:saveuserrole")
    public Object saveRoleUser(long roleId, @RequestParam("id") Long[] ids) {
        sysAuthService.saveUserRole(roleId, ids);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("saverolemenu")
    @RequiresPermissions("sys:auth:savemenurole")
    public Object saveMenuRole(long roleId, @RequestParam("menuId") Long[] menuIds) {
        sysAuthService.saveMenuRole(roleId, menuIds);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("queryauthuser")
    @RequiresPermissions("sys:auth:queryauthuser")
    public Object querAuthUser(long roleId) {
        List<SysLogin> sysLogins = sysAuthService.querAuthUserByRole(roleId);
        return MessageSupport.successDataMsg(sysLogins, "查询成功");
    }

    @RequestMapping("queryauthmenu")
    @RequiresPermissions("sys:auth:queryauthuser")
    public Object querAuthMenu(long roleId) {
        List<SysMenu> sysMenus = sysAuthService.querAuthMenuByRole(roleId);
        return MessageSupport.successDataMsg(sysMenus, "查询成功");
    }

}
