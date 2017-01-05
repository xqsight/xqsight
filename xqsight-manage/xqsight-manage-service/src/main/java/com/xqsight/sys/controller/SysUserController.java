package com.xqsight.sys.controller;

import com.github.pagehelper.Page;
import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.support.LoginSupport;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.model.enums.UserFromSourceEnum;
import com.xqsight.commons.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.sso.utils.PasswordHelper;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.service.SysUserService;
import com.xqsight.upload.model.vo.SysFileVo;
import com.xqsight.upload.support.FileUploadSupport;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/login/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("save")
    @RequiresPermissions("sys:login:save")
    public Object saveLogin(HttpServletRequest request, SysLogin sysLogin) {
        SysLogin sysLogins = sysUserService.querySingleUserByLoginId(sysLogin.getLoginId());
        if (sysLogins != null)
            return MessageSupport.failureMsg("保存失败，登录名已存在");

        sysLogin.setCreateUserId(SSOUtils.getCurrentUserId().toString());
        sysLogin.setPassword("!password");
        if (WebUtils.isMobile(request)) {
            sysLogin.setFromSource(UserFromSourceEnum.MOBILE.value());
        } else {
            sysLogin.setFromSource(UserFromSourceEnum.PC.value());
        }
        LoginTypeEnum loginType = LoginSupport.judgeLoginType(sysLogin.getLoginId());
        sysLogin.setLoginType(loginType.value());
        PasswordHelper.encryptPassword(sysLogin);
        sysUserService.saveSysLogin(sysLogin);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("resetpwd")
    @RequiresPermissions("sys:login:resetpwd")
    public Object resetPwd(long id) {
        SysLogin sysLogin = sysUserService.querySysLoginById(id);
        sysLogin.setUpdateUserId(SSOUtils.getCurrentUserId().toString());
        sysLogin.setPassword("!password");
        PasswordHelper.encryptPassword(sysLogin);
        sysUserService.updateSysLoginPwd(sysLogin.getPassword(), sysLogin.getId());
        return MessageSupport.successMsg("密码重置成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:login:delete")
    public Object deleteLogin(long id) {
        sysUserService.deleteSysLogin(id);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("updpwd")
    public Object updPassword(String oldPassword, String password) {
        SysLogin sysLogin = sysUserService.querySysLoginById(SSOUtils.getCurrentUserId());
        if (sysLogin == null)
            return MessageSupport.failureMsg("当前用户不存在");

        if (!PasswordHelper.checkPassword(sysLogin, oldPassword))
            return MessageSupport.failureMsg("原始密码不正确");

        sysLogin.setPassword(password);
        PasswordHelper.encryptPassword(sysLogin);
        sysUserService.updateSysLoginPwd(sysLogin.getPassword(), sysLogin.getId());
        return MessageSupport.successMsg("密码修改成功");
    }

    @RequestMapping("query")
    public Object queryLoginByName(XqsightPage xqsightPage, SysLogin sysLogin) {
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<SysLogin> sysLogins = sysUserService.querySysLoginByLoginIdAndOrgId(sysLogin.getLoginId(), sysLogin.getOrgId());
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, sysLogins);
    }

    @RequestMapping("querybyid")
    public Object queryLoginById(long id) {
        SysLogin sysLogins = sysUserService.querySysLoginById(id);
        return MessageSupport.successDataMsg(sysLogins, "查询成功");
    }

    @RequestMapping("queryuserinfo")
    public Object queryUserInfo() {
        SysLogin sysLogin = sysUserService.querySysLoginById(SSOUtils.getCurrentUserId());
        return MessageSupport.successDataMsg(sysLogin, "查询成功");
    }

    @RequestMapping("upduserinfo")
    public Object updUserInfo(SysLogin sysLogin) {
        sysLogin.setId(SSOUtils.getCurrentUserId());
        sysUserService.updSysLogin(sysLogin);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("updimg")
    public Object updUserImg(HttpServletRequest request) {
        if (!ServletFileUpload.isMultipartContent(request))
            return MessageSupport.failureMsg("请选择上传图片");

        SysLogin sysLogin = sysUserService.querySysLoginById(SSOUtils.getCurrentUserId());
        if (sysLogin == null)
            return MessageSupport.failureMsg("当前用户不存在");

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> multipartFileMap = mRequest.getFileMap();
        String imgUrl = "";
        for (String mapKey : multipartFileMap.keySet()) {

            MultipartFile multipartFile = multipartFileMap.get(mapKey);

            try {
                SysFileVo sysFile = FileUploadSupport.uploadFile(multipartFile);
                imgUrl = sysFile.getFileUrl();
            } catch (IOException e) {
                e.printStackTrace();
            }

            sysUserService.updUserImg(imgUrl, SSOUtils.getCurrentUserId());

            return MessageSupport.successDataMsg(imgUrl, "修改成功");
        }
        return MessageSupport.failureMsg("没有找到上传文件");
    }

}
