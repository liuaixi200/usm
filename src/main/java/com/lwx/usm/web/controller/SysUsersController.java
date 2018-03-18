/*
 * 文 件 名:  CloudUsersController.java
 * 版    权:  2008-2015 北京汇通金财科技有限公司 版权所有
 * 描    述:  <描述>
 * 修 改 人:  ZJJSCommon
 * 修改时间:  2015年5月18日
 * 修改内容:  <修改内容>
 */
package com.lwx.usm.web.controller;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.ApiResult;
import com.lwx.usm.dto.SimplePage;
import com.lwx.usm.dto.SysUserDto;
import com.lwx.usm.exception.BizException;
import com.lwx.usm.interceptor.ButtonIntercept;
import com.lwx.usm.model.SysUser;
import com.lwx.usm.model.SysUserPass;
import com.lwx.usm.service.SysUserPassService;
import com.lwx.usm.service.SysUserService;
import com.lwx.usm.utils.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author ZJJSCommon
 * @version [版本号, 2015年5月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/cuser")
public class SysUsersController extends BaseController {
	@Autowired
	private SysUserService cloudUserService;

	@Resource
	private SysUserPassService sysUserPassService;

	private Log log = LogFactory.getLog(getClass());

	@RequestMapping("toUserList")
	public String toUserListHtml() {
		return "system/userList";
	}

	/**
	 * <修改密码> <功能详细描述>
	 *
	 * @param info
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<String> modifyPwd(@RequestBody SysUserDto info, HttpServletRequest request,
									   HttpServletResponse response) throws IOException {
		logger.info("modifyPwd【 oldpwd==" + info.getPassword() + " newpwd==" + info.getNewPassword() + "】");
		SysUser _info = getCloudUserInfo();
		ApiResult<String> res = new ApiResult<>();
		if (_info == null) {
			res.setSuccess(false);
			res.setMsg("用户不存在，请重新登陆");
		} else {
			SysUserPass pass = sysUserPassService.getSysUserPassByUserName(_info.getUserName());
			String oldPassword = DigestUtils.md5Hex(_info.getUserName() + info.getPassword());
			if (!StringUtils.equals(oldPassword, pass.getPassword())) {
				res.setSuccess(false);
				res.setMsg("旧密码错误");

			} else {
				String newPassword = DigestUtils.md5Hex(_info.getUserName() + info.getNewPassword());
				//新旧一致性样验
				if (StringUtils.equals(newPassword, oldPassword)) {
					res.setSuccess(false);
					res.setMsg("新密码与旧密码不能相同");
				} else {
					//新密码强度校验
					String errors = sysUserPassService.validatePass(info.getNewPassword());
					if (StringUtils.isNotEmpty(errors)) {
						res.setSuccess(false);
						res.setMsg(errors);
					} else {

						//更新密码,将强制修改密码标识，修改为N
						SysUserPass newPass = new SysUserPass();
						newPass.setLoginName(_info.getUserName());
						newPass.setPassword(newPassword);
						newPass.setUpdatePassFlag("N");
						sysUserPassService.updateSysUserPass(newPass);
					}
				}

			}
		}


		return res;
	}

	/**
	 * <查询用户信息> <查询用户基本信息> 系统管理查询list----》用于页面展示
	 *
	 * @param zxm
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/queryUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryUserInfo(SysUser queryInfo) {
		logger.info("queryUserInfo【 系统管理查询list----》用于页面展示 】");
		SimplePage sp = getPage();
		SysUser quser = new SysUser();

		if (!isSuperUser()) {
			quser.setCreateUser(getCloudUserInfo().getUserName());
		}
		quser.setLoginName(queryInfo.getLoginName());
		queryInfo.setStatus(queryInfo.getStatus());

		Page<SysUser> page = cloudUserService.queryUserInfoLst(quser, sp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		List<SysUser> list = page.getResult();
		map.put("rows", list);
		return map;
	}

	/**
	 * <新增用户信息> <新增用户基本信息> 系统管理------》用户add
	 *
	 * @param zxm
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUserInfo(@RequestBody SysUserDto info, HttpServletRequest request,
										   HttpServletResponse response) throws IOException {
		logger.info("addUserInfo【 系统管理------》用户add 】");
		ApiResult<String> res = new ApiResult<String>();
		Map<String, Object> map = new HashMap<String, Object>();

		//判断账号重复
		SysUser user4 = new SysUser();
		user4.setLoginName(info.getLoginName());
		List<SysUser> ls = cloudUserService.queryUserInfo(user4);
		try {
			info.setType(Constant.SYSTEM_USER_GENERAL);
			if (ls.size() > 0) {
				map.put("status", "repeat");
				map.put("rows", "帐号已存在，请检查！");
			} else {
				SysUser user = getCloudUserInfo();
				info.setCreateUser(user.getUserName());
				info.setUpdateUser(user.getUserName());
				int num = cloudUserService.saveRegister(info);
				if (num == 1) {
					map.put("status", "ok");
					map.put("rows", "操作成功!");
				} else {
					map.put("status", "fail");
					map.put("rows", "操作失败!");
				}
			}
		} catch (BizException e) {
			res.setSuccess(false);
			res.setMsg(e.getMsg());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setSuccess(false);
			res.setMsg("系统错误，请联系管理员");
		}
		return map;
	}

	/**
	 * <查询用户信息> <查询用户基本信息> 暂时没用到
	 *
	 * @param info
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCurrentUser(HttpSession session) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser _info = (SysUser) session.getAttribute("cloudUser");

		if (_info != null) {
			SysUser newInfo = new SysUser();
			BeanUtils.copyProperties(_info, newInfo);
			map.put("cloudUser", newInfo);
			map.put("state", "succ");
		} else {
			map.put("state", "fail");
			map.put("msg", "id为空");
		}
		return map;
	}

	/**
	 * <查询用户信息> <查询用户基本信息> 暂时没用到
	 *
	 * @param info
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/findByPrimaryKey", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByPrimaryKey(@RequestBody String userId) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser _info = cloudUserService.findByPrimaryKey(userId);
		if (_info != null) {
			map.put("cloudUser", _info);
			map.put("state", "succ");
		} else {
			map.put("state", "fail");
			map.put("msg", "id为空");
		}
		return map;
	}

	/**
	 * <查询用户信息> <查询用户基本信息> 暂时没用到
	 *
	 * @param info
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetPassword(@RequestBody String userId) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser user = getCloudUserInfo();
		SysUser userInfo = cloudUserService.findByPrimaryKey(userId);
		if (null == userInfo) {
			throw new BizException("用户[" + userId + "]不存在");
		}
		//判断权限
		if (!isSuperUser()) {
			if (!StringUtils.equals(userInfo.getCreateUser(), user.getUserName())) {
				throw new BizException("没有权限重置该用户的密码");
			}
		}
		userInfo.setUpdateUser(user.getUserName());
		userInfo.setUpdateTime(new Date());

		SysUser _info = cloudUserService.resetPassword(userInfo);
		if (_info != null) {
			map.put("cloudUser", _info);
			map.put("state", "succ");
			map.put("msg", "该用户密码已经重置为'123456'");
		} else {
			map.put("state", "fail");
			map.put("msg", "id为空");
		}
		return map;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	@ButtonIntercept("101010_XGYH")
	public Map<String, Object> updateUser(@RequestBody SysUser userInfo) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser user = getCloudUserInfo();
		userInfo.setUpdateUser(user.getUserName());
		userInfo.setUpdateTime(new Date());
		SysUser _info = cloudUserService.updateUserSelective(userInfo);
		if (_info != null) {
			map.put("cloudUser", _info);
			map.put("state", "succ");
		} else {
			map.put("state", "fail");
			map.put("msg", "id为空");
		}
		return map;
	}

	@RequestMapping(value = "/freezeUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> freezeUser(@RequestBody String[] userIds) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String userId : userIds) {
			SysUser userInfo = new SysUser();
			userInfo.setUserId(userId);
			userInfo.setStatus("2");
			SysUser user = getCloudUserInfo();
			userInfo.setUpdateUser(user.getUserName());
			userInfo.setUpdateTime(new Date());
			cloudUserService.updateUserSelective(userInfo);
		}

		if (userIds.length > 0) {
			map.put("state", "succ");
			map.put("userIds", userIds);
		} else {
			map.put("state", "fail");
			map.put("msg", "id为空");
		}
		return map;
	}

	@RequestMapping(value = "/unFreezeUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> unFreezeUser(@RequestBody String[] userIds) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String userId : userIds) {
			SysUser userInfo = new SysUser();
			userInfo.setUserId(userId);
			userInfo.setStatus("1");
			SysUser user = getCloudUserInfo();
			userInfo.setUpdateUser(user.getUserName());
			userInfo.setUpdateTime(new Date());
			cloudUserService.updateUserSelective(userInfo);
		}

		if (userIds.length > 0) {
			map.put("state", "succ");
			map.put("userIds", userIds);
		} else {
			map.put("state", "fail");
			map.put("msg", "id为空");
		}
		return map;
	}


}
