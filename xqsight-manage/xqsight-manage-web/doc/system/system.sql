/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/6/7 13:35:59                            */
/*==============================================================*/


drop table if exists sys_dict;

drop table if exists sys_dict_detail;

drop table if exists sys_file;

drop table if exists sys_login;

drop table if exists sys_menu;

drop table if exists sys_menu_role;

drop table if exists sys_quick_key;

drop table if exists sys_role;

drop table if exists sys_user;

drop table if exists sys_user_role;

drop table if exists sys_org;

drop table if exists sys_log;

/*==============================================================*/
/* Table: sys_dict                                              */
/*==============================================================*/
create table sys_dict
(
   dict_id              bigint not null auto_increment comment '字典内码',
   dict_code            varchar(120) comment '字典编号',
   dict_name            varchar(120) comment '字典名称',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (dict_id)
);

alter table sys_dict comment '系统字典表';

/*==============================================================*/
/* Table: sys_dict_detail                                       */
/*==============================================================*/
create table sys_dict_detail
(
   dict_detail_id       bigint not null auto_increment comment '明细编号',
   dict_id              bigint not null comment '字典编号',
   dict_value           VARCHAR(40) not null comment '字典值',
   dict_desp            VARCHAR(120) not null comment '字典描述',
   editable             int default 0 comment '是否可编辑0:有效-1:无效',
   dict_lang            varchar(120) comment '语言',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (dict_detail_id)
);

alter table sys_dict_detail comment '系统字典明细表';

/*==============================================================*/
/* Table: sys_file                                              */
/*==============================================================*/
create table sys_file
(
   file_id              bigint not null auto_increment comment '主键',
   file_name            varchar(200) comment '文件名称',
   file_url             varchar(500) comment '附件URL',
   file_domain          varchar(500) comment '文件域',
   file_ext             varchar(10) default '0' comment '扩展名',
   file_size            varchar(10) default '0' comment '大小',
   attachment_type      char(2) default '0' comment '附件类型 01:普通图 02:缩略图 03:LOGO',
   file_kind            varchar(10) default '0' comment '附件种类 Image Vedio Doc Excel Ppt',
   active               int not null default 0 comment '是否有效  0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (file_id)
);

alter table sys_file comment ' 文件表';

/*==============================================================*/
/* Table: sys_login                                             */
/*==============================================================*/
create table sys_login
(
   id                   bigint not null auto_increment comment '登陆内码',
   org_id               bigint  comment '组织机构',
   login_id             varchar(120) comment '登陆名称',
   user_name            varchar(120) comment '昵称',
   real_name            varchar(120) comment '真实姓名',
   password             varchar(120) comment '登陆密码',
   login_type           int comment '登陆类型 1:编号 2:邮箱 3:电话',
   sex                  int comment '性别 0:未知 1:男 2:女',
   user_born            datetime comment '生日',
   age                  int comment '年龄',
   from_source          varchar(20) comment '来源 WECHAT  PC  MOBILE',
   img_url              varchar(120) comment '图片地址',
   salt                 varchar(120) comment '随机数',
   locked               int comment '是否锁定 0-未锁定 -1-锁定 ',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (id)
);

alter table sys_login comment '用户登陆表';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   menu_id              bigint not null auto_increment comment '菜单内码',
   parent_id            bigint not null default 0 comment '父级内码',
   menu_name            varchar(120) not null comment '菜单名称',
   url                  varchar(200) comment '菜单访问URL',
   icon                 varchar(120) comment '图标',
   type                 INT default 0 comment '类型 0：菜单1：功能',
   permission           varchar(200) comment '允许字符串',
   sort                 INT default 0 comment '排序',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (menu_id)
);

alter table sys_menu comment '菜单信息表';

/*==============================================================*/
/* Table: sys_org                                              */
/*==============================================================*/
create table sys_org
(
   org_id               bigint not null auto_increment comment '组织内码',
   parent_id            bigint not null default 0 comment '父级ID',
   org_name             varchar(120) not null comment '组织名称',
   org_type             varchar(40) comment '组织类型',
   org_code             varchar(120) comment '组织编号',
   custom_code          varchar(120) comment '自定义编号',
   sort                 INT default 0 comment '排序',
   icon                 varchar(40) comment '图标',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (org_id)
);

alter table sys_org comment '组织机构表';

/*==============================================================*/
/* Table: sys_menu_role                                         */
/*==============================================================*/
create table sys_menu_role
(
   menu_id              bigint not null comment '菜单内码',
   role_id              bigint not null comment '角色内码',
   primary key (menu_id, role_id)
);

alter table sys_menu_role comment '菜单角色表';

/*==============================================================*/
/* Table: sys_quick_key                                         */
/*==============================================================*/
create table sys_quick_key
(
   id                   bigint not null comment '登陆内码',
   fun_order            int not null comment '功能序号',
   key_icon             varchar(120) comment '图标',
   key_title            varchar(120) comment '标题',
   key_value            varchar(120) comment '连接值',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (id, fun_order)
);

alter table sys_quick_key comment '快捷键表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   role_id              bigint not null auto_increment comment '角色内码',
   role_name            varchar(120) comment '角色名称',
   role_code            varchar(120) comment '角色编号',
   role_type            varchar(40) comment '角色类型',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (role_id)
);

alter table sys_role comment '角色信息表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   bigint not null auto_increment comment '登陆内码',
   user_code            varchar(40) comment '用户编号',
   cell_phone           varchar(40) comment '电话',
   email                varchar(40) comment '邮箱',
   qq                   varchar(40) comment 'Qq',
   wechat               varchar(40) comment '微信',
   alipay               varchar(40) comment '支付宝',
   interest             varchar(200) comment '兴趣',
   country              int comment '国家',
   province             int comment '省份',
   city                 int comment '县',
   adress               varchar(400) comment '详细地址',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_opr_id        varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   upd_opr_id           varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (id)
);

alter table sys_user comment '用户信息表 ';

/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   bigint not null comment '用户内码',
   role_id              bigint not null comment '角色内码',
   primary key (id, role_id)
);

alter table sys_user_role comment '用户角色表';

/*==============================================================*/
/* table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   log_id               bigint not null auto_increment comment '日志内码',
   log_type            varchar(10) comment '日志类型',
   log_title            varchar(400) comment '日志标题',
   log_desc             varchar(2000) comment '日志描述',
   req_ip               varchar(200) comment '请求ip',
   req_url              varchar(200) comment '请求url',
   req_method           varchar(400) comment '请求方法',
   req_data             varchar(2000) comment '请求数据',
   exception            varchar(2000) comment '请求异常',
   agent_user           varchar(200) comment '用户代理',
   create_opr_id        varchar(40) comment '创建人id',
   create_time          datetime comment '创建时间',
   remark               varchar(200) comment '备注',
   primary key (log_id)
);

alter table sys_log comment '系统日志';


