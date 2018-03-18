package com.lwx.usm.mapper;

import com.lwx.usm.model.TbSequence;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface TbSequenceMapper extends Mapper<TbSequence>, InsertListMapper<TbSequence> {
}