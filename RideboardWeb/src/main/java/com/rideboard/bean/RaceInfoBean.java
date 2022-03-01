package com.rideboard.bean;

public class RaceInfoBean {
	private int raceId = 0;
	private String title = null;
	private String desc = null;
	private String location = null;
	private String type = null;
	private int stage = 0;
	private String dateStr = null;
	private double amount = 0.0;
	
	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
