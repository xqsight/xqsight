drop table if exists sys_area;

drop table if exists sys_dict;

drop table if exists sys_file;

drop table if exists sys_login;

drop table if exists sys_menu;

drop table if exists sys_menu_role;

drop table if exists sys_office;

drop table if exists sys_quick_key;

drop table if exists sys_role;

drop table if exists sys_station;

drop table if exists sys_user;

drop table if exists sys_user_role;

drop table if exists sys_user_station;

/*==============================================================*/
/* Table: sys_area                                              */
/*==============================================================*/
create table sys_area
(
   area_id              bigint not null auto_increment comment '区域内码',
   parent_id            bigint not null default 0 comment '父级id',
   parent_ids           varchar(200) not null default '0' comment '所有上级',
   area_name            varchar(100) not null comment '区域名称',
   area_code            varchar(100) comment '区域编码',
   area_type            tinyint(4) default 0 comment '区域类型',
   icon                 varchar(120) comment '图标',
   sort                 smallint(6) default 0 comment '排序',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (area_id)
);

alter table sys_area comment '区域表';

/*==============================================================*/
/* Table: sys_dict                                              */
/*==============================================================*/
create table sys_dict
(
   dict_id              bigint not null auto_increment comment '字典内码',
   parent_id            bigint default 0 comment '父级id',
   parent_ids           varchar(200) comment '所有上级',
   sort                 smallint(6) default 0 comment '排序',
   dict_code            varchar(120) comment '字典编号',
   dict_name            varchar(120) comment '字典名称',
   dict_value           varchar(120) comment '字典值',
   editable             tinyint(4) comment '是否可编辑',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
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
   status               tinyint(4) comment '用户状态 0:正常 1:查封 2:待审核',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (login_id)
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
   is_head              tinyint(4) default -1 comment '是否是导航栏 0是 -1 否',
   url                  varchar(200) comment '菜单访问URL',
   icon                 varchar(120) comment '图标',
   type                 tinyint(4) default 0 comment '类型 0：菜单1：功能',
   permission           varchar(200) comment '允许字符串',
   sort                 smallint(6) default 0 comment '排序',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
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
/* Table: sys_office                                            */
/*==============================================================*/
create table sys_office
(
   office_id            bigint not null auto_increment comment '内码',
   area_id              bigint not null default 0 comment '区域内码',
   parent_id            bigint default 0 comment '父级id',
   parent_ids           varchar(200) not null comment '所有上级',
   office_name          varchar(120) not null comment '名称',
   office_code          varchar(120) comment '编号',
   office_type          tinyint(4) not null default 1 comment '类型 1:公司 2:部门 3:小组 4:其他',
   address              varchar(120) comment '联系地址',
   zip_code             varchar(10) comment '邮政编码',
   master               varchar(40) comment '负责人',
   phone                varchar(40) comment '电话',
   fax                  varchar(15) comment '传真',
   email                varchar(40) comment '邮箱',
   sort                 smallint(6) default 0 comment '排序',
   icon                 varchar(40) comment '图标',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (office_id)
);

alter table sys_office comment '机构表';

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
   office_id            bigint not null default 0 comment '归属机构',
   role_name            varchar(120) not null comment '角色名称',
   role_enname          varchar(120) comment '英文名称',
   role_type            varchar(40) comment '角色类型',
   sys_flag             tinyint(4) comment '是否系统数据 0:是-1:否',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
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
   office_id            bigint not null default 0 comment '机构内码',
   station_name         varchar(120) not null comment '岗位名称',
   station_code         varchar(120) comment '岗位编号',
   station_type         tinyint(4) comment '岗位类型',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
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
   id                   bigint not null auto_increment comment '登陆内码',
   company_id           bigint not null default 0 comment '归属公司',
   office_id            bigint not null default 0 comment '所属部门',
   password             varchar(120) not null comment '登录密码',
   salt                 varchar(120) not null comment '随机数',
   user_name            varchar(40) comment '昵称',
   real_name            varchar(40) comment '真实姓名',
   user_code            varchar(40) comment '用户编号',
   sex                  tinyint(4) comment '性别 0:未知 1:男 2:女',
   user_born            datetime comment '生日',
   from_source          varchar(20) comment '来源 WECHAT  PC  MOBILE',
   img_url              varchar(120) comment '图片地址',
   cell_phone           varchar(40) comment '电话',
   email                varchar(40) comment '邮箱',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
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
