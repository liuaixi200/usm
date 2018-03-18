package com.lwx.usm.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.lwx.usm.model.SysDictionary;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lwx.usm.service.SysDictionaryService;

@Controller
@RequestMapping("dic")
public class SysDictionaryController extends BaseController{
	
	@Resource
	private SysDictionaryService sysDictionaryService;

	@RequestMapping(value = "/getAllByDictype", method = RequestMethod.POST)
	@ResponseBody
	public List<SysDictionary> getAllByDictype(String dicType, @RequestParam(defaultValue="N")String all) throws IOException {

		List<SysDictionary> list = sysDictionaryService.queryByDicType(dicType);
		if(null != list){
			for(SysDictionary dic : list){
				dic.setCreateTime(null);
				dic.setUpdateTime(null);
				dic.setDicType(null);
				dic.setDicTypeName(null);
			}
			if(StringUtils.equals(all, "Y")){
				List<SysDictionary> alist = new ArrayList<>();
				SysDictionary sd = new SysDictionary();
				sd.setDicCode("-");
				sd.setDicValue("-");
				sd.setDicCodeName("全部");
				alist.add(sd);
				alist.addAll(list);
				return alist;
			}
		}
		return list;

	}
	
	@RequestMapping(value = "/getDictionaryValue2CodeName", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getDictionaryvalue2CodeName(String dicType) throws IOException {
		JSONObject objct = new JSONObject();
		List<SysDictionary> list = sysDictionaryService.queryByDicType(dicType);
		if(null != list){
			for(SysDictionary dic : list){
				objct.put(dic.getDicValue(), dic.getDicCodeName());
			}
		}
		return objct;

	}
}
