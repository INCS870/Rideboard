package com.rideboard.bean;

public class EntityInfoBean implements java.io.Serializable {
	private static final long serialVersionUID = -7151933181522952434L;
	protected String teamName = null;
	protected String companyName = null;
	protected String firstName = null;
	protected String midName = null;
	protected String lastName = null;
	protected String role = null;
	
	public EntityInfoBean(String role) {
		this.role = role;
	}

	public String getName() {
		if ("racer".equals(role)) {
			return firstName + (midName == null ? "" : " " + midName) + " " + lastName;
		} else if ("team".equals(role)) {
			return teamName;
		} else if ("sponsor".equals(role)) {
			return companyName;
		}
		return "";
	}
}
