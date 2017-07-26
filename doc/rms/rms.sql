drop table if exists user_token;
drop table if exists repair;
drop table if exists questions;
drop table if exists message;
drop table if exists appointment;

/*==============================================================*/
/* Table: appointment                                                */
/*==============================================================*/
create table appointment
(
   id                   bigint not null auto_increment,
   user_name            varchar(64) not null comment '用户id',
   user_sex             tinyint(4) not null default 0 comment  '0:男，1:女 2:保密',
   telphone             varchar(20) not null comment '电话号码',
   associate_type       tinyint(4) not null default 0 comment  '0:房屋，1: 房间',
   associate_id         varchar(64) comment '关联Id',
   app_time             datetime comment '预约时间',
   status               tinyint(4) not null default 0 comment '0:申请预约1:管家确认2:预约成功3:取消预约4:预约失败',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (id)
);
alter table appointment comment '预约看房';

/*==============================================================*/
/* Table: user_token                                                */
/*==============================================================*/
create table user_token
(
   user_Id              bigint not null comment '用户id',
   token                varchar(100) not null comment 'token',
   expire_time          datetime not null comment '过期时间',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (user_Id)
);
alter table user_token comment '用户token';

/*==============================================================*/
/* Table: message                                                */
/*==============================================================*/
create table message
(
   id                   bigint not null auto_increment,
   title    	          varchar(200) comment '消息标题',
   content              varchar(500) comment '消息内容',
   type	                VARCHAR(10) comment  '消息类型',
   sender	              VARCHAR(30) comment  '发送人',
   receiver	            VARCHAR(30)  comment  '接收人',
   status	              VARCHAR(10) comment  '状态',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (id)
);
alter table message comment '消息';


/*==============================================================*/
/* Table: questions                                                */
/*==============================================================*/
create table questions
(
   id                   bigint not null auto_increment,
   question    			    varchar(2000) comment '问题',
   answer           	  varchar(2000) comment '回答',
   type					        VARCHAR(1) COMMENT  '类型',
   sort					        int  COMMENT  '排序',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (id)
);
alter table questions comment '问答';


/*==============================================================*/
/* Table: repair                                                */
/*==============================================================*/
create table repair
(
   id                   bigint not null auto_increment comment 'id',
   associate_type       tinyint(4) not null default 0 COMMENT  '0:房屋，1: 房间',
   associate_id         varchar(64) comment '关联Id',
   user_id    	        varchar(64) comment '报修人ID',
   user_name    	      varchar(20) comment '报修人',
   user_mobile          varchar(20) comment '报修电话',
   repair_mobile	      VARCHAR(20) comment '报修联系电话',
	 expect_repair_time	  VARCHAR(50) comment '期望维修时间',
   contract_id	        VARCHAR(64) COMMENT '合同号',
   description	        VARCHAR(500) COMMENT  '报修描述',
   keeper	              VARCHAR(20) COMMENT  '管家',
   keeper_mobile	      VARCHAR(20) COMMENT  '管家电话',
   status	              tinyint(4) not null default 0 COMMENT  '状态',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (id)
);
alter table repair comment '保修';


