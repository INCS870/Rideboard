package com.rideboard.data.model;

public class SponsorModel extends BaseModel {
	private int sponsorId = 0;
	private String companyName = null;
	private String bn_number = null;
	private String jobTitle = null;
	private String jobDesc = null;
	private java.util.Date requestDate = null;
	private double amount = 0.0;

	private String requestDateStr = null;
	
	public int getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(int sponsorId) {
		this.sponsorId = sponsorId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public java.util.Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(java.util.Date requestDate) {
		this.requestDate = requestDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRequestDateStr() {
		return requestDateStr;
	}
	public void setRequestDateStr(String requestDateStr) {
		this.requestDateStr = requestDateStr;
	}
	public String getBn_number() {
		return bn_number;
	}
	public void setBn_number(String bn_number) {
		this.bn_number = bn_number;
	}
}
