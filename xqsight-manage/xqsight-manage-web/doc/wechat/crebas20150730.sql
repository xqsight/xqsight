/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/25 14:50:17                           */
/*==============================================================*/


drop table if exists WX_AUTO_REPLAY;

drop table if exists WX_BASE_INFO;

drop table if exists WX_CUS_TEMP;

drop table if exists WX_IMG_MSG;

drop table if exists WX_IMG_MSG_UNIT;

drop table if exists WX_MENU;

drop table if exists WX_MESSAGE;

drop table if exists WX_PROMISE;

drop table if exists WX_TEXT_MSG;

drop table if exists WX_USER_INFO;

/*==============================================================*/
/* Table: WX_AUTO_REPLAY                                        */
/*==============================================================*/
create table WX_AUTO_REPLAY
(
   WX_ID                bigint not null comment '微信ID',
   REPLAY_ID            bigint not null auto_increment comment 'ID',
   KEY_WORD             varchar(400) comment '关键字',
   MESSAGE_TYPE         int comment '消息类型
            0:自定义
            1:文本消息
            2:图文消息
            ',
   MESSAGE_ID           bigint comment '消息id',
   CUSTOM_REPLAY        varchar(2000) comment '自定义内容',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (REPLAY_ID)
);

alter table WX_AUTO_REPLAY comment '微信自动恢复管理';

/*==============================================================*/
/* Table: WX_BASE_INFO                                          */
/*==============================================================*/
create table WX_BASE_INFO
(
   WX_ID                bigint not null auto_increment comment '内码',
   WX_CODE              varchar(200) comment '公众微信号',
   WX_ORIGINAL_ID       varchar(200) comment '公众号原始ID',
   WX_TYPE              int comment '公众号类型
            0:订阅号
            1:服务好
            2:企业号
            ',
   WX_APPID             varchar(400) comment '公众帐号APPID',
   WX_APPSECRET         varchar(400) comment '公众帐号APPSECRET',
   WX_DESCRIPT          varchar(400) comment '公众帐号描述',
   EMAIL                varchar(200) comment '电子邮箱',
   ROLE_NAME            varchar(120) comment '公众微信号名称',
   WX_TOKEN             varchar(400) comment '公众号TOKEN',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (WX_ID)
);

alter table WX_BASE_INFO comment '微信信息表';

/*==============================================================*/
/* Table: WX_CUS_TEMP                                           */
/*==============================================================*/
create table WX_CUS_TEMP
(
   WX_ID                bigint not null default 0 comment '微信ID',
   TEMP_ID              bigint not null auto_increment comment 'ID',
   TEMP_TYPE            int comment '模板类型',
   TEMP_TITLE           varchar(400) comment '标题',
   TEMP_CONTENT         varchar(2000) comment '内容',
   TEMP_HREF            varchar(400) comment '访问路径',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (TEMP_ID)
);

alter table WX_CUS_TEMP comment '微信自定义模板消息';

/*==============================================================*/
/* Table: WX_IMG_MSG                                            */
/*==============================================================*/
create table WX_IMG_MSG
(
   WX_ID                bigint not null default 0 comment '微信ID',
   IMG_MSG_ID           bigint not null auto_increment comment '消息ID',
   IMG_URL              varchar(200) comment '图片url',
   IMG_MSG_HREF         varchar(200) comment '消息连接',
   IMG_MSG_TITLE        varchar(200) comment '标题',
   IMG_MSG_CONTENT      varchar(2000) comment '内容',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (IMG_MSG_ID)
);

alter table WX_IMG_MSG comment '微信图文消
WX_ID=0时,为公共消息';

/*==============================================================*/
/* Table: WX_IMG_MSG_UNIT                                       */
/*==============================================================*/
create table WX_IMG_MSG_UNIT
(
   WX_ID                bigint not null default 0 comment '微信ID',
   UNIT_ID              bigint not null comment '消息ID',
   IMG_MSG_ID           bigint not null comment '图文消息ID',
   UNIT_NAME            varchar(200) comment '图文名称',
   IMG_COUNT            int comment '消息条数',
   UNIT_DESCRIPT        varchar(400) comment '说明',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (WX_ID, UNIT_ID, IMG_MSG_ID)
);

alter table WX_IMG_MSG_UNIT comment '微信图文单元
WX_ID=0时,为公共消息';

/*==============================================================*/
/* Table: WX_MENU                                               */
/*==============================================================*/
create table WX_MENU
(
   WX_ID                bigint not null comment '微信ID',
   MENU_ID              bigint not null auto_increment comment '菜单ID',
   MENU_NAME            varchar(120) comment '菜单名称',
   MENU_TYPE            int comment '菜单类型
            0:CLICK 
            1: VIEW
            ',
   MENU_KEY             varchar(120) comment '菜单key值',
   MENU_URL             varchar(400) comment '菜单url',
   SORT                 int comment '排序',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (MENU_ID)
);

alter table WX_MENU comment ' 微信菜单表';

/*==============================================================*/
/* Table: WX_MESSAGE                                            */
/*==============================================================*/
create table WX_MESSAGE
(
   WX_ID                bigint not null comment '微信ID',
   MESSAGE_ID           bigint not null auto_increment comment '消息ID',
   SEND_USER            varchar(120) comment '发送人',
   MESSAGE_CONTENT      varchar(2000) comment '发送内容',
   MESSAGE_TYPE         int comment '消息类型',
   REPLY_CONTENT        varchar(2000) comment '回复内容',
   IS_REPLY             int comment '是否回复',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (MESSAGE_ID)
);

alter table WX_MESSAGE comment '微信消息管理';

/*==============================================================*/
/* Table: WX_PROMISE                                            */
/*==============================================================*/
create table WX_PROMISE
(
   PROMISE_ID           bigint not null auto_increment comment '预约ID',
   WX_USER_ID           bigint not null comment '用户ID',
   PROMISE_TIME         datetime comment '预约时间',
   PROMISE_ADDRESS      varchar(400) comment '预约地点',
   PROMISE_ADDR_DETAIL  varchar(400) comment '地址明细',
   STATUS               varbinary(2) comment '状态
            0:已提交
            1:维修中
            2.已完成
            ',
   FROM_SOURCE          varchar(40) not null comment '来源',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (PROMISE_ID)
);

alter table WX_PROMISE comment ' 预约信息';

/*==============================================================*/
/* Table: WX_TEXT_MSG                                           */
/*==============================================================*/
create table WX_TEXT_MSG
(
   WX_ID                bigint not null default 0 comment '微信ID',
   TEXT_MSG_ID          bigint not null auto_increment comment '消息ID',
   TEXT_MSG_NAME        varchar(200) comment '标题',
   TEXT_MSG_CONTENT     varchar(2000) comment '内容',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (TEXT_MSG_ID)
);

alter table WX_TEXT_MSG comment '微信文本消息';

/*==============================================================*/
/* Table: WX_USER_INFO                                          */
/*==============================================================*/
create table WX_USER_INFO
(
   WX_ID                bigint not null comment '微信ID',
   WX_USER_ID           bigint not null auto_increment comment '微信用户ID',
   WX_USER_CODE         varchar(120) comment '微信号',
   WX_NAME              varchar(120) comment '微信昵称
            ',
   USER_NAME            varchar(120) comment '真实名称',
   SEX                  int comment '性别
            0:保密
            1:男
            2:女
            ',
   TEL_PHONE            varchar(120) not null comment '电话',
   QQ                   varchar(120) comment 'qq',
   EMAIL                varchar(120) comment '邮箱',
   ACTIVE               int not null default 0 comment '是否有效
            0:有效
            -1:无效
            ',
   CERATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (WX_USER_ID)
);

alter table WX_USER_INFO comment ' 微信用户信息';

