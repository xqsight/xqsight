/** 角色表初始化 **/
INSERT INTO `sys_role` (`role_id`, `office_id`, `role_name`, `role_enname`, `role_type`, `sys_flag`, `active`) VALUES
('1', '1', '系统管理', 'sys_manage', '0', '0', '0');

/** 菜单表初始化 **/
INSERT INTO `sys_menu` (`menu_id`,`parent_id`,`parent_ids`,`target_type`,`is_head`,`menu_name`,`url`,`icon`,`type`,`permission`,`sort`,`active`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remark`) VALUES
 (1,0,',0,','iframe-tab','-1','系统管理','','',0,'',2,0,'1','2017-02-07 17:18:37','1','2017-02-07 17:18:37',''),
 (2,1,',0,1,','iframe-tab','-1','菜单管理','system/menu/menuMain.html','',0,'sys:menu:*',3,0,'1','2017-02-07 17:18:37','1','2017-02-07 17:24:37',''),
 (3,1,',0,1,','iframe-tab','-1','角色管理','system/role/roleMain.html',NULL,0,'sys:role:*',4,0,'1','2017-02-07 17:18:37','1','2017-02-07 17:18:37',NULL),
 (4,1,',0,1,','iframe-tab','-1','机构管理','system/office/officeMain.html','',0,'sys:office:*',5,0,'1','2017-02-07 17:30:18','1','2017-02-07 21:20:16',''),
 (5,1,',0,1,','iframe-tab','-1','字典管理','system/dict/dictMain.html','',0,'sys:dict:*',6,0,'1','2017-02-07 17:45:51','1','2017-02-07 21:20:23',''),
 (6,1,',0,1,','iframe-tab','-1','区域管理','system/area/areaMain.html','',0,'sys:area:*',7,0,'1','2017-02-07 17:45:51','1','2017-02-07 21:20:23','');


/** 登录信息初始化 **/
INSERT INTO `sys_login` (`id`, `login_id`,`status`, `active`, `create_time`, `create_user_id`, `update_time`, `update_user_id`, `remark`) values
	(1, 'admin', 0, 0, '2016-01-12 11:55:43', 'test', '2016-01-12 11:55:43', 'test', NULL),
	(2, 'jerry', 0, 0, '2016-01-12 10:24:29', 'test', '2016-01-12 10:24:29', 'test', NULL);

/** 用户信息初始化 **/
INSERT INTO `sys_user` (`id`,`user_name`,`password`,`salt`,`active`,`create_user_id`,`create_time`,`update_user_id`,`update_time`,`remark`) VALUES
(1,'管理员','487dfa8e448a55606364964e73016104c55a226fb0f489655e016334a072f0fd','201406e096abed6f1dfee2028f4ad914',0,NULL,NULL,NULL,NULL,NULL),
(2,'测试','487dfa8e448a55606364964e73016104c55a226fb0f489655e016334a072f0fd','201406e096abed6f1dfee2028f4ad914',0,NULL,NULL,NULL,NULL,NULL);

/** 用户权限初始化 **/
INSERT INTO `sys_menu_role`(`menu_id`,`role_id`)VALUES
(1,1),(2,1),(3,1),(4,1),(5,1),(6,1);

INSERT INTO `sys_user_role`(`id`,`role_id`)VALUES
(1,1),(2,1);






