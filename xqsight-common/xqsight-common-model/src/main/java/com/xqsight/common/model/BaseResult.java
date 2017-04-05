package com.xqsight.common.model;

import com.xqsight.common.model.constants.Constants;
import lombok.*;

/**
 * @author wangganggang
 * @date 2017/3/23
 */
@Data
@AllArgsConstructor
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

    public BaseResult(Object data){
        this.code = Constants.SUCCESS;
        this.message = "success";
        this.data = data;
    }

}
