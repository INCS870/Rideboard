package com.rideboard.bean;

import com.rideboard.common.Constants;

public class RacerInfoBean extends EntityInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = -4985530574283248251L;
	private int racerId = 0;
	private String firstName = null;
	private String midName = null;
	private String lastName = null;
	private String sin = null;
	private int ranking = 0;

	public RacerInfoBean() {
		super(Constants.TYPE_RACE);
	}

	public int getRacerId() {
		return racerId;
	}

	public void setRacerId(int racerId) {
		this.racerId = racerId;
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
