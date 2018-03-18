package com.lwx.usm.mapper;

import com.lwx.usm.model.SysUserPass;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SysUserPassMapper extends Mapper<SysUserPass>, InsertListMapper<SysUserPass> {
}