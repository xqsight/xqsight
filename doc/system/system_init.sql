/**部门表初始化 **/
INSERT INTO `sys_department` (`department_id`,`parent_id`,`parent_ids`,`department_name`,`department_type`,`active`,`department_code`,`icon`,`sort`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remark`) VALUES
(1,0,',0,','部门管理','default',0,'_system_department',NULL,1,NULL,NULL,NULL,NULL,NULL),
(2,1,',0,1,','开发部','01',0,'develop','',1,'1','2017-02-08 15:21:00','1','2017-02-08 15:22:53',''),
(3,1,',0,1,','前端技术部','01',0,'pre_dept','',2,'1','2017-02-08 15:21:45','1','2017-02-08 15:21:45','');


/** 字典表初始化 **/
INSERT INTO `sys_dict` (`dict_id`,`parent_id`,`parent_ids`,`sort`,`dict_code`,`dict_name`,`dict_value`,`editable`,`active`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remark`) VALUES
(1,0,',0,',1,'_system_dict','系统字典','_sys_dict',-1,0,NULL,NULL,NULL,NULL,NULL),
(7,1,',0,1,',1,'is_edit','可否编辑','',1,0,'1','2017-02-08 18:58:00','1','2017-02-08 18:58:53',''),
(8,7,',0,1,7,',1,'yes','是','1',1,0,'1','2017-02-08 18:59:10','1','2017-02-08 19:01:25',''),
(9,7,',0,1,7,',1,'no','否','1',1,0,'1','2017-02-08 18:59:35','1','2017-02-08 19:01:18',''),
(10,1,',0,1,',1,'user_type','用户类型','',1,0,'1','2017-02-08 19:00:10','1','2017-02-08 19:00:10',''),
(11,10,',0,1,10,',1,'1','普通用户','1',1,0,'1','2017-02-08 19:00:42','1','2017-02-08 19:00:42','');


/** 角色表初始化 **/
INSERT INTO `sys_role` (`role_id`,`parent_id`,`parent_ids`,`role_name`,`role_type`,`active`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remark`) VALUES
(1,0,',0,','系统角色','default',0,NULL,NULL,NULL,NULL,NULL),
(2,1,',0,1,','系统管理',NULL,0,'1','2017-02-07 17:25:10','1','2017-02-07 17:25:10','');


/** 菜单表初始化 **/
INSERT INTO `sys_menu` (`menu_id`,`parent_id`,`parent_ids`,`target_type`,`is_head`,`menu_name`,`url`,`icon`,`type`,`permission`,`sort`,`active`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remark`) VALUES
 (1,0,',0,','iframe-tab','-1','系统菜单',NULL,NULL,0,NULL,1,0,'1','2017-02-07 17:18:37','1','2017-02-07 17:18:37',NULL),
 (2,1,',0,1,','iframe-tab','-1','系统管理','','',0,'',2,0,'1','2017-02-07 17:18:37','1','2017-02-07 17:18:37',''),
 (3,2,',0,1,2,','iframe-tab','-1','菜单管理','system/menu/menuMain.html','',0,'sys:menu:*',3,0,'1','2017-02-07 17:18:37','1','2017-02-07 17:24:37',''),
 (4,2,',0,1,2,','iframe-tab','-1','角色管理','system/role/roleMain.html',NULL,0,'sys:role:*',4,0,'1','2017-02-07 17:18:37','1','2017-02-07 17:18:37',NULL),
 (5,2,',0,1,2,','iframe-tab','-1','部门管理','system/department/departmentMain.html','',0,'sys:department:*',5,0,'1','2017-02-07 17:30:18','1','2017-02-07 21:20:16',''),
 (6,2,',0,1,2,','iframe-tab','-1','字典管理','system/dict/dictMain.html','',0,'sys:dict:*',6,0,'1','2017-02-07 17:45:51','1','2017-02-07 21:20:23',''),
 (7,2,',0,1,2,','iframe-tab','-1','区域管理','system/area/areaMain.html','',0,'sys:area:*',7,0,'1','2017-02-07 17:45:51','1','2017-02-07 21:20:23','');

/** 区域表初始化 **/
INSERT INTO `sys_area` VALUES (1,0,',0,','系统区域','_sys_area',0,NULL,0,0,'1','2017-02-22 18:27:50','1','2017-02-22 18:27:50','qw');

/** 机构表初始化 **/
INSERT INTO `sys_office` (`office_id`, `area_id`, `parent_id`, `parent_ids`, `office_name`, `office_code`, `office_type`) VALUES
('1', '1', '0', ',0,', '系统机构', '_sys_office', '1');

/** 登录信息初始化 **/
INSERT INTO `sys_login` (`id`, `login_id`,`status`, `active`, `create_time`, `create_user_id`, `update_time`, `update_user_id`, `remark`) values
	(1, 'admin', 0, 0, '2016-01-12 11:55:43', 'test', '2016-01-12 11:55:43', 'test', NULL),
	(2, 'jerry', 0, 0, '2016-01-12 10:24:29', 'test', '2016-01-12 10:24:29', 'test', NULL);

/** 用户信息初始化 **/
INSERT INTO `sys_user` (`id`,`department_id`,`user_name`,`password`,`salt`,`active`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remark`) VALUES
(1,2,'管理员','487dfa8e448a55606364964e73016104c55a226fb0f489655e016334a072f0fd','201406e096abed6f1dfee2028f4ad914',0,NULL,NULL,NULL,NULL,NULL),
(2,2,'测试','487dfa8e448a55606364964e73016104c55a226fb0f489655e016334a072f0fd','201406e096abed6f1dfee2028f4ad914',0,NULL,NULL,NULL,NULL,NULL);

/** 用户权限初始化 **/
INSERT INTO `sys_menu_role`(`menu_id`,`role_id`)VALUES
(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2);

INSERT INTO `sys_user_role`(`id`,`role_id`)VALUES
(1,2),(2,2);






