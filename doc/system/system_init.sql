/**部门表初始化 **/
INSERT INTO `sys_department` VALUES (1,0,',0,','部门管理','default',0,'_system_department',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);

/** 字典表初始化 **/
INSERT INTO `sys_dict` VALUES (1,0,',0,',1,'_system_dict','系统字典','_sys_dict',-1,0,NULL,NULL,NULL,NULL,NULL);

/** 角色表初始化 **/
INSERT INTO `sys_role` VALUES (1,0,',0,','系统角色','_system_role','default',0,NULL,NULL,NULL,NULL,NULL);

/** 菜单表初始化 **/
INSERT INTO `sys_menu` VALUES (1,0,',0,','系统菜单',NULL,NULL,0,NULL,1,0,NULL,NULL,NULL,NULL,NULL);

/** 用户信息初始化 **/
insert into `sys_login` (`id`, `login_id`, `user_name`, `password`, `login_type`,`salt`, `locked`, `active`, `create_time`, `create_user_id`, `update_time`, `update_user_id`, `remark`) values
	(1, 'admin', '管理员', '487dfa8e448a55606364964e73016104c55a226fb0f489655e016334a072f0fd', 1,'201406e096abed6f1dfee2028f4ad914',  0, 0, '2016-01-12 11:55:43', 'test', '2016-01-12 11:55:43', 'test', NULL),
	(2, 'jerry', 'jerry', 'f66baef8bef93e45c0453e1b18b1a838f0f55fad57a3b0c1d3c83dbb6d1e9fb5', 1,'ab6addd8c9ea9741baa0cb7c69a3f52f', 0, 0, '2016-01-12 10:24:29', 'test', '2016-01-12 10:24:29', 'test', NULL);




