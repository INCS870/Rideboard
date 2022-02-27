package com.rideboard.bean;

public class TeamInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = 2286697265248878006L;
	private int teamId = 0;
	private String teamTitle = null;
	private java.util.List<String> typeList = null;
	private int ranking = 0;
	private double budget = 0.0;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamTitle() {
		return teamTitle;
	}
	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}
	public java.util.List<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(java.util.List<String> typeList) {
		this.typeList = typeList;
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
}
