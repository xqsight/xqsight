package com.xqsight.common.model.shiro;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangganggang
 * @date 2017/3/23
 */
@Data
public class BaseUserModel extends BaseModel implements Serializable {

    /**
     * 用户内码
     **/
    private Long id;

    /**
     * 登录编号
     **/
    private String loginId;

    /**
     * 密码
     **/
    private String password;

    /**
     * 加密密码的盐
     **/
    private String salt;

    /**
     * 用户名
     **/
    private String userName;

    /**
     * 登录类型
     **/
    private int loginType;

    /*** 部门编号 **/
    private String departmentCode;

    /**
     * 拥有的角色列表
     **/
    private List<Long> roleIds;

    /**
     * 是否锁定 0-未锁定 -1-锁定
     */
    private int locked;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseUserModel user = (BaseUserModel) o;

        if (id != null ? !id.equals(user.id) : user.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password='" + password + '\'' + ", salt='" + salt + '\'' + ", roleIds="
                + roleIds + ", locked=" + locked + '}';
    }

    /**
     * 判断是否锁定
     *
     * @return
     */
    public boolean isUserLocked() {
        return this.locked == -1;
    }

    /**
     * @return
     */
    public String getCredentialsSalt() {
        return salt + System.currentTimeMillis();
    }

    @Override
    public Serializable getPK() {
        return this.id;
    }
}
