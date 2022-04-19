package com.rideboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rideboard.bean.LoginBean;
import com.rideboard.data.dao.DataAccessManager;
import com.rideboard.data.model.UserModel;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	DataAccessManager dataAccessManager;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView init(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());

		Object userObj = com.rideboard.common.Utils.getSession("security.userid");
		if (userObj != null) {
			model.addAttribute("msg", userObj);
			new ModelAndView("redirect:/main");
		}

		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Model model, @ModelAttribute("loginBean") LoginBean loginBean) throws Exception {
		ModelAndView modelView = null;
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		
		if (loginBean != null) {
			String userName = loginBean.getUserName();
			String password = loginBean.getPassword();
			java.util.Date todate = new java.util.Date();
			boolean loginFailed = false;

			if (userName == null || "".equals(userName)) {
				model.addAttribute("error", "Invalid Login Inputs");
				return new ModelAndView("login");
			}

			UserModel user = dataAccessManager.equalOne(UserModel.class, "userName", userName);
			logger.info("found user ? " + user);
			if (user == null) {
				model.addAttribute("error", "Login failed, username or password is incorrect. ");
				return new ModelAndView("login");
			}
			if (user != null && user.getStatus().equals("L")) {
				logger.info("found user but too many attempted.");
				model.addAttribute("error", "Too many login attempted for this user, account is locked.");
				loginFailed = true;
			}

			if (user != null && !loginFailed) {
				String userPwd = user.getPassword();
				String hashPwd = "";
				if (password != null && !password.trim().equals("")) {
					hashPwd = com.rideboard.common.Utils.hash(password);
				}

				if ((userPwd == null ? "" : userPwd).equals(hashPwd)) {					
					user.setLast_attempt_dt(todate);
					user.setAttempt_count(0);
					dataAccessManager.update(user);

					com.rideboard.common.Utils.addSession("security.userid", user.getUserId());
					com.rideboard.common.Utils.addSession("security.roleid", user.getRoleId());
					com.rideboard.common.Utils.addSession("security.role", user.getRole());
					com.rideboard.common.Utils.addSession("security.user", user.getUserName());
					
					loginFailed = false;
				} else {
					loginFailed = true;
				}
			}

			if (loginFailed) {
				if (user != null) {
					int cnt = user.getAttempt_count() + 1;
					user.setLast_attempt_dt(todate);
					user.setAttempt_count(cnt);
					if (cnt >= 5)
						user.setStatus("L");
					else model.addAttribute("error", "Login failed, username or password is incorrect. ");
					dataAccessManager.update(user);
				}
				modelView = new ModelAndView("login");
			} else {
				modelView = new ModelAndView("redirect:/main");
			}
			
			dataAccessManager.closeSession();
			return modelView;
		}
		model.addAttribute("error", "Unknown Issue");
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		com.rideboard.common.Utils.removeSession("security.userid");
		com.rideboard.common.Utils.removeSession("security.role");
		return new ModelAndView("login");
	}
}
