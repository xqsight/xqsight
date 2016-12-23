create table cms_app
(
   app_id               bigint not null auto_increment comment '站点id',
   app_code             varchar(120) comment '站点编号',
   app_name             varchar(120) comment '站点名称',
   app_domain           varchar(200) comment '站点域名',
   app_logo             varchar(500) comment '站定logo',
   app_keyword          varchar(400) comment '站点关键字',
   app_copyright        varchar(200) comment '站点版本信息',
   app_style            varchar(120) comment '站点风格',
   app_managerid        varchar(40) comment '电子邮箱',
   app_description      varchar(500) comment '站点管理员id',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_time          datetime comment '创建时间',
   create_opr_id        varchar(40) comment '创建人id',
   update_time          datetime comment '修改时间',
   upd_opr_id           varchar(40) comment '修改人id',
   remark               varchar(200) comment '备注',
   primary key (app_id)
);

alter table cms_app comment '系统应用表';

create table cms_article
(
   article_id           bigint not null auto_increment comment '文章编号',
   model_id             bigint not null comment '所属模块',
   file_id              varchar(1000) comment '附件id',
   article_title        varchar(1000) default '0' comment '标题',
   article_author       varchar(120) default '01' comment '作者',
   article_content      text comment '内容',
   article_description  varchar(500) comment '描述',
   article_type         varchar(10) comment '文章类型',
   article_url          varchar(200) comment '链接url',
   article_keyword      varchar(500) comment '关键字',
   article_sourcle      varchar(200) comment '来源',
   article_hit          int default 0 comment '是否显示0:显示-1:隐藏',
   article_has_pic      int default 0 comment '是否有图片0:有-1:没有',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_time          datetime comment '创建时间',
   create_opr_id        varchar(40) comment '创建人id',
   update_time          datetime comment '修改时间',
   upd_opr_id           varchar(40) comment '修改人id',
   remark               varchar(200) comment '备注',
   primary key (article_id, model_id)
);

alter table cms_article comment '文章表';

/*==============================================================*/
/* table: cms_attention                                         */
/*==============================================================*/
create table cms_attention
(
   attention_id         bigint not null auto_increment comment '收藏主键',
   assocication_id      bigint not null comment '收藏的id',
   attention_type       int default 1 comment '收藏类型  1:收藏2:顶3.关注',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_time          datetime comment '创建时间',
   create_opr_id        varchar(40) comment '创建人id',
   update_time          datetime comment '修改时间',
   upd_opr_id           varchar(40) comment '修改人id',
   remark               varchar(200) comment '备注',
   primary key (attention_id)
);

alter table cms_attention comment '用户收藏表';

/*==============================================================*/
/* table: cms_article_report                                         */
/*==============================================================*/
create table cms_article_report
(
   report_id            bigint not null auto_increment comment '举报主键',
   assocication_id      bigint not null comment '收藏的id',
   report_type          int comment '举报类型',
   report_content          varchar(400) comment '举报内容',
   deal_status          int default -1 comment '处理状态 0:已处理 -1:未处理',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_time          datetime comment '创建时间',
   create_opr_id        varchar(40) comment '创建人id',
   update_time          datetime comment '修改时间',
   upd_opr_id           varchar(40) comment '修改人id',
   remark               varchar(200) comment '备注',
   primary key (report_id)
);

alter table cms_article_report comment '帖子举报表';


/*==============================================================*/
/* table: cms_comment                                           */
/*==============================================================*/
create table cms_comment
(
   comment_id           bigint not null auto_increment comment '评论主键',
   assocication_id      bigint not null comment '关联评论的id',
   comment              text comment '评论内容',
   comment_has_pic      int default 0 comment '是否有图片0:有-1:没有',
   comment_hit          int default 0 comment '是否显示0:显示-1:隐藏',
   comment_points       numeric(4,2) default 0 comment '评分',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_time          datetime comment '创建时间',
   create_opr_id        varchar(40) comment '创建人id',
   update_time          datetime comment '修改时间',
   upd_opr_id           varchar(40) comment '修改人id',
   remark               varchar(200) comment '备注',
   primary key (comment_id)
);

alter table cms_comment comment '评论表';

/*==============================================================*/
/* table: cms_model                                             */
/*==============================================================*/
create table cms_model
(
   model_id             bigint not null auto_increment comment '模块id',
   app_id               bigint not null comment '应用id',
   model_code           varchar(200) not null comment '应用编号',
   parent_id            bigint default 0 comment '父模块',
   model_class          char(2) default '01' comment '模块类型',
   model_title          varchar(200) comment '标题',
   model_description    varchar(500) comment '描述',
   model_thumbnails     varchar(1000) comment '缩略图',
   model_sort           int comment '排序',
   model_url            varchar(200) comment '模块链接',
   active               int not null default 0 comment '是否有效 0:有效 -1:无效',
   create_time          datetime comment '创建时间',
   create_opr_id        varchar(40) comment '创建人id',
   update_time          datetime comment '修改时间',
   upd_opr_id           varchar(40) comment '修改人id',
   remark               varchar(200) comment '备注',
   primary key (model_id)
);

alter table cms_model comment '模块信息表';