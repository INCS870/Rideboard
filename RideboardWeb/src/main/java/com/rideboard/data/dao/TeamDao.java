package com.rideboard.data.dao;

import com.rideboard.data.DummyData;
import com.rideboard.data.model.TeamModel;

public class TeamDao {
	public TeamModel findTeamById(int key) {
		return DummyData.findTeamById(key);
	}
	public java.util.Collection<TeamModel> all() {
		return DummyData.listTeams();
	}
}
