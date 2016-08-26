package com.xqsight.sys.controller;

import com.xqsight.common.support.MessageSupport;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/menu/")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("save")
    @RequiresPermissions("sys:menu:save")
    public Object saveMenu(SysMenu sysMenu) {
        sysMenu.setCreateOprId(SSOUtils.getCurrentUserId().toString());
        sysMenuService.saveSysMenu(sysMenu);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("sys:menu:update")
    public Object updateMenu(SysMenu sysMenu) {
        sysMenu.setUpdOprId(SSOUtils.getCurrentUserId().toString());
        sysMenuService.updateSysMenu(sysMenu);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:menu:delete")
    public Object deleteMenu(long menuId) {
        sysMenuService.deleteSysMenu(menuId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @RequiresPermissions("sys:menu:query")
    public Object querySysMenuByMenuNameAndParentId(String menuName,long parentId) {
        List<SysMenu> sysMenus = sysMenuService.querySysMenuByMenuNameAndParentId(menuName,parentId);
        return MessageSupport.successDataMsg(sysMenus, "查询成功");
    }

    @RequestMapping("querymenubyrole")
    @RequiresPermissions("sys:menu:querymenubyrole")
    public Object querySyeMenuByRoleId(long roleId) {
        List<SysMenu> sysMenus = sysMenuService.querySyeMenuByRoleId(roleId);
        return MessageSupport.successDataMsg(sysMenus,"查询成功");
    }

    @RequestMapping("querymenubyuser")
    @RequiresPermissions("sys:menu:querymenubyuser")
    public Object querySysMenuByUser() {
        List<SysMenu> sysMenus = sysMenuService.querySysMenuByUser(SSOUtils.getCurrentUserId());
        return MessageSupport.successDataMsg(sysMenus,"查询成功");
    }

    @RequestMapping("querybyid")
    @RequiresPermissions("sys:menu:querybyid")
    public Object queryMenuById(long menuId) {
        SysMenu sysMenu = sysMenuService.querySysMenuByMenuId(menuId);
        return MessageSupport.successDataMsg(sysMenu, "查询成功");
    }

    @RequestMapping("querytree")
    @RequiresPermissions("sys:menu:querytree")
    public Object queryMenuCovertTree() {
        SysMenu sysMenu = sysMenuService.querySysMenuForTree();
        return MessageSupport.successDataMsg(sysMenu, "查询成功");
    }

    @RequestMapping("queryalltree")
    @RequiresPermissions("sys:menu:queryalltree")
    public Object queryMenuAllConvertTree() {
        SysMenu sysMenu = sysMenuService.queryMenuAllConvertTree();
        return MessageSupport.successDataMsg(sysMenu, "查询成功");
    }
}
