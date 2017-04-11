package com.xqsight.common.core.support;

import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.utils.DateFormatUtils;
import com.xqsight.common.utils.ReflectionUtils;

import java.io.Serializable;
import java.util.Date;



/**
 * model更新的辅助类，用于修改实体对象里面各个字段的信息
 *
 */
public class ModelUpdateInfoUtils {

    /** 创建和修改信息 **/
    public static final String CREATE_TIME =        "createTime";
    public static final String CREATE_OPER_ID =     "createOperId";
    public static final String CREATE_OPER_NAME =   "createOperName";
    public static final String LAST_MOD_TIME =      "updateTime";
    public static final String LAST_MOD_OPER_ID =   "updateOperId";
    public static final String LAST_MOD_OPER_NAME = "updateOperName";
    public static final String ACTIVE =             "active";
    
    private ModelUpdateInfoUtils(){ }

    /**
     * 更新实体对象的最后修改时间
     * @param object 实体对象并且实现了Serializable接口的类
     */
    public static void updateModelUpdateTime(Object object){
        if(object == null){
            return;
        }

        if(ReflectionUtils.getDeclaredField(object,LAST_MOD_TIME) != null){
            ReflectionUtils.invokeSetterMethod(object, LAST_MOD_TIME, new Date());
        }
    }

    /**
     * 得到实体对象的最后修改时间
     * @param object 实体对象并且实现了Serializable接口的类
     */
    public static Date getModelUpdateTime(Object object){
        if(object == null){
            return null;
        }
        
        try{
            if(ReflectionUtils.getDeclaredField(object,LAST_MOD_TIME) != null){
                Object dateObj = ReflectionUtils.invokeGetterMethod(object, LAST_MOD_TIME);
                if(dateObj instanceof Date){
                    return (Date)dateObj;
                }
            }
        }
        catch(Exception e){
            
        }
        return null;
    }

    /**
     * 修改实体对象里面的创建时间，操作员ID，操作员姓名
     * @param object 实体类并且实现了Serializable接口的类
     * @param user 用户实体类
     */
    public static void updateModelInfo(Serializable object, BaseUserModel user){
    	
        if(object == null || user == null){
            return;
        }
        
        if(ReflectionUtils.getDeclaredField(object,LAST_MOD_OPER_ID) != null){
            if(user.getId() != null && user.getId() > 0){
                ReflectionUtils.invokeSetterMethod(object, LAST_MOD_OPER_ID, user.getId());
            }
        }
        if(ReflectionUtils.getDeclaredField(object,LAST_MOD_OPER_NAME) != null){
            ReflectionUtils.invokeSetterMethod(object, LAST_MOD_OPER_NAME, user.getLoginId());
        }

        String ID = user.getId().getClass().getTypeName();

        // 如果实体类存在主键 id，先设定实体对象主键值为null
        if(ReflectionUtils.getDeclaredField(object,ID) != null){
            Object idValue = ReflectionUtils.getFieldValue(object, ID);
            if(idValue == null || "".equals(idValue)){
                // 一定要设置为空，要不然无法插入数据库
                ReflectionUtils.setFieldValue(object, ID, null);
                // 修改实体对象里面的创建时间，操作员ID，操作员姓名
                updateModelCreateInfo(object,user);
                updateModelUpdateTime(object);
            }
        }
        /*else if(ReflectionUtils.getDeclaredField(object,CODE) != null ||
                ReflectionUtils.getDeclaredField(object,APP_CODE) != null){
            Object createTime = ReflectionUtils.getFieldValue(object, CREATE_TIME);
            if(createTime == null){
            	// 修改实体对象里面的创建时间，操作员ID，操作员姓名
                updateModelCreateInfo(object,user);
                updateModelUpdateTime(object);
            }
        }*/
        // 修改实体对象里面的创建时间，操作员ID，操作员姓名
    }

    /**
     * 修改实体对象里面的创建时间，操作员ID，操作员姓名
     * @param object 实体类并且实现了Serializable接口的类
     * @param user 用户实体类
     */
     protected static void updateModelCreateInfo(Serializable object, BaseUserModel user){
         if(ReflectionUtils.getDeclaredField(object,CREATE_TIME) != null){
             ReflectionUtils.invokeSetterMethod(object, CREATE_TIME, DateFormatUtils.yyyy_MM_dd_SPACE_HH_mm_ss.format(new Date()));
         }
         if(ReflectionUtils.getDeclaredField(object,CREATE_OPER_ID) != null){
             if(user.getId() != null && user.getId() > 0){
                 ReflectionUtils.invokeSetterMethod(object, CREATE_OPER_ID, user.getId());
             }
         }
         if(ReflectionUtils.getDeclaredField(object,CREATE_OPER_NAME) != null){
             ReflectionUtils.invokeSetterMethod(object, CREATE_OPER_NAME, user.getLoginId());
         }
     }

     /**
      * 修改实体对象里面的创建时间，操作员ID，操作员姓名和最后修改时间
      * @param object 实体类并且实现了Serializable接口的类
      * @param transCode 操作员姓名
      */
     public static void createModelInfoBySys(Serializable object, String transCode){
         if(ReflectionUtils.getDeclaredField(object,CREATE_TIME) != null){
             ReflectionUtils.invokeSetterMethod(object, CREATE_TIME, DateFormatUtils.yyyy_MM_dd_SPACE_HH_mm_ss.format(new Date()));
         }
         if(ReflectionUtils.getDeclaredField(object,CREATE_OPER_ID) != null){
             ReflectionUtils.invokeSetterMethod(object, CREATE_OPER_ID, -1);
         }
         if(ReflectionUtils.getDeclaredField(object,CREATE_OPER_NAME) != null){
             ReflectionUtils.invokeSetterMethod(object, CREATE_OPER_NAME, transCode);
         }
         if(ReflectionUtils.getDeclaredField(object,LAST_MOD_TIME) != null){
             ReflectionUtils.invokeSetterMethod(object, LAST_MOD_TIME, DateFormatUtils.yyyy_MM_dd_SPACE_HH_mm_ss.format(new Date()));
         }
         updateModelInfoBySys(object, transCode);
     }

     /**
      * 修改实体对象里面的操作员ID、操作员姓名，表明这个订单现在是系统在操作
      * @param object 实体类并且实现了Serializable接口的类
      * @param transCode 操作员姓名
      */
     public static void updateModelInfoBySys(Serializable object, String transCode){
         if(ReflectionUtils.getDeclaredField(object,LAST_MOD_OPER_ID) != null){
             ReflectionUtils.invokeSetterMethod(object, LAST_MOD_OPER_ID, -1);
         }
         if(ReflectionUtils.getDeclaredField(object,LAST_MOD_OPER_NAME) != null){
             ReflectionUtils.invokeSetterMethod(object, LAST_MOD_OPER_NAME, transCode);
         }
     }

     /**
      * 修改实体对象里面的状态为Invalidate，表明这个订单现在无效
      * @param object 实体类并且实现了Serializable接口的类
      */
     public static void setModelInvalidActive(Serializable object){
         if(ReflectionUtils.getDeclaredField(object,ACTIVE) != null){
             ReflectionUtils.invokeSetterMethod(object, ACTIVE, Constants.INACTIVE);
         }
     }
}
