package com.rideboard.data;

import com.rideboard.bean.EventInfoBean;
import com.rideboard.data.model.EventModel;
import com.rideboard.data.model.RaceModel;
import com.rideboard.data.model.SponsorModel;
import com.rideboard.data.model.TeamModel;

public class EventDao {
	public EventModel findEventById(int key) {
		return Database.findEventById(key);
	}

	public java.util.Collection<EventModel> all() {
		return Database.listEvents();
	}

	public java.util.List<EventModel> findEventByUserId(int id) {
		return Database.findEventByUserId(id);
	}

	public EventInfoBean parseInfoBean(EventModel model) {
		EventInfoBean retVal = null;
		if (model != null) {
			retVal = new EventInfoBean();
			retVal.setEventId(model.getEventId());
			String type = model.getType();
			retVal.setType(type);
			if (type.equals(com.rideboard.common.Constants.TYPE_RACE)) {
				RaceModel race = Database.findRaceById(model.getRaceId());
				retVal.setTitle(race.getTitle());
				retVal.setLocation(race.getLocation());
				retVal.setDesc(race.getDesc());
				retVal.setDateStr(race.getDateStr());
			} else if (type.equals(com.rideboard.common.Constants.TYPE_TEAM)) {
				TeamModel team = Database.findTeamById(model.getTeamId());
				retVal.setTitle(team.getTeamTitle());
				retVal.setLocation("");
				retVal.setDateStr(com.rideboard.common.Utils.formatDate(team.getCre_dt()));
			} else if (type.equals(com.rideboard.common.Constants.TYPE_SPONSOR)) {
				SponsorModel sponsor = Database.findSponsorById(model.getSponsorId());
				retVal.setTitle(sponsor.getJobTitle());
				retVal.setLocation("");
				retVal.setDesc(sponsor.getJobDesc());
				retVal.setDateStr(sponsor.getRequestDateStr());
			}
		}
		return retVal;
	}
}