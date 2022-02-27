package com.rideboard.data.model;

public class UserModel extends BaseModel {
	private int userid = 0;
	private String user_name = null;
	private String first_name = null;
	private String mid_name = null;
	private String last_name = null;
	private String team_name = null;
	private String company_name = null;
	private String sin_number = null;
	private String bn_number = null;
	private String password = null;
	private String photo_path = null;
	private int attempt_count = 0;
	private String status = null;
	private java.util.Date last_attempt_dt = null;
	
	private String role = null;
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserid() {
		return userid;
	}

	public String getSin_number() {
		return sin_number;
	}

	public void setSin_number(String sin_number) {
		this.sin_number = sin_number;
	}

	public String getBn_number() {
		return bn_number;
	}

	public void setBn_number(String bn_number) {
		this.bn_number = bn_number;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMid_name() {
		return mid_name;
	}

	public void setMid_name(String mid_name) {
		this.mid_name = mid_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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
	
	public String getName() {
		if("racer".equals(role)) {
			return getFirst_name() + (mid_name == null ? "" : " " + mid_name) + " " + getLast_name();
		} else if("team".equals(role)) {
			return getTeam_name();
		} else if("sponsor".equals(role)) {
			return getCompany_name();
		}
		return "";
	}
}
