package com.lwx.usm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.enums.EnumBoolean;
import com.lwx.usm.exception.BizException;
import com.lwx.usm.mapper.SysDictionaryMapper;
import com.lwx.usm.model.SysDictionary;
import com.lwx.usm.service.SysDictionaryService;
import com.lwx.usm.utils.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("sysDictionaryService")
public class SysDictionanryServiceImpl implements SysDictionaryService {

	@Autowired
	private SysDictionaryMapper sysDictionaryDao;
	
	
	@Override
	public List<SysDictionary> queryByDicType(String dicType) {
		SysDictionary sysDictionary = new SysDictionary();
		sysDictionary.setDicType(dicType);
		return sysDictionaryDao.select(sysDictionary);
	}

	@Override
	public SysDictionary queryrById(Integer id) {
		// TODO Auto-generated method stub
		return sysDictionaryDao.selectByPrimaryKey(id);
	}



	@Override
	public int updateSysDictionary(SysDictionary dic) {
		//查看是否允许编辑
		SysDictionary old = findById(dic.getId());
		if(null == old){
			throw new BizException("数据字典[id="+dic.getId()+"]不存在");
		}
		if(EnumBoolean.NO.getCode().equals(old.getEdit())){
			throw new BizException("该数据项不能被修改");
		}
		int count = sysDictionaryDao.updateByPrimaryKeySelective(dic);

		return count;
	}



	@Override
	public SysDictionary findById(Integer id) {
		// TODO Auto-generated method stub
		return sysDictionaryDao.selectByPrimaryKey(id);
	}


	@Override
	public Page<SysDictionary> queryListByPage(SysDictionary dic, SimplePage sp) {
		Page<SysDictionary> page = PageHelper.startPage(sp.getPageNo(), sp.getPageSize());
		//Example example = new Example(SysDictionary.class);
		//Example.Criteria criteria = example.createCriteria();
		//criteria.andEqualTo(dic);
		sysDictionaryDao.select(dic);
		return page;
	}

	@Override
	public SysDictionary querySysDictionaryNotNull(String dicType, String dicCode) {
		Example example = new Example(SysDictionary.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("dic_type",dicType);
		criteria.andEqualTo("dic_code",dicCode);
		List<SysDictionary> list = sysDictionaryDao.selectByExample(criteria);
		if(null == list || list.size() == 0){
			throw new BizException("数据字典dicType=["+dicType+"]dicCode=["+dicCode+"]不存在");
		}
		return list.get(0);
	}


}
