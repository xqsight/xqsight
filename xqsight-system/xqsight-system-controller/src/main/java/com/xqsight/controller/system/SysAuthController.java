package com.xqsight.controller.system;

import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.support.TreeSupport;
import com.xqsight.security.annontation.CurrentUserId;
import com.xqsight.system.enums.MenuTypeEnum;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangganggang
 * @date 2016年1月8日 上午9:29:25
 */
@RestController
@RequestMapping("/sys/auth/")
public class SysAuthController {

    @Autowired
    private SysAuthService sysAuthService;

    @RequestMapping(value = "roleuser", method = RequestMethod.POST)
    public Object saveRoleUser(long roleId, @RequestParam("id") Long[] ids) {
        sysAuthService.saveUserRole(roleId, ids);
        return new BaseResult();
    }

    @RequestMapping(value = "rolemenu", method = RequestMethod.POST)
    public Object saveMenuRole(long roleId, @RequestParam("menuId") Long[] menuIds) {
        sysAuthService.saveMenuRole(roleId, menuIds);
        return new BaseResult();
    }

    @RequestMapping(value = "authuser", method = RequestMethod.GET)
    public Object queryAuthUser(long roleId) {
        List<SysLogin> sysLogins = sysAuthService.querAuthUserByRole(roleId);
        return new BaseResult(sysLogins);
    }

    @RequestMapping(value = "authmenu", method = RequestMethod.GET)
    public Object queryAuthMenu(long roleId) {
        List<SysMenu> sysMenus = sysAuthService.querAuthMenuByRole(roleId);
        return new BaseResult(sysMenus);
    }


    @RequestMapping(value = "usermenu", method = RequestMethod.GET)
    public Object queryUserMenu(@CurrentUserId long currentUserId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("type", "" + MenuTypeEnum.MENU.getValuel()).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").end();

        List<SysMenu> sysMenus = sysAuthService.queryMenuByUser(currentUserId, propertyFilters, sorts);
        sysMenus = new TreeSupport<SysMenu>().generateTree(sysMenus);
        return new BaseResult(sysMenus);
    }

}
