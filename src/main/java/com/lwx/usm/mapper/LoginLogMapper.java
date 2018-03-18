package com.lwx.usm.mapper;

import com.lwx.usm.model.LoginLog;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface LoginLogMapper extends Mapper<LoginLog>, InsertListMapper<LoginLog> {
}