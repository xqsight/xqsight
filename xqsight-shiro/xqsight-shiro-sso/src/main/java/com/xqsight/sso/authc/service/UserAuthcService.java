package com.xqsight.sso.authc.service;


import com.xqsight.common.model.UserBaseModel;

import java.util.Set;

public interface UserAuthcService {
    
    /**
     * 根据登陆id查找用户
     * @param loginId
     * @return
     */
    UserBaseModel findByLoginId(String loginId);

    /**
     * 根据用户id查找其角色
     * @param id
     * @return
     */
    Set<String> findRoles(long id);

    /**
     * 根据用户id查找其权限
     * @param id
     * @return
     */
    Set<String> findPermissions(long id);
    
    
    /**
     * 
     * 用户注册
     *
     * @Title: saveUser
     * @param @param userBaseModel    设定文件
     * @return void    返回类型
     * @throws
     */
    void saveUser(UserBaseModel userBaseModel);
}
