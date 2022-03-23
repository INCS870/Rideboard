package com.rideboard.data.model;

public class RaceResultModel {
	private int raceResultId = 0;
	private int raceId = 0;
	private int userId = 0;
	private int place = 0;
	
	public int getRaceResultId() {
		return raceResultId;
	}
	public void setRaceResultId(int raceResultId) {
		this.raceResultId = raceResultId;
	}
	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
}
