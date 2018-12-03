package com.cg.capbook.beans;

import javax.persistence.Embeddable;

@Embeddable
public class PersonalInfo {
	private String maritalStatus ;
	private String  education ;
	private String address ;
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
