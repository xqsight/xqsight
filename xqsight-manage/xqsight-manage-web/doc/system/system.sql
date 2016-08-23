/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/6/7 13:35:59                            */
/*==============================================================*/


drop table if exists SYS_DICT;

drop table if exists SYS_DICT_DETAIL;

drop table if exists SYS_FILE;

drop table if exists SYS_LOGIN;

drop table if exists SYS_MENU;

drop table if exists SYS_MENU_ROLE;

drop table if exists SYS_QUICK_KEY;

drop table if exists SYS_ROLE;

drop table if exists SYS_USER;

drop table if exists SYS_USER_ROLE;

/*==============================================================*/
/* Table: SYS_DICT                                              */
/*==============================================================*/
create table SYS_DICT
(
   DICT_ID              bigint not null auto_increment comment '字典内码',
   DICT_CODE            varchar(120) comment '字典编号',
   DICT_NAME            varchar(120) comment '字典名称',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (DICT_ID)
);

alter table SYS_DICT comment '系统字典表';

/*==============================================================*/
/* Table: SYS_DICT_DETAIL                                       */
/*==============================================================*/
create table SYS_DICT_DETAIL
(
   DICT_DETAIL_ID       bigint not null auto_increment comment '明细编号',
   DICT_ID              bigint not null comment '字典编号',
   DICT_VALUE           VARCHAR(40) not null comment '字典值',
   DICT_DESP            VARCHAR(120) not null comment '字典描述',
   EDITABLE             int default 0 comment '是否可编辑0:有效-1:无效',
   DICT_LANG            varchar(120) comment '语言',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (DICT_DETAIL_ID)
);

alter table SYS_DICT_DETAIL comment '系统字典明细表';

/*==============================================================*/
/* Table: SYS_FILE                                              */
/*==============================================================*/
create table SYS_FILE
(
   FILE_ID              bigint not null auto_increment comment '主键',
   FILE_NAME            varchar(200) comment '文件名称',
   FILE_URL             varchar(500) comment '附件URL',
   FILE_DOMAIN          varchar(500) comment '文件域',
   FILE_EXT             varchar(10) default '0' comment '扩展名',
   FILE_SIZE            varchar(10) default '0' comment '大小',
   ATTACHMENT_TYPE      char(2) default '0' comment '附件类型 01:普通图 02:缩略图 03:LOGO',
   FILE_KIND            varchar(10) default '0' comment '附件种类 Image Vedio Doc Excel Ppt',
   ACTIVE               int not null default 0 comment '是否有效  0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (FILE_ID)
);

alter table SYS_FILE comment ' 文件表';

/*==============================================================*/
/* Table: SYS_LOGIN                                             */
/*==============================================================*/
create table SYS_LOGIN
(
   ID                   bigint not null auto_increment comment '登陆内码',
   ORG_ID               bigint  comment '组织机构',
   LOGIN_ID             varchar(120) comment '登陆名称',
   USER_NAME            varchar(120) comment '昵称',
   REAL_NAME            varchar(120) comment '真实姓名',
   PASSWORD             varchar(120) comment '登陆密码',
   LOGIN_TYPE           int comment '登陆类型 1:编号 2:邮箱 3:电话',
   SEX                  int comment '性别 0:未知 1:男 2:女',
   USER_BORN            datetime comment '生日',
   AGE                  int comment '年龄',
   FROM_SOURCE          varchar(20) comment '来源 WECHAT  PC  MOBILE',
   IMG_URL              varchar(120) comment '图片地址',
   SALT                 varchar(120) comment '随机数',
   LOCKED               int comment '是否锁定 0-未锁定 -1-锁定 ',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (ID)
);

alter table SYS_LOGIN comment '用户登陆表';

/*==============================================================*/
/* Table: SYS_MENU                                              */
/*==============================================================*/
create table SYS_MENU
(
   MENU_ID              bigint not null auto_increment comment '菜单内码',
   PARENT_ID            bigint not null default 0 comment '父级内码',
   MENU_NAME            varchar(120) not null comment '菜单名称',
   URL                  varchar(200) comment '菜单访问URL',
   ICON                 varchar(120) comment '图标',
   TYPE                 INT default 0 comment '类型 0：菜单1：功能',
   PERMISSION           varchar(200) comment '允许字符串',
   SORT                 INT default 0 comment '排序',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (MENU_ID)
);

alter table SYS_MENU comment '菜单信息表';

/*==============================================================*/
/* Table: SYS_ORG                                              */
/*==============================================================*/
create table SYS_ORG
(
   ORG_ID               bigint not null auto_increment comment '组织内码',
   PARENT_ID            bigint not null default 0 comment '父级ID',
   ORG_NAME             varchar(120) not null comment '组织名称',
   ORG_TYPE             varchar(40) comment '组织类型',
   ORG_CODE             varchar(120) comment '组织编号',
   CUSTOM_CODE          varchar(120) comment '自定义编号',
   SORT                 INT default 0 comment '排序',
   ICON                 varchar(40) comment '图标',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (ORG_ID)
);

alter table SYS_ORG comment '组织机构表';

/*==============================================================*/
/* Table: SYS_MENU_ROLE                                         */
/*==============================================================*/
create table SYS_MENU_ROLE
(
   MENU_ID              bigint not null comment '菜单内码',
   ROLE_ID              bigint not null comment '角色内码',
   primary key (MENU_ID, ROLE_ID)
);

alter table SYS_MENU_ROLE comment '菜单角色表';

/*==============================================================*/
/* Table: SYS_QUICK_KEY                                         */
/*==============================================================*/
create table SYS_QUICK_KEY
(
   Id                   bigint not null comment '登陆内码',
   FUN_ORDER            int not null comment '功能序号',
   KEY_ICON             varchar(120) comment '图标',
   KEY_TITLE            varchar(120) comment '标题',
   KEY_VALUE            varchar(120) comment '连接值',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (Id, FUN_ORDER)
);

alter table SYS_QUICK_KEY comment '快捷键表';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
   ROLE_ID              bigint not null auto_increment comment '角色内码',
   ROLE_NAME            varchar(120) comment '角色名称',
   ROLE_CODE            varchar(120) comment '角色编号',
   ROLE_TYPE            varchar(40) comment '角色类型',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (ROLE_ID)
);

alter table SYS_ROLE comment '角色信息表';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   ID                   bigint not null auto_increment comment '登陆内码',
   USER_CODE            varchar(40) comment '用户编号',
   CELL_PHONE           varchar(40) comment '电话',
   EMAIL                varchar(40) comment '邮箱',
   QQ                   varchar(40) comment 'Qq',
   WECHAT               varchar(40) comment '微信',
   ALIPAY               varchar(40) comment '支付宝',
   INTEREST             varchar(200) comment '兴趣',
   COUNTRY              int comment '国家',
   PROVINCE             int comment '省份',
   CITY                 int comment '县',
   ADRESS               varchar(400) comment '详细地址',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   CREATE_TIME          datetime comment '创建时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   UPDATE_TIME          datetime comment '修改时间',
   REMARK               varchar(200) comment '备注',
   primary key (ID)
);

alter table SYS_USER comment '用户信息表 ';

/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
create table SYS_USER_ROLE
(
   ID                   bigint not null comment '用户内码',
   ROLE_ID              bigint not null comment '角色内码',
   primary key (ID, ROLE_ID)
);

alter table SYS_USER_ROLE comment '用户角色表';

