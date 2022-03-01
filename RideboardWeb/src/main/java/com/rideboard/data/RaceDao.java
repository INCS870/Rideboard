package com.rideboard.data;

import com.rideboard.data.model.RaceModel;

public class RaceDao {
	public RaceModel findRaceById(int key) {
		return Database.findRaceById(key);
	}
	public java.util.Collection<RaceModel> all() {
		return Database.listRaces();
	}
}
