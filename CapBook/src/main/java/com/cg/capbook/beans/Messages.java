package com.cg.capbook.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Messages {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int messageId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "emailId")
    private Person sender;
    @ManyToOne
    @JoinColumn(referencedColumnName = "emailId")
    private Person reciever;
    private String message;
	public Messages(Person sender,Person reciever, String message) {
		super();
		this.sender = sender;
		this.reciever = reciever;
		this.message = message;
	}
	public Messages() {
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public Person getSender() {
		return sender;
	}
	public void setSender(Person sender) {
		this.sender = sender;
	}
	public Person getReciever() {
		return reciever;
	}
	public void setReciever(Person reciever) {
		this.reciever = reciever;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
