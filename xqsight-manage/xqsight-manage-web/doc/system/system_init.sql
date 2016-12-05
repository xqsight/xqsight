
INSERT INTO `SYS_DICT` (`DICT_ID`, `DICT_CODE`, `DICT_NAME`, `active`, `create_time`, `create_opr_id`, `update_time`, `upd_opr_id`, `remark`) VALUES
	(1, 'active', '是否有效', 0, '2016-01-26 15:21:36', '2', '2016-01-26 15:21:36', '2', ''),
	(2, 'jobGroup', '任务组', 0, '2016-01-26 15:23:51', '2', '2016-01-26 15:28:57', '2', '');

INSERT INTO `SYS_DICT_DETAIL` (`DICT_DETAIL_ID`, `DICT_ID`, `DICT_VALUE`, `DICT_DESP`, `Editable`, `DICT_LANG`, `active`, `create_time`, `create_opr_id`, `update_time`, `upd_opr_id`, `remark`) VALUES
	(1, 1, '0', '有效', -1, 'chinese', 0, '2016-01-26 15:29:52', '2', '2016-01-26 15:29:52', '2', ''),
	(2, 1, '-1', '无效', -1, 'chinese', 0, '2016-01-26 15:30:12', '2', '2016-01-26 15:30:12', '2', ''),
	(3, 2, 'default', '默认组', 0, 'chinese', 0, '2016-01-26 15:34:49', '2', '2016-05-17 11:46:16', '1', '');

INSERT INTO `SYS_LOGIN` (`ID`, `ORG_ID`,`LOGIN_ID`, `USER_NAME`, `PASSWORD`, `LOGIN_TYPE`, `FROM_SOURCE`, `IMG_URL`, `SALT`, `LOCKED`, `SEX`, `AGE`, `ACTIVE`, `create_time`, `create_opr_id`, `update_time`, `upd_opr_id`, `remark`) VALUES
	(1, '2','admin', '管理员', '487dfa8e448a55606364964e73016104c55a226fb0f489655e016334a072f0fd', 3, NULL, '/upload/20160531/2016053114171100001.jpg', '201406e096abed6f1dfee2028f4ad914', 0, NULL, 0, 0, '2016-01-12 11:55:43', 'test', '2016-01-12 11:55:43', 'test', NULL),
	(2, '2','jerry', 'jerry', 'f66baef8bef93e45c0453e1b18b1a838f0f55fad57a3b0c1d3c83dbb6d1e9fb5', 1, NULL, NULL, 'ab6addd8c9ea9741baa0cb7c69a3f52f', 0, NULL, 0, 0, '2016-01-12 10:24:29', 'test', '2016-01-12 10:24:29', 'test', NULL);

INSERT INTO `SYS_MENU` (`MENU_ID`, `MENU_NAME`, `URL`, `Icon`, `type`, `permission`, `sort`, `PARENT_ID`, `active`, `create_time`, `create_opr_id`, `update_time`, `upd_opr_id`, `remark`) VALUES
	(1, '系统菜单', '', '', 0, '', 0, 0, 0, '2015-12-30 13:25:14', '1', '2015-12-30 13:25:23', '1', '1'),

	(2, '系统管理', '', '', 0, '', 0, 1, 0, '2015-12-30 13:25:14', '1', '2015-12-30 13:25:23', '1', '1'),

	(3, '菜单设置', 'system/menu/menuMain.html', NULL, 0, NULL, 1, 2, 0, '2016-01-06 13:29:49', NULL, '2016-01-06 13:29:51', NULL, NULL),
	(4, '新增', '/sys/menu/save', NULL, 1, 'sys:menu:save', 0, 3, 0, '2016-01-06 13:31:17', NULL, '2016-01-06 13:31:20', NULL, NULL),
	(5, '修改', '/sys/menu/save', '', 1, 'sys:menu:update', 0, 3, 0, '2016-01-06 13:31:16', NULL, '2016-02-01 16:08:06', '2', ''),
	(6, '删除', '/sys/menu/delete', '', 1, 'sys:menu:delete', 0, 3, 0, '2016-02-01 16:09:05', '2', '2016-02-01 16:09:05', '2', ''),
	(7, '查询', '/sys/menu/query', '', 1, 'sys:menu:query', 0, 3, 0, '2016-02-01 16:25:48', '2', '2016-02-01 16:25:48', '2', ''),

	(8, '角色管理', 'system/role/roleMain.html', '', 0, '', 2, 2, 0, '2016-01-07 11:36:31', 'test', '2016-01-07 11:36:31', 'test', ''),
	(9, '新增', '/sys/role/save', '', 1, 'sys:role:save', 0, 8, 0, '2016-01-07 11:50:23', 'test', '2016-02-01 16:09:34', '2', ''),
	(10, '修改', '/sys/role/update', '', 1, 'sys:role:update', 0, 8, 0, '2015-01-18 14:56:55', '2', '2016-02-01 16:09:53', '2', ''),
	(11, '删除', '/sys/role/delete', '', 1, 'sys:role:delete', 0, 8, 0, '2016-02-01 16:11:14', '2', '2016-02-01 16:11:14', '2', ''),
	(12, '查询', '/sys/role/query', '', 1, 'sys:role:query', 0, 8, 0, '2016-02-01 16:26:10', '5', '2016-02-01 16:26:10', '2', ''),
	(22, '查询所有', '/sys/role/queryall', '', 1, 'sys:role:queryall', 0, 8, 0, '2016-02-01 16:26:10', '5', '2016-02-01 16:26:10', '2', ''),
    (23, '查询授权用户', '/sys/auth/queryauthuser', '', 1, 'sys:auth:queryauthuser', 0, 8, 0, '2016-02-01 16:26:10', '5', '2016-02-01 16:26:10', '2', ''),

	(13, '登录管理', 'system/user/userMain.html', '', 0, '', 3, 2, 0, '2016-01-07 11:38:31', 'test', '2016-02-24 15:06:28', '3', ''),
	(14, '新增', '/sys/user/save', '', 1, 'sys:login:save', 0, 13, 0, '2015-01-18 14:57:34', '2', '2016-02-01 16:13:30', '2', ''),
	(15, '修改', '/sys/user/update', '', 1, 'sys:login:update', 0, 13, 0, '2015-01-18 14:58:08', '2', '2016-02-01 16:13:15', '2', ''),
	(16, '删除', '/sys/login/delete', '', 1, 'sys:login:delete', 0, 13, 0, '2016-02-01 16:14:10', '2', '2016-02-01 16:14:10', '2', ''),
	(17, '重置密码', '/sys/login/resetpwd', '', 1, 'sys:login:resetpwd', 0, 13, 0, '2016-02-01 16:15:03', '2', '2016-02-01 16:15:42', '2', ''),

	(18, '添加用户', '/sys/auth/saveuserrole', '', 1, 'sys:auth:saveuserrole', 0, 13, 0, '2016-02-01 16:16:42', '2', '2016-02-01 16:16:42', '2', ''),
	(19, '添加菜单', '/sys/auth/savemenurole', '', 1, 'sys:auth:savemenurole', 0, 13, 0, '2016-02-01 16:17:31', '2', '2016-02-01 16:17:31', '2', ''),
	(20, '查询', '/sys/login/query', '', 1, '', 0, 13, 0, '2016-02-01 16:26:38', '6', '2016-02-01 16:26:38', '2', ''),
	(21, '数据字典', 'system/dict/dictMain.html', '', 0, 'sys:dict:*', 4, 2, 0, '2016-01-18 13:45:34', '2', '2016-01-21 16:24:18', '2', '');



INSERT INTO `SYS_MENU_ROLE` (`MENU_ID`, `ROLE_ID`) VALUES
	(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1),
	(9, 1), (10, 1), (11, 1), (12, 1), (13, 1), (14, 1),
	(15, 1), (16, 1), (17, 1), (18, 1), (19, 1), (20, 1), (21, 1),(22, 1),(23, 1);

INSERT INTO `SYS_ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_CODE`, `ROLE_TYPE`, `active`, `create_time`, `create_opr_id`, `update_time`, `upd_opr_id`, `remark`) VALUES
	(1, '系统管理', 'admin', '', 0, '2016-01-07 14:41:06', 'test', '2016-01-20 11:35:54', '2', '主要维护系统权限');

INSERT INTO `SYS_USER_ROLE` (`ID`, `ROLE_ID`) VALUES
	(1, 1);

	INSERT INTO `sys_org` (`ORG_ID`, `PARENT_ID`, `ORG_NAME`, `ORG_TYPE`, `ORG_CODE`, `CUSTOM_CODE`, `SORT`, `ICON`, `ACTIVE`, `CREATE_OPR_ID`, `CREATE_TIME`, `UPD_OPR_ID`, `UPDATE_TIME`, `REMARK`) VALUES
	(1, 0, '系统组织', NULL, 'SYS_ORG_', 'SYS_ORG_', 0, NULL, 0, NULL, NULL, NULL, NULL, NULL),
	(2, 1, '系统管理', '01', 'SYSTEM_MANAGE', '', 1, '', 0, '1', '2016-06-20 21:12:35', '1', '2016-06-21 16:11:28', '');
