package com.lwx.usm.mapper;

import com.lwx.usm.model.SysUser;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SysUserMapper extends Mapper<SysUser>, InsertListMapper<SysUser> {
}