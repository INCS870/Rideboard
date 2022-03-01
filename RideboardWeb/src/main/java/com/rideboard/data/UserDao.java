package com.rideboard.data;

import java.util.Set;
import java.util.Map.Entry;

import com.rideboard.bean.EntityInfoBean;
import com.rideboard.bean.RacerInfoBean;
import com.rideboard.bean.SponsorInfoBean;
import com.rideboard.bean.TeamInfoBean;
import com.rideboard.data.model.RacerModel;
import com.rideboard.data.model.SponsorModel;
import com.rideboard.data.model.TeamModel;
import com.rideboard.data.model.UserModel;

public class UserDao {
	public UserModel findUserById(int key) {
		return Database.findUserById(key);
	}

	public UserModel findUserByUserName(String userName) {
		return Database.findUserByUserName(userName);
	}

	public void updateUser(UserModel user) {
		Database.updateUserById(user.getUserId(), user);
	}

	public String getAllUsers() {
		String retVal = "";
		java.util.Collection<UserModel> list = Database.listUsers();
		for (UserModel user : list) {
			retVal += " | [" + user.getUserId() + "] " + user.getUser_name() + ", " + user.getStatus() + ", "
					+ user.getAttempt_count();
		}
		return retVal;
	}

	public EntityInfoBean getEntity(UserModel user) {
		if(user != null) {
			if(user.getRole().equals(com.rideboard.common.Constants.TYPE_RACE)) {
				RacerModel racer = Database.findRacerById(user.getRacerId());
				RacerInfoBean retVal = new RacerInfoBean();
				retVal.setRacerId(racer.getRacerId());
				retVal.setFirst_name(racer.getFirst_name());
				retVal.setMid_name(racer.getMid_name());
				retVal.setLast_name(racer.getLast_name());
				retVal.setRanking(racer.getRanking());
				retVal.setSin_number(racer.getSin_number());
				return retVal;
			}
			else if(user.getRole().equals(com.rideboard.common.Constants.TYPE_SPONSOR)) {
				SponsorModel sponsor = Database.findSponsorById(user.getSponsorId());
				SponsorInfoBean retVal = new SponsorInfoBean();
				retVal.setSponsorId(sponsor.getSponsorId());
				retVal.setCompanyName(sponsor.getCompanyName());
				retVal.setJobTitle(sponsor.getJobTitle());
				retVal.setAmount(sponsor.getAmount());
				retVal.setJobDesc(sponsor.getJobDesc());
				retVal.setRequestDateStr(sponsor.getRequestDateStr());
				retVal.setBnNumber(sponsor.getBn_number());
				return retVal;
			}
			else if(user.getRole().equals(com.rideboard.common.Constants.TYPE_TEAM)) {
				TeamModel team = Database.findTeamById(user.getTeamId());
				TeamInfoBean retVal = new TeamInfoBean();
				retVal.setTeamId(team.getTeamId());
				retVal.setBudget(team.getBudget());
				retVal.setRanking(team.getRanking());
				retVal.setTeamTitle(team.getTeamTitle());
				retVal.setBnNumber(team.getBn_number());
				return retVal;
			}
		}
		return null;
	}
}
