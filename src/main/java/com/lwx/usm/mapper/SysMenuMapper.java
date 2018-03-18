package com.lwx.usm.mapper;

import com.lwx.usm.model.SysMenu;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SysMenuMapper extends Mapper<SysMenu>, InsertListMapper<SysMenu> {
}