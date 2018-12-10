
package com.cg.capbook.beans;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
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
	@Column(columnDefinition="BLOB")
	private byte[] profilePic;
	@OneToMany(mappedBy="person")
	private Map<Integer,Notifications> notifications;
	private String securityAnswer1;
	 private String securityAnswer2 ;


	 
	public Person() {

	}
	public Person(String emailId, String securityAnswer1, String securityAnswer2, String password,
			String confirmPassword) {
		super();
		this.emailId = emailId;
		this.securityAnswer1 = securityAnswer1;
		this.securityAnswer2 = securityAnswer2;
		this.password = password;
		this.confirmPassword = confirmPassword;
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
	

	

	public Person(String firstName, String lastName, String emailId, String dateOfBirth, String mobileNo,
			String gender,String securityAnswer1,String securityAnswer2, String password, String confirmPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.securityAnswer1=securityAnswer1;
		this.securityAnswer2=securityAnswer2;
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

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public String getSecurityAnswer1() {
		return securityAnswer1;
	}
	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}
	public String getSecurityAnswer2() {
		return securityAnswer2;
	}
	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}
	@Override
	public String toString() {
		return "Person [emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", mobileNo=" + mobileNo + ", gender=" + gender + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", maritalStatus=" + maritalStatus + ", education="
				+ education + ", address=" + address + ", profilePic=" + Arrays.toString(profilePic)
				 + ", securityAnswer1=" + securityAnswer1 + ", securityAnswer2="
				+ securityAnswer2 + "]";
	}
	
	
}