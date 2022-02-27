package com.rideboard.bean;

public class DashInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = -8678969261004645283L;
	
	private int eventCnt = 0;
	private int raceCnt = 0;
	private int sponsorCnt = 0;
	private int worldRank = 0;
	private String nextEvent = "";
	private java.util.List<EventInfoBean> eventList = null;

	public void addEventInfo(EventInfoBean eventInfo) {
		if(eventList==null) eventList = new java.util.ArrayList<EventInfoBean>();
		eventList.add(eventInfo);
	}
	public void removeEventInfo(EventInfoBean eventInfo) {
		if(eventList==null) return;
		eventList.remove(eventInfo);
	}	
	public java.util.List<EventInfoBean> getEventList() {
		return eventList;
	}
	public void setEventList(java.util.List<EventInfoBean> eventList) {
		this.eventList = eventList;
	}
	public int getEventCnt() {
		return eventCnt;
	}
	public void setEventCnt(int eventCnt) {
		this.eventCnt = eventCnt;
	}
	public int getSponsorCnt() {
		return sponsorCnt;
	}
	public int getRaceCnt() {
		return raceCnt;
	}
	public void setRaceCnt(int raceCnt) {
		this.raceCnt = raceCnt;
	}
	public void setSponsorCnt(int sponsorCnt) {
		this.sponsorCnt = sponsorCnt;
	}
	public int getWorldRank() {
		return worldRank;
	}
	public void setWorldRank(int worldRank) {
		this.worldRank = worldRank;
	}
	public String getNextEvent() {
		return nextEvent;
	}
	public void setNextEvent(String nextEvent) {
		this.nextEvent = nextEvent;
	}
}
