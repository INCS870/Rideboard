package com.rideboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rideboard.bean.DashInfoBean;
import com.rideboard.bean.LoginBean;
import com.rideboard.data.UserDao;
import com.rideboard.data.model.UserModel;

@Controller
public class LoginController {
	UserDao userDao = new UserDao();
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model) throws Exception {
		model.addAttribute("msg", "Please Enter Your Login Details");
		model.addAttribute("host_ip",  java.net.InetAddress.getLocalHost());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(Model model, @ModelAttribute("loginBean") LoginBean loginBean) throws Exception {
		model.addAttribute("host_ip",  java.net.InetAddress.getLocalHost());
		if (loginBean != null) {
			String userName = loginBean.getUserName();
			String password = loginBean.getPassword();
			
			if(userName == null || "".equals(userName)) {
				model.addAttribute("error", "Invalid Login Inputs");
				return "login";
			}
			UserModel user = userDao.findUserByUserName(userName);
			if(user == null) {
				model.addAttribute("error", "Login failed, username or password is incorrect. ");
				return "login";
			}
			if(user.getStatus().equals("L")) {
				model.addAttribute("error", "Too many login attempted for this user, account is locked.");
				return "login";
			}
						
			java.util.Date todate = new java.util.Date();
			String userPwd = user.getPassword();
			String hashPwd = "";
			if(password != null && !password.trim().equals("")) {
				hashPwd = com.rideboard.common.Utils.hash(password);
			}
			
			if ((userPwd==null?"":userPwd).equals(hashPwd)) {
				com.rideboard.common.Utils.addSession("security.userid", loginBean.getUserName());
				
				user.setLast_attempt_dt(todate);
				user.setAttempt_count(0);
				userDao.updateUser(user);
				
				LoginBean bean = new LoginBean();
				bean.setUserName(user.getName());
				bean.setRoleName(user.getRole());
				bean.setLastLoginDate(com.rideboard.common.Utils.formatDate(user.getLast_attempt_dt()));
				model.addAttribute("userObj", bean);
				return "success";
			} else {
				int cnt = user.getAttempt_count() + 1;
				user.setLast_attempt_dt(todate);
				user.setAttempt_count(cnt);
				if(cnt >=5) user.setStatus("L");
				userDao.updateUser(user);

				model.addAttribute("error", "Login failed, username or password is incorrect. ");
				return "login";
			}
		}
		model.addAttribute("error", "Unknown Issue");
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String submit(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		com.rideboard.common.Utils.removeSession("security.userid");
		return "login";
	}
	
	@RequestMapping(value="/next", method = RequestMethod.POST)
	public String nextPage(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		
		DashInfoBean dashInfoBean = new DashInfoBean();
		dashInfoBean.setEventCnt(10);
		dashInfoBean.setNextEvent("Yokohama F1 2022");
		dashInfoBean.setSponsorCnt(8);
		dashInfoBean.setRaceCnt(6);
		dashInfoBean.setWorldRank(5);
		model.addAttribute("pageObj", dashInfoBean);
		return "main";
	}
}
