package com.rideboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rideboard.bean.DashInfoBean;
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
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	DataAccessManager dataAccessManager;

	UserDao userDao = new UserDao();
	TeamDao teamDao = new TeamDao();
	EventDao eventDao = new EventDao();
	RaceDao raceDao = new RaceDao();
	RacerDao racerDao = new RacerDao();
	SponsorDao sponsorDao = new SponsorDao();

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView mainPage(Model model) throws Exception {
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		Object userId = com.rideboard.common.Utils.getSession("security.userid");
		
		if (userId == null) {
			model.addAttribute("error", "Invalid session or session is timeout. ");
			return new ModelAndView("login");
		}
				
		model.addAttribute("userProfileId", userId);
		
		List<UserModel> users = dataAccessManager.equal(UserModel.class, "userId", userId);
		UserModel user = users == null || users.isEmpty() ? null : users.get(0);
		
		if (user == null) {
			model.addAttribute("error", "Invalid session or session is timeout. ");
			return new ModelAndView("login");
		}
		
		LoginBean bean = new LoginBean();
		
		RoleModel roleModel = null;
		List<RoleModel> roles = dataAccessManager.equal(RoleModel.class, "roleId", user.getRoleId());
		if (roles != null && !roles.isEmpty()) {
			roleModel = roles.get(0);
			bean.setRoleName(roleModel.getRoleName());
		}

		bean.setUserName(user.getUserName());
		bean.setLastLoginDate(com.rideboard.common.Utils.formatDate(user.getLast_attempt_dt()));

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

		String role = roleModel.getRoleCode();
		model.addAttribute("userProfileRole", role);
		if (role.equals(com.rideboard.common.Constants.TYPE_RACE)) {
			RacerInfoBean entity = new RacerInfoBean();
			List<RacerModel> racers = dataAccessManager.equal(RacerModel.class, "userId", userId);
			if (racers != null && !racers.isEmpty()) {
				Utils.autoMap(racers.get(0), entity);
				logger.info("Entity is " + entity);
			}
			bean.setProfileName(entity.getName());
			dashInfoBean.setWorldRank(((RacerInfoBean) entity).getRanking());
		} else if (role.equals(com.rideboard.common.Constants.TYPE_TEAM)) {
			TeamInfoBean entity = new TeamInfoBean();
			List<TeamModel> teams = dataAccessManager.equal(TeamModel.class, "userId", userId);
			if (teams != null && !teams.isEmpty()) {
				Utils.autoMap(teams.get(0), entity);
				logger.info("Entity is " + entity);
			}
			bean.setProfileName(entity.getName());
			dashInfoBean.setWorldRank(((TeamInfoBean) entity).getRanking());
		}

		model.addAttribute("userObj", bean);
		model.addAttribute("pageObj", dashInfoBean);
		return new ModelAndView("main");
	}
}
