package com.xqsight.sys.controller;

import com.github.pagehelper.Page;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.support.LoginSupport;
import com.xqsight.common.enums.UserFromSourceEnum;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.utils.DateFormatUtils;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.sso.authc.service.PasswordHelper;
import com.xqsight.sso.shiro.constants.WebConstants;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.service.SysUserService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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
    public Object saveLogin(SysLogin sysLogin) {
        SysLogin sysLogins = sysUserService.querySingleUserByLoginId(sysLogin.getLoginId());
        if (sysLogins != null)
            return MessageSupport.failureMsg("保存失败，登录名已存在");

        sysLogin.setCreateOprId(SSOUtils.getCurrentUserId().toString());
        sysLogin.setPassword("!password");
        sysLogin.setFromSource(UserFromSourceEnum.MOBILE.value());
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
        sysLogin.setUpdOprId(SSOUtils.getCurrentUserId().toString());
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

    @RequestMapping("query")
    public Object queryLoginByName(XqsightPage xqsightPage, SysLogin sysLogin) {
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<SysLogin> sysLogins = sysUserService.querySysLoginByLoginId(sysLogin.getLoginId());
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, sysLogins);
    }

    @RequestMapping("querybyid")
    public Object queryLoginById(long id) {
        SysLogin sysLogins = sysUserService.querySysLoginById(id);
        return MessageSupport.successDataMsg(sysLogins, "查询成功");
    }

    @RequestMapping("queryuserinfo")
    public Object queryUserInfo(HttpServletRequest request, @RequestParam(required = false) Long id) {
        if (!WebUtils.isMobile(request))
            id = SSOUtils.getCurrentUserId();

        SysLogin sysLogin = sysUserService.querySysLoginById(id);
        return MessageSupport.successDataMsg(sysLogin, "查询成功");
    }

    @RequestMapping("upduserinfo")
    public Object updUserInfo(HttpServletRequest request, SysLogin sysLogin) {
        if (!WebUtils.isMobile(request))
            sysLogin.setId(SSOUtils.getCurrentUserId());

        sysUserService.updSysLogin(sysLogin);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("updpwd")
    public Object updPassword(Long id, String password) {
        SysLogin sysLogin = sysUserService.querySysLoginById(id);
        if (sysLogin == null)
            return MessageSupport.failureMsg("当前用户不存在");

        sysLogin.setPassword(password);
        PasswordHelper.encryptPassword(sysLogin);
        sysUserService.updateSysLoginPwd(sysLogin.getPassword(), id);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("updimg")
    public Object updUserImg(HttpServletRequest request, @RequestParam(required = false) Long id) {
        if (!WebUtils.isMobile(request))
            id = SSOUtils.getCurrentUserId();

        if (!ServletFileUpload.isMultipartContent(request))
            return MessageSupport.failureMsg("请选择上传图片");

        SysLogin sysLogin = sysUserService.querySysLoginById(id);
        if (sysLogin == null)
            return MessageSupport.failureMsg("当前用户不存在");

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> multipartFileMap = mRequest.getFileMap();
        String imgUrl = "";
        for (String mapKey : multipartFileMap.keySet()) {
            MultipartFile multipartFile = multipartFileMap.get(mapKey);
            String fileName = multipartFile.getOriginalFilename();
            String ext = FilenameUtils.getExtension(fileName);
            String fileSize = String.valueOf(multipartFile.getSize());
            String fileBaseName = FilenameUtils.getBaseName(fileName);

            /**  文件保存路径 upload + 文件类型 + 日期 **/
            StringBuffer filePath = new StringBuffer("/upload").append(File.separator).append("image").append(File.separator).append(DateFormatUtils.yyyyMMdd.format(new Date()));
            /**  上传文件的名称 ：原文件名 + 大小 + 随机数**/
            StringBuffer newFileName = new StringBuffer(fileBaseName).append("-").append(fileSize).append("-").append(RandomUtil.randomString(6));
            /**  上传文件的名称 ：原文件名 + 大小 + 随机数 + 扩展名  **/
            String newFullFileName = newFileName.append(".").append(ext).toString();
            /** 当前项目的根目录 **/
            String projectPath = request.getSession().getServletContext().getRealPath(File.separator);
            /** 本地上传文件处理 **/
            String localFilePath = projectPath + File.separator + filePath.append(File.separator).append(newFullFileName).toString();

            File newFile = new File(localFilePath);
            try {
                FileUtils.touch(newFile);
                //传送文件
                multipartFile.transferTo(newFile);
                imgUrl = request.getContextPath() + filePath.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return MessageSupport.failureMsg("上传失败");
            }
        }
        sysUserService.updUserImg(imgUrl, id);

        return MessageSupport.successDataMsg(imgUrl, "修改成功");
    }

    @RequestMapping("register")
    public Object registerForMobile(HttpServletRequest request) {
        String loginId = request.getParameter(WebConstants.LOGIN_ID);
        String password = request.getParameter(WebConstants.PASSWORD);
        SysLogin sysLogin = sysUserService.querySingleUserByLoginId(loginId);
        if (sysLogin != null)
            return MessageSupport.failureMsg("当前用户已经存在");

        sysLogin = new SysLogin();
        sysLogin.setLoginId(loginId);
        sysLogin.setPassword(password);
        sysLogin.setFromSource(UserFromSourceEnum.MOBILE.value());
        LoginTypeEnum loginType = LoginSupport.judgeLoginType(sysLogin.getLoginId());
        sysLogin.setLoginType(loginType.value());
        PasswordHelper.encryptPassword(sysLogin);
        sysUserService.saveSysLogin(sysLogin);
        return MessageSupport.successMsg("注册成功");
    }

    @RequestMapping("login")
    public Object loginForMobile(HttpServletRequest request) {
        String loginId = request.getParameter(WebConstants.LOGIN_ID);
        String password = request.getParameter(WebConstants.PASSWORD);
        SysLogin SysLogin = sysUserService.querySingleUserByLoginId(loginId);
        if (SysLogin == null) {
            return MessageSupport.failureMsg("当前用户不存在！");
        } else {
            if (PasswordHelper.checkPassword(SysLogin, password)) {
                return MessageSupport.successDataMsg(SysLogin, "登录成功");
            } else {
                return MessageSupport.failureMsg("用户名或密码不对");
            }
        }
    }

    @RequestMapping("findpasswd")
    public Object findPasswd(String login_id, String pass_wd) {
        SysLogin sysLogin = sysUserService.querySingleUserByLoginId(login_id);
        if (sysLogin == null)
            return MessageSupport.failureMsg("当前用户不存在");

        sysLogin.setPassword(pass_wd);
        PasswordHelper.encryptPassword(sysLogin);
        sysUserService.updateSysLoginPwd(sysLogin.getPassword(), sysLogin.getId());
        return MessageSupport.successMsg("密码修改成功");
    }
}
