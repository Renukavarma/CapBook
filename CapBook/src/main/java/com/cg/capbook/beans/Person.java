package com.cg.capbook.beans;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	 private String emailId ;
	 private String  firstName ;
	 private String lastName ;
	 private String dataOfBirth ;
	 private String phoneNumber ;
	 private String gender ;
	 private String password ;
	 private String confirmPassword ;
	 @Embedded
	 private PersonalInfo personalInfo;
	 
	 
	public Person() {

	}

	public Person(String firstName, String lastName, String emailId, String dataOfBirth, String phoneNumber,
			String gender, String password, String confirmPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dataOfBirth = dataOfBirth;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDataOfBirth() {
		return dataOfBirth;
	}
	public void setDataOfBirth(String dataOfBirth) {
		this.dataOfBirth = dataOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", dataOfBirth="
				+ dataOfBirth + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + "]";
	}
}
