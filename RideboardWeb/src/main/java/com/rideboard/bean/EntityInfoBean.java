package com.rideboard.bean;

public class EntityInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = -7151933181522952434L;
	protected String companyName = null;
	protected String firstName = null;
	protected String midName = null;
	protected String lastName = null;
	protected String role = null;

	public EntityInfoBean(String role) {
		this.role = role;
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

	public String getName() {
		if (com.rideboard.common.Constants.TYPE_RACE.equals(role)) {
			return firstName + (midName == null ? "" : " " + midName) + " " + lastName;
		} else {
			return companyName;
		}
	}
}
