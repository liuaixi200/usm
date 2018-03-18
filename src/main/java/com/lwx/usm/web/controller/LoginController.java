package com.lwx.usm.web.controller;

import com.github.pagehelper.Page;
import com.lwx.usm.dto.SysUserDto;
import com.lwx.usm.enums.ConstantRoleMenuType;
import com.lwx.usm.enums.ConstantUserStatus;
import com.lwx.usm.mapper.SysButtonMapper;
import com.lwx.usm.mapper.SysRoleMenuMapper;
import com.lwx.usm.model.*;
import com.lwx.usm.service.*;
import com.lwx.usm.utils.BaseUtil;
import com.lwx.usm.utils.CSRFTokenManager;
import com.lwx.usm.utils.Constant;
import com.lwx.usm.utils.ListUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class LoginController extends BaseController {

	@Autowired
	private SysUserService cloudUserService;

	@Autowired
	private SysRoleService cloudRoleService;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysButtonMapper sysButtonMapper;

	@Resource
	private SysUserDataAuthService sysUserDataAuthService;
	@Resource
	private LoginLogService loginLogService;
	@Resource
	private LoginSessionService loginSessionService;

	@Resource
	private SysDictionaryService sysDictionaryService;

	@Resource
	private SysUserPassService sysUserPassService;

	@RequestMapping(value = {"/", "/login", "/index"}, method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		//如果已登陆
		SysUser user = getCloudUserInfo();
		if (null == user) {
			return "login";
		}
		//进入强制修改密码页面
		SysUserPass pass = sysUserPassService.getSysUserPassByUserName(user.getLoginName());
		if (StringUtils.equals(pass.getUpdatePassFlag(), "Y")) {
			return "system/updatePassword";
		}
		return "index";
	}

	/**
	 * <用户登陆>
	 * <用户登陆验证>
	 *
	 * @return 用户信息
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "login/loginValite", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginValite(@RequestBody SysUserDto info, HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("loginValite【 username==" + info.getLoginName() + "  pwd==" + info.getPassword() + "】");
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();

    /*    session.setMaxInactiveInterval(-1); //设置session时间 -1 为永不失效
		if(info.getTaken() == null || !info.getTaken().equals(session.getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString())){
        	return map;
        }*/
		//构建登陆日志
		LoginLog log = getLoginLog();
		log.setLoginType(Constant.LOGIN_TYPE_LOGIN);
		if (info.getLoginName() == null) {
			map.put("state", "fail");
			map.put("msg", "用户名不能为空");
			return map;
		}

		if (info.getPassword() == null) {
			map.put("state", "fail");
			map.put("msg", "密码不能为空");
			return map;
		}
		SysUser _info = new SysUser();
		_info.setLoginName(info.getLoginName());
		List<SysUser> list = cloudUserService.queryLoginValite(_info);
		int count = list.size();
		logger.info("【count==" + count + "】");
		log.setLoginName(info.getLoginName());

		if (count > 1) {
			map.put("state", "fail");
			map.put("msg", "该用户名存在多条记录，请联系管理员");
			logger.info("该用户名存在多条记录，请联系管理员");
			log.setLoginStatus(Constant.LOGIN_STATUS_FAIL);
			log.setLoginResult("该用户名存在多条记录，请联系管理员");
		} else if (count == 0) {
			map.put("state", "fail");
			map.put("msg", "该用户名不存在");
			logger.info("该用户名不存在");
			log.setLoginStatus(Constant.LOGIN_STATUS_FAIL);
			log.setLoginResult("用户不存在");

		} else {
			_info = list.get(0);
			SysUserPass newPass = new SysUserPass();
			newPass.setLoginName(info.getLoginName());
			if (!ConstantUserStatus.STATUS_AVAILABLE.equals(_info.getStatus())) {
				map.put("state", "fail");
				map.put("msg", "当前用户不可用");
				log.setLoginStatus(Constant.LOGIN_STATUS_FAIL);
				log.setLoginResult("当前用户已被禁用");
			} else {
				SysUserPass pass = sysUserPassService.getSysUserPassByUserName(info.getLoginName());
				//是否能登陆  用户被锁定
				if (StringUtils.equals(pass.getLockFlag(), "Y")) {

					Date lockDate = pass.getLockTime();
					long diffTime = System.currentTimeMillis() - lockDate.getTime();
					Map<String, Object> params = new HashMap<>();
					params.put("dicType", "PASSWORD_UNLOCK");
					params.put("dicCode", "unlckTime");
					SysDictionary sd = sysDictionaryService.querySysDictionaryNotNull("PASSWORD_UNLOCK", "unlckTime");

					long unlockTime = Long.parseLong(sd.getDicValue());
					if (diffTime > unlockTime) {
						//释放，锁定次数
						pass.setErrors(0);
						pass.setLockFlag("N");
					} else {
						map.put("state", "fail");
						map.put("msg", "用户被锁定中，不能登陆");
						log.setLoginStatus(Constant.LOGIN_STATUS_FAIL);
						log.setLoginResult("用户被锁定中，不能登陆");
					}

				} else {
					//未被锁定
					String _pwd = pass.getPassword();
					String pwdResult = DigestUtils.md5Hex(info.getLoginName() + info.getPassword());
					if (_pwd.equals(pwdResult)) {

						map.put("result", list.get(0));
						map.put("state", "succ");
						map.put("msg", "用户验证成功");
						// 设置用户
						SysUser user = list.get(0);
						session.setAttribute("cloudUser", user);

						//设置权限列表并设置到session
						List<SysRole> roleList = cloudRoleService.queryUserRole(list.get(0).getUserId());
						session.setAttribute("roleList", roleList);

						// 数据权限 按钮权限
						if (Constant.SYSTEM_USER_ADMIN.equals(user.getType())) {
							// 管理员存数据权限
							//所有菜单
							//所有按钮
							List<SysButton> blist = sysButtonMapper.selectAll();
							session.setAttribute(Constant.SESSION_FUNC_BTN, ListUtil.getSlist(blist, "buttonId"));
						} else {
							List<SysUserDataAuth> clist = sysUserDataAuthService.queryDataAuthListByUser(user.getUserId(), Constant.SYSTEM_DATA_AUTH_CITY);
							List<SysUserDataAuth> plist = sysUserDataAuthService.queryDataAuthListByUser(user.getUserId(), Constant.SYSTEM_DATA_AUTH_PROJECT);
							session.setAttribute(Constant.SESSION_DATA_AUTH_CITY, ListUtil.getSlist(clist, "authCode"));
							session.setAttribute(Constant.SESSION_DATA_AUTH_PROJECT, ListUtil.getSlist(plist, "authCode"));
							SysRoleMenu sysRoleMenu = new SysRoleMenu();
							Example.Builder build = new Example.Builder(SysRoleMenu.class).where(WeekendSqls.<SysRoleMenu>custom()
							.andEqualTo(SysRoleMenu::getMenuType, ConstantRoleMenuType.BUTTON));
							if (null != roleList && roleList.size() > 0) {
								build.andWhere(WeekendSqls.<SysRoleMenu>custom().andIn(SysRoleMenu::getRoleId, roleList));
								List<SysRoleMenu> rmList = sysRoleMenuMapper.selectByExample(build.build());
								session.setAttribute(Constant.SESSION_FUNC_BTN, ListUtil.getSlist(rmList, "menuId"));
							}

						}
						pass.setLoginCount(pass.getLoginCount() + 1);
						pass.setErrors(0);
						logger.info("用户IDSESSION==【" + ((SysUser) session.getAttribute("cloudUser")).getUserId() + "】");
						logger.info("用户登陆验证【用户名/密码】成功");
						log.setLoginStatus(Constant.LOGIN_STATUS_OK);
						log.setLoginResult("登陆成功");
					} else {
						pass.setErrors(pass.getErrors() + 1);
						//是否需要锁定

						Map<String, Object> params = new HashMap<>();
						params.put("dicType", "PASSWORD_ERRROS");
						params.put("dicCode", "passwordErrors");
						SysDictionary sd2 = sysDictionaryService.querySysDictionaryNotNull("PASSWORD_ERRROS", "passwordErrors");

						int error = Integer.parseInt(sd2.getDicValue());
						if (pass.getErrors() >= error) {
							pass.setLockFlag("Y");
							pass.setLockTime(new Date());
						}

						map.put("state", "fail");
						map.put("msg", "您输入的用户名和密码不匹配，请重新输入");
						logger.info("用户登陆验证：用户名/密码错误");
						log.setLoginStatus(Constant.LOGIN_STATUS_FAIL);
						log.setLoginResult("您输入的用户名和密码不匹配，请重新输入");
					}
				}

				//更新PASS
				sysUserPassService.updateSysUserPass(pass);
			}

		}

		//写入日志
		loginLogService.addLoginLog(log);

		return map;
	}

	/**
	 * <验证用户是否登陆>
	 * <验证当前用户是否登陆，用户信息从session中获取>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "login/quitLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quitLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();

		//构建登陆日志
		LoginLog log = getLoginLog();
		log.setLoginType(Constant.LOGIN_TYPE_OUT);
		log.setLoginStatus(Constant.LOGIN_STATUS_OK);

		log.setLoginName(getCloudUserInfo().getLoginName());
		session.removeAttribute("cloudUser");
		session.invalidate();
		log.setLoginResult("登出成功");
		map.put("state", "succ");
		loginLogService.addLoginLog(log);
		return map;
	}

	@RequestMapping(value = "login/safety", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> safety(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		String token = CSRFTokenManager.getTokenForSession(session);
		map.put("token", token);
		return map;
	}

	@RequestMapping("toLoginLogList")
	public String toLoginLogList() {
		return "system/loginLog";
	}


	@RequestMapping(value = "/getLoginLogList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLoginLogList(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<>();

		//开始时间，结束时间;
		LoginLog loginLog = new LoginLog();
		loginLog.setLoginName(req.getParameter("loginName"));
		logger.info("查询登陆日志入参:" + loginLog);
		Page<LoginLog> page = loginLogService.queryListByPage(loginLog, getPage());

		res.put("rows", page.getResult());
		res.put("total", page.getTotal());
		return res;
	}

	@RequestMapping("toLoginSessionList")
	public String toLoginSessionList() {
		return "system/loginSession";
	}


	@RequestMapping(value = "/getLoginSessionList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLoginSessionList(HttpServletRequest req) {
		Map<String, Object> res = new HashMap<>();
		LoginSession loginSession = new LoginSession();
		//开始时间，结束时间

		//params.put("startTime", req.getParameter("startTime"));
		//params.put("endTime", req.getParameter("endTime"));
		loginSession.setLoginName(req.getParameter("loginName"));
		logger.info("查询登陆会话入参:" + loginSession);
		Page<LoginSession> page = loginSessionService.queryListByPage(loginSession, getPage());

		res.put("rows", page.getResult());
		res.put("total", page.getTotal());
		return res;
	}

}
