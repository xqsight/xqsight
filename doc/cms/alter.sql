ALTER TABLE `ejusite`.`cms_article`
ADD COLUMN `thumbnail_img` VARCHAR(120) NULL COMMENT '文章缩列图'


ALTER TABLE `ejusite`.`cms_article`
ADD COLUMN `scan_times` bigint not null default 0 COMMENT '浏览量'

ALTER TABLE `ejusite`.`cms_article`
ADD COLUMN `agree_times` bigint not null default 0 COMMENT '点赞量'


ALTER TABLE `ejusite`.`cms_job`
ADD COLUMN `position_desp` text COMMENT '职位描述'

ALTER TABLE `ejusite`.`cms_job`
ADD COLUMN `job_need` text COMMENT '岗位需求'

ALTER TABLE `ejusite`.`cms_job`
ADD COLUMN `job_email` varchar(120) null COMMENT '邮箱'
