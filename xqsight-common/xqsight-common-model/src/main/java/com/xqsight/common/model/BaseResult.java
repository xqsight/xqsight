package com.xqsight.common.model;

import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.shiro.BaseUserModel;
import lombok.*;

/**
 * @author wangganggang
 * @date 2017/3/23
 */
@Data
public class BaseResult {

    /** 状态码：1成功，其他为失败 **/
    protected int code;

    /** 成功为success，其他为失败原因 **/
    protected String message;

    /** 数据结果集 **/
    protected Object data;

    public BaseResult(){
        this.code = Constants.SUCCESS;
        this.message = "success";
    }

    public BaseResult(int code,String message){
        this.code = code;
        this.message = message;
    }

    public BaseResult(Object data){
        this.code = Constants.SUCCESS;
        this.message = "success";
        this.data = data;
    }

    public static BaseResult success(){
        return new BaseResult();
    }

    public static BaseResult failure(){
        return new BaseResult().code(Constants.FAILURE);
    }

    public BaseResult message(String message){
        this.message = message;
        return this;
    }

    public BaseResult code(int code){
        this.code = code;
        return this;
    }

    public BaseResult data(Object data){
        this.data = data;
        return this;
    }
}
