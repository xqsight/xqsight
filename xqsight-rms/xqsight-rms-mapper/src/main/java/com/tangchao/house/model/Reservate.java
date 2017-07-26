package com.tangchao.house.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>预定房屋实体类</p>
 * @author wangganggang
 * @date 2017年07月26日 下午10:46
 */
@Data
public class Reservate  extends BaseModel{
    /** 主键 */
    private Long id;

    /** server_user_id - 服务管家id */
    private String serverUserId;
    /** user_name - 用户id */
    private Long userId;
    /** user_name - 用户名称 */
    private String userName;
    /** telphone - 电话号码 */
    private String telphone;
    /** associate_type - 0:房屋，1: 房间 */
    private Byte associateType;
    /** associate_id - 关联Id */
    private String associateId;
    /** sign_time - 签约时间 */
    private LocalDateTime signTime;
    /** status - 0:申请预定1:管家确认2:支付定金3:预约成功4:取消预定 */
    private Byte status;
    /** pay_status - 0:为支付1:已支付 */
    private Byte payStatus;

    @Override
    public Serializable getPK() {
        return this.id;
    }
}
