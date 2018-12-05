package com.cg.capbook.beans;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Person {
	@Id
	 private String emailId ;
	 private String  firstName ;
	 private String lastName ;
	 private String dateOfBirth ;
	 private String mobileNo ;
	 private String gender ;
	 private String password ;
	 private String confirmPassword ;
	 private String maritalStatus;
		private String  education ;
		private String address ;
	 @ManyToOne
		@JoinColumn(name="friend_id")
		private Person friend;
		@OneToMany(mappedBy="friend")
		private List<Person> friendList;
	 
	 
	public Person() {

	}

	public Person(String firstName, String lastName, String emailId, String dateOfBirth, String mobileNo,
			String gender, String password, String confirmPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.password = password;
		this.confirmPassword = confirmPassword;
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
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", dateOfBirth="
				+ dateOfBirth + ", mobileNo=" + mobileNo + ", gender=" + gender + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + "]";
	}

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
