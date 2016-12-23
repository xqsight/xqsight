INSERT INTO portal.cms_app (app_id,app_code,app_name,app_domain,app_logo,app_keyword,app_copyright,app_style,app_managerid,app_description,active,create_time,create_opr_id,update_time,upd_opr_id,remark) VALUES
(1,'default','default','default','','','','','','',0,'2016-12-24 00:50:26.000','1','2016-12-24 00:53:10.000','1','');

INSERT INTO portal.cms_model (model_id,app_id,model_code,parent_id,model_class,model_title,model_description,model_thumbnails,model_sort,model_url,active,create_time,create_opr_id,update_time,upd_opr_id,remark) VALUES
(1,1,'default',0,'01','内容模块','default','default',1,NULL,0,NULL,NULL,NULL,NULL,NULL)
,(2,1,'new_01',1,'01','新闻展示','','',1,NULL,0,'2016-12-24 00:58:43.000','1',NULL,NULL,'');