-- 20170330 by liuax01

-- 用户表 --
DELIMITER ;
drop table if exists sys_user;
create table sys_user
(
   user_id               varchar(32) not null comment '主键',
   login_name            varchar(32) not null comment '用户帐号',
   user_name           varchar(32) not null comment '用户姓名',
   user_mobile          varchar(32) default NULL comment '电话',
   user_mail            varchar(32) default NULL comment '邮箱',
  cert_no            varchar(32) default NULL comment '证件号码',
   company          varchar(32) default NULL comment '公司;后期可能更改',
   address              varchar(64) default NULL comment '地址',
   sex                  char(1)  null comment 'M男 F女',
   birthday             timestamp null comment '生日',
   type                 char(1) default '2' comment '类型 1 超级用户 2 普通用户',
   status               char(1) default '1' comment '1 可用 2 冻结（初始化状态 1 可用）',
   create_user          varchar(32) default NULL comment '创建人帐号',
   update_user          varchar(32) default NULL comment '更新人帐号',
   create_time           timestamp not null default CURRENT_TIMESTAMP comment '创建日期',
   update_time           timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改日期',
   extfld1               varchar(64) default NULL comment '扩展字段1',
   extfld2               varchar(128) default NULL comment '扩展字段2',
   extfld3               varchar(255) default NULL comment '扩展字段3',
   remark               varchar(128) default NULL comment '备注',
   primary key (user_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='用户表';


-- 用户密码表  --
DROP TABLE IF EXISTS `sys_user_pass`;
CREATE TABLE `sys_user_pass` (
  `login_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `update_pass_flag` char(1) DEFAULT 'N' COMMENT '强制修改密码标识',
  `login_count` int(7) DEFAULT NULL  COMMENT '登陆次数',
  `lock_flag` char(1) DEFAULT 'N' COMMENT '锁定标识',
  `lock_time` datetime DEFAULT null COMMENT '锁定时间',
   `errors` int(2) DEFAULT 0 COMMENT '当前错误次数',
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户密码表';

-- 菜单表 --
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` varchar(32) NOT NULL COMMENT '主键',
  `MENU_CODE` varchar(64) DEFAULT NULL COMMENT '菜单编码（用于排序）',
  `MENU_NAME` varchar(512) DEFAULT NULL COMMENT '菜单名',
  `MENU_LINK` varchar(512) DEFAULT NULL COMMENT '菜单链接',
  `MENU_ICON` varchar(64) DEFAULT NULL COMMENT '菜单图标',
  `PARENT_ID` varchar(64) DEFAULT NULL COMMENT '父级菜单ID',
  `IS_PARENT` char(1) DEFAULT NULL COMMENT '是否父级菜单',
  `status` char(1) DEFAULT NULL COMMENT '0 NO 1 YES',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- 按钮表 --
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button` (
  `button_id` varchar(32) NOT NULL COMMENT '主键',
  `button_code` varchar(32) DEFAULT NULL COMMENT '按钮编码 规则:菜单编码_铵钮中文标识',
  `button_name` varchar(64) DEFAULT NULL COMMENT '按钮名',
  `button_icon` varchar(64) DEFAULT NULL COMMENT '按钮图标',
  `status` char(1) DEFAULT NULL COMMENT '0 NO 1 YES',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级菜单ID',
  PRIMARY KEY (`button_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='按钮表';

-- 角色表 --
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(32)  NOT NULL COMMENT '主键',
  `ROLE_NO` varchar(128) DEFAULT NULL COMMENT '角色编号',
  `ROLE_NAME` varchar(128) DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(512) DEFAULT NULL COMMENT '角色描述',
  `TYPE` varchar(64) DEFAULT NULL COMMENT '类型（1：超级管理员；2：访客；3：系统新增）',
  `status` char(1) DEFAULT NULL COMMENT '0 NO 1 YES',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 角色菜单表 -- 
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
  `ROLE_ID` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` varchar(50) DEFAULT NULL COMMENT '菜单ID',
  `MENU_TYPE` char(1) DEFAULT NULL COMMENT '菜单类型 1 菜单 2按钮',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- 用户角色 --
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `status` char(1) DEFAULT NULL COMMENT '0 NO 1 YES',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- 用户数据权限表 --
DROP TABLE IF EXISTS `sys_user_data_auth`;
CREATE TABLE `sys_user_data_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `auth_type` char(2) NOT NULL COMMENT '权限类型',
  `auth_type_name` varchar(30) NOT NULL COMMENT '权限类型名称',
  `auth_code` varchar(32) NOT NULL COMMENT '权限编码,存组织ID:org_id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户数据权限表';

-- 系统数据字典表 --
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_type` varchar(20) NOT NULL COMMENT '字典类型',
  `dic_type_name` char(40) NOT NULL COMMENT '字典类型名称',
  `dic_code` varchar(30) NOT NULL COMMENT '字典编码',
  `dic_value` varchar(255) NOT NULL COMMENT '字典编码对应的值',
  `dic_code_name` varchar(60) NOT NULL COMMENT '字典编码名称',
  `edit` char(1) NOT NULL COMMENT '是否允许编辑 Y 允许 N 不允许',
  `sort_code` varchar(3)  NULL COMMENT '排序编码',
  `status` char(1) DEFAULT NULL COMMENT '0 NO 1 YES',
   create_user  varchar(32) default NULL comment '创建人帐号',
   update_user  varchar(32) default NULL comment '更新人帐号',
   create_time   datetime not null default CURRENT_TIMESTAMP comment '创建日期',
   update_time   datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统数据字典表';



-- 登陆日志表--
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remote_ip` varchar(32)  NULL COMMENT '远程IP',
  `local_ip` varchar(32) NOT NULL COMMENT '本地IP',
  `login_name` varchar(32) NOT NULL COMMENT '登陆用户名 ',
  `session_id` varchar(32)  NULL COMMENT 'session_id',
   login_time  datetime NOT NULL COMMENT '登陆时间',
  `login_type` char(1)  NULL COMMENT '1 登陆 2登出',
  `login_result` varchar(200)  NULL COMMENT '登陆结果',
  `login_status` char(1)  NULL COMMENT '1 OK 2 FAIL',
   create_user  varchar(32) default NULL comment '创建人帐号',
   update_user  varchar(32) default NULL comment '更新人帐号',
   create_time   datetime not null default CURRENT_TIMESTAMP comment '创建日期',
   update_time   datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆日志表';


-- 实时会话表--
DROP TABLE IF EXISTS `login_session`;
CREATE TABLE `login_session` (
  `session_id` varchar(32)  NULL COMMENT 'session_id',
  `remote_ip` varchar(32)  NULL COMMENT '远程IP',
  `local_ip` varchar(32) NOT NULL COMMENT '本地IP',
  `login_name` varchar(32) NOT NULL COMMENT '登陆用户名 ',
   login_time  datetime NOT NULL COMMENT '登陆时间',
   session_size varchar(20) NULL COMMENT 'session占用空间大小',
   create_user  varchar(32) default NULL comment '创建人帐号',
   update_user  varchar(32) default NULL comment '更新人帐号',
   
   create_time   datetime not null default CURRENT_TIMESTAMP comment '创建日期',
   update_time   datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改日期',
   PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实时会话表';

-- 序列表格 -- 
DROP TABLE IF EXISTS `tb_sequence`;
CREATE TABLE `tb_sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` bigint(30) NOT NULL COMMENT '序列当前值',
  `increment` int(11) NOT NULL DEFAULT '1',
  `curr_date` date DEFAULT NULL COMMENT '当前日期',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 序列函数 --
DROP FUNCTION IF EXISTS `GET_SEQUENCE`; 

DELIMITER // 
CREATE FUNCTION `GET_SEQUENCE` (`typeName` varchar(30))  RETURNS int(11) 
 BEGIN 
	declare `seq` int(11); 
	declare `cnt` int(2); 
	select count(1) into `cnt` from `tb_sequence` where `name`=typeName;
	if `cnt` = 0 then 
		insert into `tb_sequence`(name,current_value,increment,curr_date) values(typeName,'0','1',SYSDATE());
	end if;

	select current_value into `seq` from tb_sequence where name=typeName;
	if `seq` = 999999999 THEN
		update tb_sequence set current_value=0 where name=typeName;
	ELSE
			update tb_sequence set current_value=`seq`+increment where name=typeName;
	end if;

	return `seq`;
	
END
//
DELIMITER ;

ALTER TABLE `sys_user`
ADD UNIQUE INDEX `loginname_idx` (`login_name`) USING BTREE ;

