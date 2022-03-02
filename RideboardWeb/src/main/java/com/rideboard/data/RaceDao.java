package com.rideboard.data;

import com.rideboard.bean.RaceInfoBean;
import com.rideboard.data.model.RaceModel;

public class RaceDao {
	public RaceModel findRaceById(int key) {
		return Database.findRaceById(key);
	}
	public java.util.Collection<RaceModel> all() {
		return Database.listRaces();
	}
	public RaceInfoBean parseInfoBean(RaceModel race) {
		RaceInfoBean bean = null;
		if(race != null) {
			bean = new RaceInfoBean();
			bean.setAmount(race.getAmount());
			bean.setDateStr(race.getDateStr());
			bean.setDesc(race.getDesc());
			bean.setLocation(race.getLocation());
			bean.setRaceId(race.getRaceId());
			bean.setStage(race.getStage());
			bean.setTitle(race.getTitle());
			bean.setType(race.getType());
		}
		return bean;
	}
}
