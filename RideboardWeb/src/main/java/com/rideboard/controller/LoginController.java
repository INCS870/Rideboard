package com.rideboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rideboard.bean.DashInfoBean;
import com.rideboard.bean.EntityInfoBean;
import com.rideboard.bean.LoginBean;
import com.rideboard.bean.RacerInfoBean;
import com.rideboard.bean.TeamInfoBean;
import com.rideboard.common.Utils;
import com.rideboard.data.dao.DataAccessManager;
import com.rideboard.data.dao.EventDao;
import com.rideboard.data.dao.RaceDao;
import com.rideboard.data.dao.RacerDao;
import com.rideboard.data.dao.SponsorDao;
import com.rideboard.data.dao.TeamDao;
import com.rideboard.data.dao.UserDao;
import com.rideboard.data.model.EventModel;
import com.rideboard.data.model.RaceModel;
import com.rideboard.data.model.RacerModel;
import com.rideboard.data.model.RoleModel;
import com.rideboard.data.model.SponsorModel;
import com.rideboard.data.model.TeamModel;
import com.rideboard.data.model.UserModel;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	DataAccessManager dataAccessManager;

	UserDao userDao = new UserDao();
	TeamDao teamDao = new TeamDao();
	EventDao eventDao = new EventDao();
	RaceDao raceDao = new RaceDao();
	RacerDao racerDao = new RacerDao();
	SponsorDao sponsorDao = new SponsorDao();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());

		Object userObj = com.rideboard.common.Utils.getSession("security.userid");
		if (userObj != null) {
			model.addAttribute("msg", userObj);
			return mainPage(model);
		}

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("loginBean") LoginBean loginBean) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());

		if (loginBean != null) {
			String userName = loginBean.getUserName();
			String password = loginBean.getPassword();
			java.util.Date todate = new java.util.Date();
			boolean loginFailed = false;

			if (userName == null || "".equals(userName)) {
				model.addAttribute("error", "Invalid Login Inputs");
				return "login";
			}

			List<UserModel> users = dataAccessManager.equal(UserModel.class, "userName", userName);
			logger.debug("user list : " + users);
			UserModel user = users == null || users.isEmpty() ? null : users.get(0);
			logger.debug("found user ? " + user);
			if (user == null) {
				model.addAttribute("error", "Login failed, username or password is incorrect. ");
				loginFailed = true;
			}
			if (user != null && user.getStatus().equals("L")) {
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

					LoginBean bean = new LoginBean();

					List<RacerModel> racers = dataAccessManager.equal(RacerModel.class, "userId", user.getUserId());
					if (racers != null && !racers.isEmpty()) {
						RacerInfoBean entity = new RacerInfoBean();
						Utils.autoMap(racers.get(0), entity);
						logger.info("Racer Entity is " + entity);
						bean.setProfileName(entity.getName());
					}

					List<RoleModel> roles = dataAccessManager.equal(RoleModel.class, "roleId", user.getRoleId());
					if (roles != null && !roles.isEmpty()) {
						RoleModel role = roles.get(0);
						user.setRole(role.getRoleCode());
						bean.setRoleName(role.getRoleName());
					}

					bean.setUserName(user.getUserName());
					bean.setLastLoginDate(com.rideboard.common.Utils.formatDate(user.getLast_attempt_dt()));
					model.addAttribute("userObj", bean);

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
					dataAccessManager.update(user);
				}
				model.addAttribute("error", "Login failed, username or password is incorrect. ");
				return "login";
			} else {
				return mainPage(model);
			}
		}
		model.addAttribute("error", "Unknown Issue");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		com.rideboard.common.Utils.removeSession("security.userid");
		com.rideboard.common.Utils.removeSession("security.role");
		return "login";
	}

	public String mainPage(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		Object userId = com.rideboard.common.Utils.getSession("security.userid");
		Object role = com.rideboard.common.Utils.getSession("security.role");
		
		if (userId == null) return "login";
		
		model.addAttribute("userProfileId", userId);
		model.addAttribute("userProfileRole", role);

		DashInfoBean dashInfoBean = new DashInfoBean();
		java.util.List<EventModel> events = eventDao.findEventByUserId((Integer) userId);
		if (events != null) {
			for (EventModel event : events) {
				if (event != null)
					dashInfoBean.addEventInfo(eventDao.parseInfoBean(event));
			}
		}
		java.util.Collection<SponsorModel> sponsors = sponsorDao.all();
		if (sponsors != null) {
			for (SponsorModel sponsor : sponsors) {
				if (sponsor != null)
					dashInfoBean.addSponsorInfo(sponsorDao.parseInfoBean(sponsor));
			}
		}
		java.util.Collection<RaceModel> races = raceDao.all();
		if (races != null) {
			for (RaceModel race : races) {
				if (race != null)
					dashInfoBean.addRaceInfo(raceDao.parseInfoBean(race));
			}
		}
		
		
		if (role.equals(com.rideboard.common.Constants.TYPE_RACE)) {
			RacerInfoBean entity = new RacerInfoBean();
			List<RacerModel> racers = dataAccessManager.equal(RacerModel.class, "userId", userId);
			if (racers != null && !racers.isEmpty()) {
				Utils.autoMap(racers.get(0), entity);
				logger.info("Entity is " + entity);
			}			
			dashInfoBean.setWorldRank(((RacerInfoBean) entity).getRanking());
		} else if (role.equals(com.rideboard.common.Constants.TYPE_TEAM)) {
			TeamInfoBean entity = new TeamInfoBean();
			List<TeamModel> teams = dataAccessManager.equal(TeamModel.class, "userId", userId);
			if (teams != null && !teams.isEmpty()) {
				Utils.autoMap(teams.get(0), entity);
				logger.info("Entity is " + entity);
			}			
			dashInfoBean.setWorldRank(((TeamInfoBean) entity).getRanking());
		}
		
		model.addAttribute("pageObj", dashInfoBean);
		return "main";
	}
}
