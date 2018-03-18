package com.lwx.usm.mapper;

import com.lwx.usm.model.LoginSession;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface LoginSessionMapper extends Mapper<LoginSession>, InsertListMapper<LoginSession> {
}