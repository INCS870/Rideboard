package com.rideboard.data.dao;

import com.rideboard.data.DummyData;
import com.rideboard.data.model.RacerModel;

public class RacerDao {
	public RacerModel findRacerById(int key) {
		return DummyData.findRacerById(key);
	}
	public java.util.Collection<RacerModel> all() {
		return DummyData.listRacers();
	}
}
