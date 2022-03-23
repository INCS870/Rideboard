package com.rideboard.bean;

import java.util.Date;

import com.rideboard.common.Utils;

public class RaceInfoBean implements java.io.Serializable{
	private static final long serialVersionUID = -4726010725024254075L;
	
	private int raceId = 0;
	private String title = null;
	private String desc = null;
	private String location = null;
	private String type = null;
	private int stage = 0;
	private Date fromDt = null;
	private Date toDt = null;	
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
		return dateStr == null ? Utils.formatDate(fromDt) : dateStr;
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
}
