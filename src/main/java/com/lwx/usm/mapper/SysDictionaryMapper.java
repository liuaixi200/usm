package com.lwx.usm.mapper;

import com.lwx.usm.model.SysDictionary;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface SysDictionaryMapper extends Mapper<SysDictionary>, InsertListMapper<SysDictionary> {
}