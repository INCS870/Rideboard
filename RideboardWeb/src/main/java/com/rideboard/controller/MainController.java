package com.rideboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rideboard.bean.DashInfoBean;
import com.rideboard.bean.EventInfoBean;
import com.rideboard.bean.LoginBean;
import com.rideboard.bean.RaceInfoBean;
import com.rideboard.bean.RacerInfoBean;
import com.rideboard.bean.SponsorInfoBean;
import com.rideboard.bean.TeamInfoBean;
import com.rideboard.common.Utils;
import com.rideboard.data.dao.DataAccessManager;
import com.rideboard.data.model.CompanyModel;
import com.rideboard.data.model.EventModel;
import com.rideboard.data.model.LocationModel;
import com.rideboard.data.model.RaceModel;
import com.rideboard.data.model.RacerModel;
import com.rideboard.data.model.RequestModel;
import com.rideboard.data.model.RoleModel;
import com.rideboard.data.model.UserModel;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	DataAccessManager dataAccessManager;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profilePage(Model model) throws Exception {
		ModelAndView view = null;
		LoginBean bean = new LoginBean();
		Object userId = com.rideboard.common.Utils.getSession("security.userid");
//		Object roleId = com.rideboard.common.Utils.getSession("security.roleid");
//		Object userName = com.rideboard.common.Utils.getSession("security.user");
		
		logger.info("get profile ");
		logger.debug("get profile from user " + userId);
		
		if (userId == null) {
			model.addAttribute("error", "Invalid session or session is timeout. ");
			view = new ModelAndView("redirect:/login");
			view.setStatus(HttpStatus.UNAUTHORIZED);
			return view;
		}

		UserModel user = dataAccessManager.equalOne(UserModel.class, "userId", userId);
		if (user == null) {
			model.addAttribute("error", "Invalid session or session is timeout. ");
			view = new ModelAndView("redirect:/login");
			view.setStatus(HttpStatus.UNAUTHORIZED);
			return view;
		}
		logger.debug("get user object " + user);

		RoleModel roleModel = dataAccessManager.equalOne(RoleModel.class, "roleId", user.getRoleId());
		if (roleModel != null) {
			bean.setRoleName(roleModel.getRoleName());
		}
		
		logger.debug("get role object " + roleModel);

		if (roleModel == null) {
			model.addAttribute("error", "Invalid role for user " + user.getUserName() + ". ");
			view = new ModelAndView("redirect:/login");
			view.setStatus(HttpStatus.UNAUTHORIZED);
			return view;
		}
		String role = roleModel.getRoleCode();
		model.addAttribute("userProfileId", userId);
		model.addAttribute("userProfileRole", role);
		
		if (role.equals(com.rideboard.common.Constants.TYPE_RACE)) {
			RacerInfoBean entity = new RacerInfoBean();
			RacerModel racer = dataAccessManager.equalOne(RacerModel.class, "userId", userId);
			if (racer != null) {
				Utils.autoMap(racer, entity);
				logger.debug("Entity is " + entity);
				if (entity != null) {
					bean.setProfileName(entity.getName());
				}
			}
		} else if (role.equals(com.rideboard.common.Constants.TYPE_TEAM)) {
			TeamInfoBean entity = new TeamInfoBean();
			CompanyModel team = dataAccessManager.equalOne(CompanyModel.class, "userId", userId);
			if (team != null) {
				Utils.autoMap(team, entity);
				bean.setProfileName(entity.getName());
				logger.debug("Entity is " + entity);
			}
		}
		bean.setUserName(user.getUserName());
		bean.setEmail(user.getEmail());
		bean.setLastLoginDate(com.rideboard.common.Utils.formatDate(user.getLast_attempt_dt()));
		model.addAttribute("userObj", bean);
		
		dataAccessManager.closeSession();
		view = new ModelAndView("profile");
		return view;
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView updateProfile(Model model, @ModelAttribute("loginBean") LoginBean loginBean) throws Exception {
		Object userId = com.rideboard.common.Utils.getSession("security.userid");
		ModelAndView view = null;
		String currPwd = loginBean.getPassword();
		String newPwd = loginBean.getNewPassword();
		String newEmail = loginBean.getEmail();
		logger.info("loginBean " + loginBean);
		logger.debug("loginBean email " + newEmail);
		logger.debug("loginBean pwd " + currPwd);
		logger.debug("loginBean new " + newPwd);

		logger.info("get profile from user " + userId);

		if (userId == null) {
			model.addAttribute("error", "Invalid session or session is timeout. ");
			view = new ModelAndView("login");
			view.setStatus(HttpStatus.UNAUTHORIZED);
			return view;
		}

		UserModel user = dataAccessManager.equalOne(UserModel.class, "userId", userId);
		user.setLast_upd_dt(new java.util.Date());
		user.setEmail(newEmail);
		if(currPwd != null && !currPwd.isEmpty() && newPwd != null && !newPwd.isEmpty()) {
			String hashedCurrPwd = Utils.hash(currPwd);
			String hashedNewPwd = Utils.hash(newPwd);
			
			if(hashedCurrPwd.equals(user.getPassword())) {
				user.setPassword(hashedNewPwd);
			} else {
				model.addAttribute("error", "Current password does not match, update denied.");
				view = mainPage(model);
				view.setStatus(HttpStatus.resolve(500));
				return view;
			}
		}
		dataAccessManager.update(user);
		return mainPage(model);
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView mainPage(Model model) throws Exception {
		Object userId = com.rideboard.common.Utils.getSession("security.userid");
		Object roleId = com.rideboard.common.Utils.getSession("security.roleid");
		Object userName = com.rideboard.common.Utils.getSession("security.user");
		model.addAttribute("host_ip", java.net.InetAddress.getLocalHost());
		model.addAttribute("userProfileId", userId);
		model.addAttribute("userName", userName);

		if (userId == null) {
			model.addAttribute("error", "Invalid session or session is timeout. ");
			return new ModelAndView("login");
		}

		RoleModel roleModel = dataAccessManager.equalOne(RoleModel.class, "roleId", /*user.getRoleId()*/roleId);
		if (roleModel == null) {
			model.addAttribute("error", "Invalid role for user " + /*user.getUserName()*/userName + ". ");
			return new ModelAndView("login");
		}

		DashInfoBean dashInfoBean = new DashInfoBean();
		java.util.List<EventInfoBean> events = getEvents((Integer) userId);
		dashInfoBean.addEventInfos(events);
		dashInfoBean.addRaceInfos(getRaces());
		dashInfoBean.addSponsorInfos(getSponsors());

		logger.info("dash info events " + dashInfoBean.getEventCnt());
		logger.info("dash info sponsors " + dashInfoBean.getSponsorCnt());
		logger.info("dash info races " + dashInfoBean.getRaceCnt());
		
		if(dashInfoBean.getEventCnt() > 0) {
			dashInfoBean.setNextEvent(events.get(0).toString());
		}

		String role = roleModel.getRoleCode();
		model.addAttribute("userProfileRole", role);
		if (role.equals(com.rideboard.common.Constants.TYPE_RACE)) {
			RacerInfoBean entity = new RacerInfoBean();
			RacerModel racer = dataAccessManager.equalOne(RacerModel.class, "userId", userId);
			if (racer != null) {
				Utils.autoMap(racer, entity);
				logger.debug("Entity is " + entity);
				if (entity != null) {
					dashInfoBean.setWorldRank(((RacerInfoBean) entity).getRanking());
				}
			}
		} else if (role.equals(com.rideboard.common.Constants.TYPE_TEAM)) {
			TeamInfoBean entity = new TeamInfoBean();
			CompanyModel team = dataAccessManager.equalOne(CompanyModel.class, "userId", userId);
			if (team != null) {
				Utils.autoMap(team, entity);
				dashInfoBean.setWorldRank(((TeamInfoBean) entity).getRanking());
				logger.debug("Entity is " + entity);
			}
		}
		model.addAttribute("pageObj", dashInfoBean);

		dataAccessManager.closeSession();
		return new ModelAndView("main");
	}

	private java.util.List<EventInfoBean> getEvents(int userId) throws Exception {
		logger.info("getEvents start");
		java.util.List<EventInfoBean> infos = null;
		java.util.List<EventModel> list = dataAccessManager.equalMore(EventModel.class, "userId", userId);
		if (list != null && !list.isEmpty()) {
			logger.info("getEvents list " + list.size());
			infos = new java.util.ArrayList<EventInfoBean>(list.size());
			for (EventModel model : list) {
				if (model == null)
					continue;
				EventInfoBean bean = new EventInfoBean();
				Utils.autoMap(model, bean);
				RequestModel req = dataAccessManager.equalOne(RequestModel.class, "requestId", model.getRequestId());
				logger.info("getEvents req " + req);
				if (req != null)
					Utils.autoMap(req, bean);
				if (model.getRaceId() != null && model.getRaceId() > 0) {
					RaceModel race = dataAccessManager.equalOne(RaceModel.class, "raceId", model.getRaceId());
					logger.info("getEvents race " + race);
					if (race != null) {
						Utils.autoMap(race, bean);
						if (race.getLocationCode() != null) {
							java.util.List<LocationModel> locs = dataAccessManager.equalMore(LocationModel.class, "locationCode",
									race.getLocationCode());
							if (locs != null && !locs.isEmpty()) {
								for(LocationModel loc:locs) {
									if(loc.getCountryCode().equals(race.getCountryCode()))
									bean.setLocation(loc.getLocationName());
								}
							}
						}
						infos.add(bean);
					}
				}
			}
		}
		return infos;
	}

	private java.util.List<RaceInfoBean> getRaces() throws Exception {
		logger.info("getRaces start");
		java.util.List<RaceInfoBean> beans = null;
		java.util.List<RaceModel> models = dataAccessManager.list(RaceModel.class);
		if (models != null && !models.isEmpty()) {
			beans = new java.util.ArrayList<RaceInfoBean>(models.size());
			for (RaceModel model : models) {
				RaceInfoBean bean = new RaceInfoBean();
				Utils.autoMap(model, bean);
				if (model.getLocationCode() != null) {
					java.util.List<LocationModel> locs = dataAccessManager.equalMore(LocationModel.class, "locationCode",
							model.getLocationCode());
					if (locs != null && !locs.isEmpty()) {
						for(LocationModel loc:locs) {
							if(loc.getCountryCode().equals(model.getCountryCode()))
							bean.setLocation(loc.getLocationName());
						}
					}
				}
				beans.add(bean);
			}
		}
		return beans;
	}

	private java.util.List<SponsorInfoBean> getSponsors() throws Exception {
		logger.info("getSponsor start");
		java.util.List<SponsorInfoBean> beans = null;
		RoleModel roleModel = dataAccessManager.equalOne(RoleModel.class, "roleCode",
				com.rideboard.common.Constants.TYPE_SPONSOR);
		logger.debug("getSponsor roleModel " + roleModel);
		if (roleModel != null) {
			java.util.List<UserModel> users = dataAccessManager.equalMore(UserModel.class, "roleId",
					roleModel.getRoleId());
			logger.debug("getSponsor users " + users);
			if (users != null && !users.isEmpty()) {
				for (UserModel user : users) {
					java.util.List<CompanyModel> sponsors = dataAccessManager.equalMore(CompanyModel.class, "userId",
							user.getUserId());
					if (sponsors != null && !sponsors.isEmpty()) {
						beans = new java.util.ArrayList<SponsorInfoBean>(sponsors.size());
						for (CompanyModel model : sponsors) {
							SponsorInfoBean bean = new SponsorInfoBean();
							Utils.autoMap(model, bean);
							beans.add(bean);
						}
					}
				}
			}
		}
		return beans;
	}

}
