package com.rideboard.bean;

public class DashInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = -8678969261004645283L;
	
	private int eventCnt = 0;
	private int raceCnt = 0;
	private int sponsorCnt = 0;
	private int worldRank = 0;
	private String nextEvent = "N/A";
	private java.util.List<EventInfoBean> eventList = null;
	private java.util.List<RaceInfoBean> raceList = null;
	private java.util.List<SponsorInfoBean> sponsorList = null;

	public void addEventInfo(EventInfoBean eventInfo) {
		if(eventList==null) eventList = new java.util.ArrayList<EventInfoBean>();
		eventList.add(eventInfo);
		eventCnt++;
		if(eventCnt==1) nextEvent = eventInfo.getTitle();
	}
	public void removeEventInfo(EventInfoBean eventInfo) {
		if(eventList==null) return;
		eventList.remove(eventInfo);
		eventCnt--;
		if(eventCnt==0) nextEvent = "N/A";
	}	
	public void addRaceInfo(RaceInfoBean raceInfo) {
		if(raceList==null) raceList = new java.util.ArrayList<RaceInfoBean>();
		raceList.add(raceInfo);
		raceCnt++;
	}
	public void removeRaceInfo(RaceInfoBean raceInfo) {
		if(raceList==null) return;
		raceList.remove(raceInfo);
		raceCnt--;
	}	
	public void addSponsorInfo(SponsorInfoBean sponsorInfo) {
		if(sponsorList==null) sponsorList = new java.util.ArrayList<SponsorInfoBean>();
		sponsorList.add(sponsorInfo);
		sponsorCnt++;
	}
	public void Sponsor(SponsorInfoBean sponsorInfo) {
		if(sponsorList==null) return;
		sponsorList.remove(sponsorInfo);
		sponsorCnt--;
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
	public boolean hasEvents() {
		return eventCnt > 0;
	}
	public java.util.List<EventInfoBean> getEventList() {
		return eventList;
	}
	public java.util.List<RaceInfoBean> getRaceList() {
		return raceList;
	}
	public java.util.List<SponsorInfoBean> getSponsorList() {
		return sponsorList;
	}
}
