package com.rideboard.bean;

import java.util.Date;

import com.rideboard.common.Utils;

public class EventInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = 2286697265248878004L;
	private int eventId = 0;
	private String type = null;
	private String title = null;
	private String desc = null;
	private String dateStr = null;
	private String location = null;
	private Date fromDt = null;
	private Date toDt = null;
	
	public int getEventId() {
		return eventId;
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
	public String getDateStr() {
		return dateStr==null?Utils.formatDate(fromDt):dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
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
}
