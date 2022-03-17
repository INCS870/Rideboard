package com.rideboard.data.dao;

import com.rideboard.bean.EntityInfoBean;
import com.rideboard.bean.RacerInfoBean;
import com.rideboard.bean.SponsorInfoBean;
import com.rideboard.bean.TeamInfoBean;
import com.rideboard.common.Utils;
import com.rideboard.data.DummyData;
import com.rideboard.data.model.RacerModel;
import com.rideboard.data.model.SponsorModel;
import com.rideboard.data.model.TeamModel;
import com.rideboard.data.model.UserModel;

public class UserDao {
	public UserModel findUserById(int key) {
		return DummyData.findUserById(key);
	}

	public UserModel findUserByUserName(String userName) {
		return DummyData.findUserByUserName(userName);
	}

	public void updateUser(UserModel user) {
		DummyData.updateUserById(user.getUserId(), user);
	}

	public String getAllUsers() {
		String retVal = "";
		java.util.Collection<UserModel> list = DummyData.listUsers();
		for (UserModel user : list) {
			retVal += " | [" + user.getUserId() + "] " + user.getUserName() + ", " + user.getStatus() + ", "
					+ user.getAttempt_count();
		}
		return retVal;
	}

	public EntityInfoBean getEntity(UserModel user) throws Exception {
		if(user != null) {
			if(user.getRole().equals(com.rideboard.common.Constants.TYPE_RACE)) {
				RacerModel racer = DummyData.findRacerByUserId(user.getUserId());
				RacerInfoBean retVal = new RacerInfoBean();
				Utils.autoMap(racer, retVal);
				return retVal;
			}
			else if(user.getRole().equals(com.rideboard.common.Constants.TYPE_SPONSOR)) {
				SponsorModel sponsor = DummyData.findSponsorByUserId(user.getUserId());
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
				TeamModel team = DummyData.findTeamByUserId(user.getUserId());
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
