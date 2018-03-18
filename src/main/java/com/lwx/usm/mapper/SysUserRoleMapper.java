package com.lwx.usm.mapper;

import com.lwx.usm.model.SysUserRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SysUserRoleMapper extends Mapper<SysUserRole>, InsertListMapper<SysUserRole> {
}