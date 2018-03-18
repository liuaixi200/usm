package com.lwx.usm.service;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.model.SysDictionary;

import java.util.List;

public interface SysDictionaryService {

	/**
     * 根据字典Type查询类型信息
     * @param dicType
     * @return
     */
    List<SysDictionary> queryByDicType(String dicType);
    
    public SysDictionary queryrById(Integer id);
    

	public int updateSysDictionary(SysDictionary dic);
	
	public SysDictionary querySysDictionaryNotNull(String dicType,String dicCode);
	
	public SysDictionary findById(Integer id);

	
	public Page<SysDictionary> queryListByPage(SysDictionary supplier,SimplePage sp);
	
}
