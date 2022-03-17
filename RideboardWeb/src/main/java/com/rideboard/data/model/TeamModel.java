package com.rideboard.data.model;

public class TeamModel extends BaseModel {
	private int teamId = 0;
	private int userId = 0;
	private String teamTitle = null;
	private String bn_number = null;
	private int ranking = 0;
	private double budget = 0.0;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTeamTitle() {
		return teamTitle;
	}
	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public String getBn_number() {
		return bn_number;
	}
	public void setBn_number(String bn_number) {
		this.bn_number = bn_number;
	}
}
