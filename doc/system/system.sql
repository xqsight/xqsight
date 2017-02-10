drop table if exists sys_department;

drop table if exists sys_dict;

drop table if exists sys_file;

drop table if exists sys_login;

drop table if exists sys_menu;

drop table if exists sys_menu_role;

drop table if exists sys_quick_key;

drop table if exists sys_role;

drop table if exists sys_station;

drop table if exists sys_user;

drop table if exists sys_user_role;

drop table if exists sys_user_station;

/*==============================================================*/
/* Table: sys_department                                        */
/*==============================================================*/
create table sys_department
(
   department_id        bigint not null auto_increment comment '部门内码',
   parent_id            bigint default 0 comment '父级id',
   parent_ids           varchar(200) comment '所有上级',
   department_name      varchar(120) not null comment '部门名称',
   department_type      varchar(40) comment '部门类型',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   department_code      varchar(120) comment '部门编号',
   icon                 varchar(120) comment '图标',
   sort                 INT default 0 comment '排序',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (department_id)
);

alter table sys_department comment '部门表';

/*==============================================================*/
/* Table: sys_dict                                              */
/*==============================================================*/
create table sys_dict
(
   dict_id              bigint not null auto_increment comment '字典内码',
   parent_id            bigint default 0 comment '父级id',
   parent_ids           varchar(200) comment '所有上级',
   sort                 int default 0 comment '排序',
   dict_code            varchar(120) comment '字典编号',
   dict_name            varchar(120) comment '字典名称',
   dict_value           varchar(120) comment '字典值',
   editable             int comment '是否可编辑',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (dict_id)
);

alter table sys_dict comment '系统字典表';

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
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
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
   id                   bigint not null comment '登陆内码',
   login_id             varchar(120) comment '登陆名称',
   locked               int comment '是否锁定 0-未锁定 -1-锁定',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
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
   parent_id            bigint default 0 comment '父级id',
   parent_ids           varchar(200) comment '所有上级',
   menu_name            varchar(120) not null comment '菜单名称',
   target_type          varchar(20) default 'iframe-tab' comment '跳转类型',
   is_head              INT default -1 comment '是否是导航栏 0是 -1 否',
   url                  varchar(200) comment '菜单访问URL',
   icon                 varchar(120) comment '图标',
   type                 INT default 0 comment '类型 0：菜单1：功能',
   permission           varchar(200) comment '允许字符串',
   sort                 INT default 0 comment '排序',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (menu_id)
);

alter table sys_menu comment '菜单信息表';

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
   Id                   bigint not null comment '登陆内码',
   fun_order            int not null comment '功能序号',
   key_icon             varchar(120) comment '图标',
   key_title            varchar(120) comment '标题',
   key_value            varchar(120) comment '连接值',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (Id, fun_order)
);

alter table sys_quick_key comment '快捷键表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   role_id              bigint not null auto_increment comment '角色内码',
   parent_id            bigint default 0 comment '父级id',
   parent_ids           varchar(200) comment '所有上级',
   role_name            varchar(120) comment '角色名称',
   role_type            varchar(40) comment '角色类型',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (role_id)
);

alter table sys_role comment '角色信息表';

/*==============================================================*/
/* Table: sys_station                                           */
/*==============================================================*/
create table sys_station
(
   station_id           bigint not null auto_increment comment '岗位内码',
   department_id        bigint default 0 comment '部门内码',
   station_name         varchar(120) comment '岗位名称',
   station_code         varchar(120) comment '岗位编号',
   station_type         varchar(40) comment '岗位类型',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (station_id)
);

alter table sys_station comment '岗位信息表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   ID                   bigint not null auto_increment comment '登陆内码',
   department_id        bigint not null default 0 comment '所属部门',
   password             varchar(120) comment '登录密码',
   salt                 varchar(120) comment '随机数',
   user_name            varchar(40) comment '昵称',
   real_name            varchar(40) comment '真实姓名',
   user_code            varchar(40) comment '用户编号',
   sex                  int comment '性别 0:未知 1:男 2:女',
   user_born            datetime comment '生日',
   age                  int comment '年龄',
   from_source          varchar(20) comment '来源 WECHAT  PC  MOBILE',
   img_url              varchar(120) comment '图片地址',
   cell_phone           varchar(40) comment '电话',
   email                varchar(40) comment '邮箱',
   qq                   varchar(40) comment 'Qq',
   wecaht               varchar(40) comment '微信',
   alipay               varchar(40) comment '支付宝',
   interest             varchar(200) comment '兴趣',
   country              int comment '国家',
   province             int comment '省份',
   city                 int comment '县',
   address              varchar(400) comment '详细地址',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (ID)
);

alter table sys_user comment '用户信息表 ';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   bigint not null comment '用户内码',
   role_id              bigint not null comment '角色内码',
   primary key (id, role_id)
);

alter table sys_user_role comment '用户角色表';

/*==============================================================*/
/* Table: sys_user_station                                      */
/*==============================================================*/
create table sys_user_station
(
   id                   bigint not null comment '用户内码',
   station_id           bigint not null comment '岗位内码',
   primary key (id, station_id)
);

alter table sys_user_station comment '用户岗位表';
