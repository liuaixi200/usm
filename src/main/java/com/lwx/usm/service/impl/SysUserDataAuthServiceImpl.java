package com.lwx.usm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lwx.usm.mapper.SysUserDataAuthMapper;
import com.lwx.usm.service.SysUserDataAuthService;
import com.lwx.usm.utils.BaseUtil;
import com.lwx.usm.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lwx.usm.model.SysUserDataAuth;
import tk.mybatis.mapper.entity.Example;

@Service("sysUserDataAuthServiceImpl")
public class SysUserDataAuthServiceImpl implements SysUserDataAuthService {
	
	@Resource
	private SysUserDataAuthMapper sysUserDataAuthMapper;

	private Log LOG = LogFactory.getLog(getClass());

	@Override
	public List<SysUserDataAuth> queryDataAuthListByUser(String userId, String authType) {

		SysUserDataAuth dataAuth = new SysUserDataAuth();
		dataAuth.setUserId(userId);
		dataAuth.setAuthType(authType);
		return sysUserDataAuthMapper.select(dataAuth);
	}

	@Override
	public List<SysUserDataAuth> queryDataAuthListByParam(SysUserDataAuth params) {
		// TODO Auto-generated method stub
		return sysUserDataAuthMapper.select(params);
	}

	@Override
	@Transactional
	public void saveUserDateAuth(SysUserDataAuth[] datas) {
		if(datas == null || datas.length == 0){
			return;
		} else {
		
			if(datas.length == 1 && datas[0].getAuthType().equals("del")){
				// 全部清除
				//删除
				SysUserDataAuth dataAuth = new SysUserDataAuth();
				dataAuth.setUserId(datas[0].getUserId());
				sysUserDataAuthMapper.delete(dataAuth);
				LOG.info("删除用户["+datas[0].getUserId()+"]数据权限");
			} else {

			}
			
		}
	}

}
