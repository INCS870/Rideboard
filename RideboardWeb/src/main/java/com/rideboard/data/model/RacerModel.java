package com.rideboard.data.model;

public class RacerModel extends BaseModel {
	private int racerId = 0;
	private int userId = 0;
	private String firstName = null;
	private String midName = null;
	private String lastName = null;
	private String sin = null;
	private int ranking = 0;
	
	public int getRacerId() {
		return racerId;
	}
	public void setRacerId(int racerId) {
		this.racerId = racerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMidName() {
		return midName;
	}
	public void setMidName(String midName) {
		this.midName = midName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
}
