package com.rideboard.data;

import com.rideboard.data.model.RacerModel;

public class RacerDao {
	public RacerModel findRacerById(int key) {
		return Database.findRacerById(key);
	}
	public java.util.Collection<RacerModel> all() {
		return Database.listRacers();
	}
}
