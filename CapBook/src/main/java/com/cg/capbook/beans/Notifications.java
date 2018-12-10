package com.cg.capbook.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notifications {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int notificationId;
	@ManyToOne
	private Person person;
	private String message;
	public Notifications() {
	}
	public Notifications( Person person, String message) {
		super();
		this.person = person;
		this.message = message;
	}
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
