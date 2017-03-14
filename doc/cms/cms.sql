drop table if exists cms_ad;

drop table if exists cms_article;

drop table if exists cms_article_tag;

drop table if exists cms_job;

drop table if exists cms_model;

drop table if exists cms_position;

drop table if exists cms_site;

drop table if exists cms_tag;

/*==============================================================*/
/* Table: cms_ad                                                */
/*==============================================================*/
create table cms_ad
(
   ad_id                bigint not null auto_increment comment '广告内码',
   site_id              bigint not null default 0 comment '站点内码',
   ad_name              varchar(40) not null comment '名称',
   ad_url               varchar(400) comment '广告url',
   ad_text              varchar(200) comment '文字',
   ad_script            varchar(400) comment '代码',
   ad_image             varchar(100) comment '图片',
   ad_flash             varchar(400) comment 'flash',
   ad_begin_time        datetime comment '开始时间',
   ad_end_time          datetime comment '结束时间',
   type                 tinyint(4) comment '类型',
   sort                 smallint(6) comment '排序',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (ad_id)
);

alter table cms_ad comment '广告表';

/*==============================================================*/
/* Table: cms_article                                           */
/*==============================================================*/
create table cms_article
(
   article_id           bigint not null auto_increment comment '文章内码',
   model_id             bigint not null default 0 comment '模块内码',
   article_img          varchar(120) comment '文章缩列图',
   article_title        varchar(120) comment '文章标题',
   article_author       varchar(40) comment '文章作者',
   article_desp         varchar(120) comment '文章描述',
   article_content      text comment '文章内容',
   article_url          varchar(120) comment '文章访问URl',
   department           varchar(100) comment '部门',
   article_source       varchar(100) comment '文章来源',
   publish_time         datetime comment '发布时间',
   article_hit          tinyint(4) comment '是否显示 0:显示-1:隐藏',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (article_id)
);

alter table cms_article comment '文章表';

/*==============================================================*/
/* Table: cms_article_tag                                       */
/*==============================================================*/
create table cms_article_tag
(
   tag_id               bigint not null default 0 comment '标签内码',
   article_id           bigint not null default 0 comment '文章内码',
   primary key (tag_id, article_id)
);

alter table cms_article_tag comment '文章标签表';

/*==============================================================*/
/* Table: cms_job                                               */
/*==============================================================*/
create table cms_job
(
   job_id               bigint not null auto_increment comment '内码',
   position_id          bigint not null default 0 comment '职位内码',
   job_name             varchar(120) not null comment '招聘名称',
   job_start_time       datetime comment '招聘开始时间',
   job_end_time         datetime comment '招聘结束时间',
   job_content          text comment '招聘内容',
   job_phone            varchar(20) comment '电话',
   status               tinyint(4) default 1 comment '状态 1:正常2:结束3:4:',
   job_address          varchar(200) comment '地点',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (job_id)
);

alter table cms_job comment '招聘表';


/*==============================================================*/
/* Table: cms_model                                             */
/*==============================================================*/
create table cms_model
(
   model_id             bigint not null auto_increment comment '模块内码',
   site_id              bigint not null default 0 comment '站点内码',
   parent_id            bigint not null default 0 comment '上级',
   parent_ids           varchar(200) comment '所有上级',
   model_code           varchar(40) comment '模块编号',
   model_name           varchar(40) not null comment '模块名称',
   model_type           tinyint(4) default 0 comment '模块类型',
   model_url            varchar(40) comment '模块URl',
   model_desp           varchar(200) comment '模块说明',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (model_id)
);

alter table cms_model comment '模块表';

/*==============================================================*/
/* Table: cms_position                                          */
/*==============================================================*/
create table cms_position
(
   position_id          bigint not null auto_increment comment '职位内码',
   parent_id            bigint not null default 0 comment '上级',
   parent_ids           varchar(200) not null default '0' comment '所有上级',
   position_name        varchar(100) not null comment '职位名称',
   position_code        varchar(100) comment '职位编码',
   sort                 smallint(6) comment '排序',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (position_id)
);

alter table cms_position comment '职位表';

/*==============================================================*/
/* Table: cms_site                                              */
/*==============================================================*/
create table cms_site
(
   site_id              bigint not null auto_increment comment '站点内码',
   parent_id            bigint not null default 0 comment '上级站点',
   parent_ids           varchar(200) comment '所有上级',
   site_code            varchar(40) comment '站点编号',
   site_name            varchar(40) not null comment '站点名称',
   icon                 varchar(20) comment '图标',
   sort                 tinyint(6) default 0 comment '排序',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (site_id)
);

alter table cms_site comment '站点表';

/*==============================================================*/
/* Table: cms_tag                                               */
/*==============================================================*/
create table cms_tag
(
   tag_id               bigint not null auto_increment comment '标签内码',
   tag_name             varchar(10) not null comment '标签名称',
   active               tinyint(4) not null default 0 comment '是否有效 0:有效 -1:无效',
   create_user_id       varchar(40) comment '创建人ID',
   create_time          datetime comment '创建时间',
   update_user_id       varchar(40) comment '修改人ID',
   update_time          datetime comment '修改时间',
   remark               varchar(200) comment '备注',
   primary key (tag_id)
);

alter table cms_tag comment '标签表';
