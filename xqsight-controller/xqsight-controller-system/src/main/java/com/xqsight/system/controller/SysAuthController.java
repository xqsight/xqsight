package com.xqsight.system.controller;

import com.xqsight.common.core.annotation.AttributeFilter;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.TreeSupport;
import com.xqsight.sso.shiro.annotation.CurrentUserId;
import com.xqsight.system.enums.MenuTypeEnum;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年1月8日 上午9:29:25
 */
@RestController
@RequestMapping("/sys/auth/")
public class SysAuthController {

    @Autowired
    private SysAuthService sysAuthService;

    @RequestMapping("saveroleuser")
    public Object saveRoleUser(long roleId, @RequestParam("id") Long[] ids) {
        sysAuthService.saveUserRole(roleId, ids);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("saverolemenu")
    public Object saveMenuRole(long roleId, @RequestParam("menuId") Long[] menuIds) {
        sysAuthService.saveMenuRole(roleId, menuIds);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("queryauthuser")
    public Object queryAuthUser(long roleId) {
        List<SysLogin> sysLogins = sysAuthService.querAuthUserByRole(roleId);
        return MessageSupport.successDataMsg(sysLogins, "查询成功");
    }

    @RequestMapping("queryauthmenu")
    public Object queryAuthMenu(long roleId) {
        List<SysMenu> sysMenus = sysAuthService.querAuthMenuByRole(roleId);
        return MessageSupport.successDataMsg(sysMenus, "查询成功");
    }


    @RequestMapping("queryusermenu")
    @AttributeFilter(type = SysMenu.class, filter = "createTime,updateTime")
    public Object queryUserMenu(@CurrentUserId long currentUserId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("type", "" + MenuTypeEnum.MENU.getValuel()).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").end();

        List<SysMenu> sysMenus = sysAuthService.queryMenuByUser(currentUserId, propertyFilters, sorts);
        List<SysMenu> sysMenuList = new TreeSupport<SysMenu>().generateTree(sysMenus);
        return MessageSupport.successDataMsg(sysMenuList, "查询成功");
    }

}
