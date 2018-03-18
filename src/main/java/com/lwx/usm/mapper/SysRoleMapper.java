package com.lwx.usm.mapper;

import com.lwx.usm.model.SysRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SysRoleMapper extends Mapper<SysRole>, InsertListMapper<SysRole> {
}