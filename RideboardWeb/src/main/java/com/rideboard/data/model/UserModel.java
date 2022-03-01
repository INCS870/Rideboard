package com.rideboard.data.model;

public class UserModel extends BaseModel {
	private int userId = 0;
	private int racerId = 0;
	private int teamId = 0;
	private int sponsorId = 0;
	private String user_name = null;
	private String password = null;
	private String photo_path = null;
	private int attempt_count = 0;
	private String status = null;
	private java.util.Date last_attempt_dt = null;
	
	private String role = null;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRacerId() {
		return racerId;
	}

	public void setRacerId(int racerId) {
		this.racerId = racerId;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public int getAttempt_count() {
		return attempt_count;
	}

	public void setAttempt_count(int attempt_count) {
		this.attempt_count = attempt_count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.util.Date getLast_attempt_dt() {
		return last_attempt_dt;
	}

	public void setLast_attempt_dt(java.util.Date last_attempt_dt) {
		this.last_attempt_dt = last_attempt_dt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
