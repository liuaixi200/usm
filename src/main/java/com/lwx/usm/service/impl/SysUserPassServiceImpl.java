package com.lwx.usm.service.impl;

import com.lwx.usm.mapper.SysUserPassMapper;
import com.lwx.usm.model.SysDictionary;
import com.lwx.usm.model.SysUserPass;
import com.lwx.usm.service.SysDictionaryService;
import com.lwx.usm.service.SysUserPassService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("sysUserPassServiceImpl")
public class SysUserPassServiceImpl implements SysUserPassService{
	
	@Resource
	private SysUserPassMapper sysUserPassMapper;
	
	@Resource
	private SysDictionaryService sysDictionaryService;

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public SysUserPass getSysUserPassByUserName(String userName) {
		
		return sysUserPassMapper.selectByPrimaryKey(userName);
	}

	@Override
	public void updateSysUserPass(SysUserPass pass) {
		
		sysUserPassMapper.updateByPrimaryKeySelective(pass);
	}

	@Override
	public int addSysUserPass(SysUserPass pass) {
		//
		pass.setErrors(0);
		pass.setLockFlag("N");
		pass.setLockTime(null);
		pass.setLoginCount(0);
		pass.setUpdatePassFlag("Y");
		sysUserPassMapper.insertSelective(pass);
		return 1;
	}

	@Override
	public String validatePass(String password) {

		List<SysDictionary> groupList = sysDictionaryService.queryByDicType("PASSWORD_RULE");
		if(null != groupList){
			for(SysDictionary group : groupList){
				String rule = group.getDicValue();
				int oks = 0;
				int currentOks = 0;
				List<SysDictionary> list = sysDictionaryService.queryByDicType(rule);
				if(null != list){
					for(SysDictionary dic : list){
						if(StringUtils.equals(dic.getDicCode(), "count")){
							oks = Integer.parseInt(dic.getDicValue());
						} else {
							Pattern pattern = Pattern.compile(dic.getDicValue());
							log.info("当前规则为："+dic.getDicValue());
							Matcher matcher = pattern.matcher(password);
							if(matcher.find()){
								currentOks += 1;
								log.info("规则["+dic.getDicCode()+"]校验成功");
							} else {
								log.info("规则["+dic.getDicCode()+"]校验失败");
							}
						}
					}

					if(currentOks < oks){
						SysDictionary sd = sysDictionaryService.querySysDictionaryNotNull("PASSWORD_RULE_MSG",rule);
						return sd.getDicValue();

					}
				} 
			}
		}

		return null;
	}
	
	public static void main(String[] args) {

		Pattern pattern = Pattern.compile("\\S{6}");
		Matcher m = pattern.matcher("Ab");
		System.out.println(m.find());
	}

}
