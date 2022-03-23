package com.rideboard.data.model;

public class BaseModel {
	private java.util.Date cre_dt = null;
	private String cre_user = null;
	private java.util.Date last_upd_dt = null;
	private String last_upd_user = null;
	
	public java.util.Date getCre_dt() {
		return cre_dt;
	}
	public void setCre_dt(java.util.Date cre_dt) {
		this.cre_dt = cre_dt;
	}
	public String getCre_user() {
		return cre_user;
	}
	public void setCre_user(String cre_user) {
		this.cre_user = cre_user;
	}
	public java.util.Date getLast_upd_dt() {
		return last_upd_dt;
	}
	public void setLast_upd_dt(java.util.Date last_upd_dt) {
		this.last_upd_dt = last_upd_dt;
	}
	public String getLast_upd_user() {
		return last_upd_user;
	}
	public void setLast_upd_user(String last_upd_user) {
		this.last_upd_user = last_upd_user;
	}
}
