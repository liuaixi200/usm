-- ----------------------------
-- Records of cloud_menu
-- ----------------------------
delete from sys_menu;
INSERT INTO `sys_menu` VALUES ('1010', '1010', '系统管理', null, null, '-1', 'Y', '1');
INSERT INTO `sys_menu` VALUES ('101010', '101010', '用户管理', 'cuser/toUserList', null, '1010', 'N', '1');
INSERT INTO `sys_menu` VALUES ('101020', '101020', '菜单管理', 'cmenu/toMenuList', null, '1010', 'N', '1');
INSERT INTO `sys_menu` VALUES ('101030', '101030', '角色管理', 'crole/toRoleList', null, '1010', 'N', '1');
INSERT INTO `sys_menu` VALUES ('101040', '101040', '登陆日志', 'toLoginLogList', null, '1010', 'N', '1');
INSERT INTO `sys_menu` VALUES ('101050', '101050', '登陆会话', 'toLoginSessionList', null, '1010', 'N', '1');

delete from sys_button;

## 用户管理
insert into sys_button(button_id,button_code,button_name,parent_id) values('101010_XZYH','101010_XZYH','新增','101010');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101010_XGYH','101010_XGYH','修改','101010');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101010_DJYH','101010_DJYH','冻结','101010');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101010_JDYH','101010_JDYH','解冻','101010');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101010_FPSJQX','101010_FPSJQX','分配数据权限','101010');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101010_MMCZ','101010_MMCZ','密码重置','101010');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101010_FPJS','101010_FPJS','分配角色','101010');

## 菜单管理


## 角色管理
insert into sys_button(button_id,button_code,button_name,parent_id) values('101030_XZJS','101030_XZJS','新增','101030');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101030_XGYS','101030_XGYS','修改','101030');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101030_SCJS','101030_SCJS','删除','101030');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101030_QYJS','101030_QYJS','启用','101030');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101030_TYJS','101030_TYJS','停用','101030');
insert into sys_button(button_id,button_code,button_name,parent_id) values('101030_FPCD','101030_FPCD','分配菜单','101030');


delete from sys_user;
delete from sys_user_pass ;

INSERT INTO `sys_user`(user_id,login_name,user_name,type,status) VALUES
  ('10000', 'admin', '超级管理员',  '1', '1');
insert into sys_user_pass values('admin','172eee54aa664e9dd0536b063796e54e','N',0,'N',null,0);

-- 交易状态 资金渠道 ---
delete from sys_dictionary where dic_type='PASSWORD_RULE';
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE','需要校验的密码组','PASSWORD_RULE_A','规则组A','PASSWORD_RULE_A','Y','Y','system','system');
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE','需要校验的密码组','PASSWORD_RULE_B','规则组B','PASSWORD_RULE_B','Y','Y','system','system');

delete from sys_dictionary where dic_type='PASSWORD_RULE_A';
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_A','密码规则','capitals','大字母至少出现一次','[A-Z]+','Y','Y','system','system');
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_A','密码规则','lowerCase','小写字母至少出现一次','[a-z]+','Y','Y','system','system');
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_A','密码规则','specail','特殊字符[!@#$%^&*()_+<>?:;"\'~]至少出现一次','[!@#$%^&*()_+<>?:;\\"\'~]+','Y','Y','system','system');
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_A','密码规则','digit','数字至少出现一次','\\d+','Y','Y','system','system');
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_A','密码规则','count','以上规则出现2次','2','Y','Y','system','system');

delete from sys_dictionary where dic_type='PASSWORD_RULE_B';
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_B','密码规则','length','密码至少需要6位长度','\\S{6}','Y','Y','system','system');
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_B','密码规则','count','以上规则出现1次','1','Y','Y','system','system');

delete from sys_dictionary where dic_type='PASSWORD_RULE_MSG';
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_MSG','密码规则提示信息','PASSWORD_RULE_A','A组提示信息','必须包含数字、大写字母,小写字母和特殊字符[!@#$%^&*()_+<>?:;"\'~]中的2种或2种以上','Y','Y','system','system');
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_RULE_MSG','密码规则提示信息','PASSWORD_RULE_B','B组提示信息','密码长度必须大于等于6位','Y','Y','system','system');

delete from sys_dictionary where dic_type='PASSWORD_UNLOCK';
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_UNLOCK','自动解锁时间','unlckTime','自动解锁时间，单位：秒','86400','Y','Y','system','system');

delete from sys_dictionary where dic_type='PASSWORD_ERRROS';
insert into sys_dictionary(dic_type,dic_type_name,dic_code,dic_code_name,dic_value,edit,status,create_user,update_user)
values('PASSWORD_ERRROS','密码错误','passwordErrors','密码错误次数','3','Y','Y','system','system');


