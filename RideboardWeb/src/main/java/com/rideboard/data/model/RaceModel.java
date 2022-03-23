package com.rideboard.data.model;

import java.util.Date;

public class RaceModel extends BaseModel {
	private int raceId = 0;
	private String title = null;
	private String section = null;
	private String locationCode = null;
	private String type = null;
	private int companyId = 0;
	private int round = 0;
	private Date fromDt = null;
	private Date toDt = null;	
	private double prizeFirst = 0.0;
	private double prizeSecond = 0.0;
	private double prizeThird = 0.0;
	private double prizeGeneral = 0.0;
	
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
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
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
	public double getPrizeFirst() {
		return prizeFirst;
	}
	public void setPrizeFirst(double prizeFirst) {
		this.prizeFirst = prizeFirst;
	}
	public double getPrizeSecond() {
		return prizeSecond;
	}
	public void setPrizeSecond(double prizeSecond) {
		this.prizeSecond = prizeSecond;
	}
	public double getPrizeThird() {
		return prizeThird;
	}
	public void setPrizeThird(double prizeThird) {
		this.prizeThird = prizeThird;
	}
	public double getPrizeGeneral() {
		return prizeGeneral;
	}
	public void setPrizeGeneral(double prizeGeneral) {
		this.prizeGeneral = prizeGeneral;
	}
}
