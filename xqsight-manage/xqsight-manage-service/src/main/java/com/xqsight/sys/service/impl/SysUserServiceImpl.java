package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.mysqlmapper.SysUserMapper;
import com.xqsight.sys.service.SysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2015年12月30日 下午2:11:03
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private static Logger logger = LogManager.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysLoginMapper;

    /**
     * <p>Title: saveSysLogin</p>
     * <p>Description: </p>
     *
     * @param sysLogin
     */
    @Override
    public void saveSysLogin(SysLogin sysLogin) {
        logger.debug("出入参数:sysLogin={}", JSON.toJSONString(sysLogin));
        sysLoginMapper.saveSysLogin(sysLogin);
    }


    /**
     * <p>Title: updateSysLogin</p>
     * <p>Description: </p>
     *
     * @param
     */
    @Override
    public void updateSysLoginPwd(String password, long id) {
        logger.debug("出入参数:password={},id={}", password, id);
        sysLoginMapper.updateSysLoginPwd(password, id);
    }

    /**
     * <p>Title: updUserName</p>
     * <p>Description: </p>
     *
     * @param sysLogin
     */
    @Override
    public void updSysLogin(SysLogin sysLogin) {
        logger.debug("出入参数:sysLogin={}", JSON.toJSONString(sysLogin));
        sysLoginMapper.updSysLogin(sysLogin);
    }


    /**
     * <p>Title: deleteSysLogin</p>
     * <p>Description: </p>
     *
     * @param id
     */
    @Override
    public void deleteSysLogin(long id) {
        logger.debug("出入参数:id={}", id);
        sysLoginMapper.deleteSysLogin(id);
        sysLoginMapper.deleteUserRole(id);
    }

    /**
     * <p>Title: querySysLogin</p>
     * <p>Description: </p>
     *
     * @return
     */
    @Override
    public List<SysLogin> querySysLoginByLoginId(String loginId) {
        logger.debug("出入参数:loginId={}", loginId);
        return sysLoginMapper.querySysLoginByLoginId(loginId);
    }

    @Override
    public List<SysLogin> querySysLoginByLoginIdAndOrgId(String loginId, long orgId) {
        logger.debug("出入参数:loginId={},orgId={}", loginId,orgId);
        return sysLoginMapper.querySingleUserByLoginIdAndOrgId(loginId,orgId);
    }

    /**
     * <p>Title: querySysLoginById</p>
     * <p>Description: </p>
     *
     * @param id
     * @return
     */
    @Override
    public SysLogin querySysLoginById(long id) {
        logger.debug("出入参数:id={}", id);
        return sysLoginMapper.querySysLoginById(id);
    }

    @Override
    public void updUserImg(String imgUrl, long id) {
        logger.debug("出入参数:imgUrl={},id={}", imgUrl, id);
        sysLoginMapper.updateUserImg(imgUrl, id);
    }

    @Override
    public SysLogin querySingleUserByLoginId(String loginId) {
        logger.debug("出入参数:loginId={}", loginId);
        return sysLoginMapper.querySingleUserByLoginId(loginId);
    }
}
