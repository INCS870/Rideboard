package com.rideboard.bean;

public class SponsorInfoBean extends EntityInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = 2286697265248878005L;
	private int sponsorId = 0;
	private String companyName = null;
	private String jobTitle = null;
	private String jobDesc = null;
	private String requestDateStr = null;
	private String bnNumber = null;
	private double amount = 0.0;
	
	public SponsorInfoBean() {
		super(com.rideboard.common.Constants.TYPE_SPONSOR);
	}
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
	public String getRequestDateStr() {
		return requestDateStr;
	}
	public void setRequestDateStr(String requestDateStr) {
		this.requestDateStr = requestDateStr;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getBnNumber() {
		return bnNumber;
	}
	public void setBnNumber(String bnNumber) {
		this.bnNumber = bnNumber;
	}
}
