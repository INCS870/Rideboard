package com.rideboard.data;

import com.rideboard.data.model.TeamModel;

public class TeamDao {
	public TeamModel findTeamById(int key) {
		return Database.findTeamById(key);
	}
	public java.util.Collection<TeamModel> all() {
		return Database.listTeams();
	}
}
