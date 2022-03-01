package com.rideboard.data.model;

import java.util.Date;

public class EventModel extends BaseModel {
	private int eventId = 0;
	private int userId = 0;
	private int raceId = 0;
	private int teamId = 0;
	private int sponsorId = 0;
	private String type = null;
	private String location = null;
	private Date fromDt = null;
	private Date toDt = null;	
	
	private String dateStr = null;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getEventId() {
		return eventId;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getSponsorId() {
		return sponsorId;
	}

	public void setSponsorId(int sponsorId) {
		this.sponsorId = sponsorId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getFromDt() {
		return fromDt;
	}

	public void setFromDt(Date fromDt) {
		this.fromDt = fromDt;
	}

	public Date getToDt() {
		return toDt;
	}

	public void setToDt(Date toDt) {
		this.toDt = toDt;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}
