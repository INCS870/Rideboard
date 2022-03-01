package com.rideboard.data.model;

public class RacerModel extends BaseModel {
	private int racerId = 0;
	private String first_name = null;
	private String mid_name = null;
	private String last_name = null;
	private String sin_number = null;
	private int ranking = 0;

	public int getRacerId() {
		return racerId;
	}

	public void setRacerId(int racerId) {
		this.racerId = racerId;
	}

	public String getFirst_name() {
		return first_name;
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

	public String getSin_number() {
		return sin_number;
	}

	public void setSin_number(String sin_number) {
		this.sin_number = sin_number;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
}
