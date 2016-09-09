/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/6/7 13:38:45                            */
/*==============================================================*/


drop table if exists BEAUTY_PARLOR;

drop table if exists BOOLD_PRESSURE;

drop table if exists BOOLD_SUGAR;

drop table if exists CMS_APP;

drop table if exists CMS_ARTICLE;

drop table if exists CMS_ATTENTION;

drop table if exists CMS_COMMENT;

drop table if exists CMS_MODEL;

drop table if exists CMS_ARTICLE_REPORT;

drop table if exists ECG;

drop table if exists FAT;

drop table if exists GENE_VEDIO;

drop table if exists OXYGEN;

drop table if exists PRODUCT;

drop table if exists REPORT_RECORD;

drop table if exists STEP_COUNTER;

/*==============================================================*/
/* Table: BEAUTY_PARLOR                                         */
/*==============================================================*/
create table BEAUTY_PARLOR
(
   BEAUTY_ID            bigint not null auto_increment comment '主键',
   FILE_ID              varchar(500) comment '附件ID',
   BEAUTY_NAME          varchar(100) comment '美容院名称',
   BEAUTY_ADDRESS       varchar(120) comment '美容院店址',
   BEAUTY_PHONE         varchar(120) default '0' comment '美容院电话',
   BEAUTY_QQ            varchar(120) comment '美容院qq',
   BEAUTY_DESCRIPT      varchar(1000) comment '美容院描述',
   BEAUTY_LNG           varchar(40) comment '经度',
   BEAUTY_LAT           varchar(40) comment '纬度',
   COMMENT_HAS_PIC      int comment '是否有图片0:有-1:没有',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (BEAUTY_ID)
);

alter table BEAUTY_PARLOR comment '美容院表';

/*==============================================================*/
/* Table: BOOLD_PRESSURE                                        */
/*==============================================================*/
create table BOOLD_PRESSURE
(
   BOOLD_ID             bigint not null auto_increment comment '主键',
   TEST_TIME            datetime comment '测试时间',
   SYSTOLIC_PRESSURE    varchar(120) comment '收缩压',
   DISATOLIC_PRESSURE   varchar(120) default '0' comment '收缩压',
   VENOUS_PRESSURE      varchar(120) comment '舒张压',
   EXCEPTION            varchar(120) comment '异常情况',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (BOOLD_ID)
);

alter table BOOLD_PRESSURE comment '血压记录表';

/*==============================================================*/
/* Table: BOOLD_SUGAR                                           */
/*==============================================================*/
create table BOOLD_SUGAR
(
   BOOLD_ID             bigint not null auto_increment comment '主键',
   TEST_TIME            datetime comment '测试时间',
   EMPTY                varchar(120) comment '空腹',
   TWO_HOURS            varchar(120) default '0' comment '两小时后',
   RANDOM               varchar(120) comment '随即',
   EXCEPTION            varchar(120) comment '异常情况',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (BOOLD_ID)
);

alter table BOOLD_SUGAR comment ' 血糖记录表';

/*==============================================================*/
/* Table: CMS_APP                                               */
/*==============================================================*/
create table CMS_APP
(
   APP_ID               bigint not null auto_increment comment '站点ID',
   APP_CODE             varchar(120) comment '站点编号',
   APP_NAME             varchar(120) comment '站点名称',
   APP_DOMAIN           varchar(200) comment '站点域名',
   APP_LOGO             varchar(500) comment '站定logo',
   APP_KEYWORD          varchar(400) comment '站点关键字',
   APP_COPYRIGHT        varchar(200) comment '站点版本信息',
   APP_STYLE            varchar(120) comment '站点风格',
   APP_MANAGERID        varchar(40) comment '电子邮箱',
   APP_DESCRIPTION      varchar(500) comment '站点管理员ID',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (APP_ID)
);

alter table CMS_APP comment '系统应用表';

/*==============================================================*/
/* Table: CMS_ARTICLE                                           */
/*==============================================================*/
create table CMS_ARTICLE
(
   ARTICLE_ID           bigint not null auto_increment comment '文章编号',
   MODEL_ID             bigint not null comment '所属模块',
   FILE_ID              varchar(1000) comment '附件ID',
   ARTICLE_TITLE        varchar(1000) default '0' comment '标题',
   ARTICLE_AUTHOR       varchar(120) default '01' comment '作者',
   ARTICLE_CONTENT      text comment '内容',
   ARTICLE_DESCRIPTION  varchar(500) comment '描述',
   ARTICLE_TYPE         varchar(10) comment '文章类型',
   ARTICLE_URL          varchar(200) comment '链接URL',
   ARTICLE_KEYWORD      varchar(500) comment '关键字',
   ARTICLE_SOURCLE      varchar(200) comment '来源',
   ARTICLE_HIT          int default 0 comment '是否显示0:显示-1:隐藏',
   ARTICLE_HAS_PIC      int default 0 comment '是否有图片0:有-1:没有',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (ARTICLE_ID, MODEL_ID)
);

alter table CMS_ARTICLE comment '文章表';

/*==============================================================*/
/* Table: CMS_ATTENTION                                         */
/*==============================================================*/
create table CMS_ATTENTION
(
   ATTENTION_ID         bigint not null auto_increment comment '收藏主键',
   ASSOCICATION_ID      bigint not null comment '收藏的ID',
   ATTENTION_TYPE       int default 1 comment '收藏类型  1:收藏2:顶3.关注',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (ATTENTION_ID)
);

alter table CMS_ATTENTION comment '用户收藏表';

/*==============================================================*/
/* Table: CMS_ARTICLE_REPORT                                         */
/*==============================================================*/
create table CMS_ARTICLE_REPORT
(
   REPORT_ID            bigint not null auto_increment comment '举报主键',
   ASSOCICATION_ID      bigint not null comment '收藏的ID',
   REPORT_TYPE          int comment '举报类型',
   REPORT_CONTENT          varchar(400) comment '举报内容',
   DEAL_STATUS          int default -1 comment '处理状态 0:已处理 -1:未处理',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (REPORT_ID)
);

alter table CMS_ARTICLE_REPORT comment '帖子举报表';


/*==============================================================*/
/* Table: CMS_COMMENT                                           */
/*==============================================================*/
create table CMS_COMMENT
(
   COMMENT_ID           bigint not null auto_increment comment '评论主键',
   ASSOCICATION_ID      bigint not null comment '关联评论的ID',
   COMMENT              text comment '评论内容',
   COMMENT_HAS_PIC      int default 0 comment '是否有图片0:有-1:没有',
   COMMENT_HIT          int default 0 comment '是否显示0:显示-1:隐藏',
   COMMENT_POINTS       numeric(4,2) default 0 comment '评分',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (COMMENT_ID)
);

alter table CMS_COMMENT comment '评论表';

/*==============================================================*/
/* Table: CMS_MODEL                                             */
/*==============================================================*/
create table CMS_MODEL
(
   MODEL_ID             bigint not null auto_increment comment '模块ID',
   APP_ID               bigint not null comment '应用ID',
   MODEL_CODE           varchar(200) not null comment '应用编号',
   PARENT_ID            bigint default 0 comment '父模块',
   MODEL_CLASS          char(2) default '01' comment '模块类型',
   MODEL_TITLE          varchar(200) comment '标题',
   MODEL_DESCRIPTION    varchar(500) comment '描述',
   MODEL_THUMBNAILS     varchar(1000) comment '缩略图',
   MODEL_SORT           int comment '排序',
   MODEL_URL            varchar(200) comment '模块链接',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (MODEL_ID)
);

alter table CMS_MODEL comment '模块信息表';

/*==============================================================*/
/* Table: ECG                                                   */
/*==============================================================*/
create table ECG
(
   BOOLD_ID             bigint not null auto_increment comment '主键',
   TEST_TIME            datetime comment '测试时间',
   HEART_RATE           varchar(120) comment '心率',
   EXCEPTION            varchar(120) comment '异常情况',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (BOOLD_ID)
);

alter table ECG comment '心电记录表';

/*==============================================================*/
/* Table: FAT                                                   */
/*==============================================================*/
create table FAT
(
   BOOLD_ID             bigint not null auto_increment comment '主键',
   TEST_TIME            datetime comment '测试时间',
   FAT_CONTENT          varchar(120) comment '脂肪含量',
   FAT_INDEX            varchar(120) default '0' comment '体质指数',
   WATER_CONTENT        varchar(120) comment '水分含量',
   BODY_SHAPE           varchar(120) comment '体型',
   FAT_RATE             varchar(120) comment '基础代谢率',
   EXCEPTION            varchar(120) comment '异常情况',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (BOOLD_ID)
);

alter table FAT comment '脂肪记录表';

/*==============================================================*/
/* Table: GENE_VEDIO                                            */
/*==============================================================*/
create table GENE_VEDIO
(
   VEDIO_ID             bigint not null auto_increment comment '视频ID',
   VEDIO_NAME           varchar(200) not null comment '视频名称',
   VEDIO_DESCRIPTION    varchar(500) default '0' comment '视频描述',
   VEDIO_TYPE           char(2) default '01' comment '视频类型',
   FILE_ID              varchar(200) comment '附件ID',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (VEDIO_ID)
);

alter table GENE_VEDIO comment '基因大讲堂表';

/*==============================================================*/
/* Table: OXYGEN                                                */
/*==============================================================*/
create table OXYGEN
(
   BOOLD_ID             bigint not null auto_increment comment '主键',
   TEST_TIME            datetime comment '测试时间',
   OXYGEN               varchar(120) comment '血氧',
   PULSE_RATE           varchar(120) default '0' comment '脉率',
   EXCEPTION            varchar(120) comment '异常情况',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (BOOLD_ID)
);

alter table OXYGEN comment '血氧记录表';

/*==============================================================*/
/* Table: PRODUCT                                               */
/*==============================================================*/
create table PRODUCT
(
   PRODUCT_ID           bigint not null auto_increment comment '产品ID',
   PRODUCT_NAME         varchar(200) not null comment '产品名称',
   PRODUCT_DESCRIPTION  varchar(500) default '0' comment '产品描述',
   PRODUCT_CONTENT      text comment '产品内容',
   PRODUCT_TYPE         char(2) default '01' comment '产品类型',
   PRODUCT_PRICE        numeric(5,2) default 0 comment '产品价格',
   BEGIN_TIME           datetime comment '开始时间',
   END_TIME             datetime comment '结束时间',
   FILE_ID              varchar(500) comment '附件ID',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (PRODUCT_ID)
);

alter table PRODUCT comment '产品表';

/*==============================================================*/
/* Table: REPORT_RECORD                                         */
/*==============================================================*/
create table REPORT_RECORD
(
   REPORT_ID            bigint not null auto_increment comment '记录主键',
   TEST_TIME            datetime comment '测试时间',
   REPORT_NAME          varchar(200) not null comment '名称',
   REPORT_DESCRIPT      varchar(500) comment '描述',
   REPORT_URL           varchar(200) comment '访问url',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (REPORT_ID)
);

alter table REPORT_RECORD comment '报告单表';

/*==============================================================*/
/* Table: STEP_COUNTER                                          */
/*==============================================================*/
create table STEP_COUNTER
(
   STEP_ID              bigint not null auto_increment comment '主键',
   STEP_COUNT           bigint comment '步数',
   KILOMERTRE_COUNT     numeric(15,2) comment '步行公里',
   ENERGY_COUNT         numeric(15,2) default 0 comment '消耗能量',
   ACTIVE               int not null default 0 comment '是否有效 0:有效 -1:无效',
   CREATE_TIME          datetime comment '创建时间',
   CREATE_OPR_ID        varchar(40) comment '创建人ID',
   UPDATE_TIME          datetime comment '修改时间',
   UPD_OPR_ID           varchar(40) comment '修改人ID',
   REMARK               varchar(200) comment '备注',
   primary key (STEP_ID)
);

alter table STEP_COUNTER comment ' 计步器表';

