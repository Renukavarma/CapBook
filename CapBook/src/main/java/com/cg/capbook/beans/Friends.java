package com.cg.capbook.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@IdClass(FriendsId.class)
public class Friends implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(referencedColumnName = "emailId")
    private Person sender;
    @Id
    @ManyToOne
    @JoinColumn(referencedColumnName = "emailId")
    private Person reciever;
    private boolean isAccepted;
    

	public Friends() {
		super();
	}

	
	public Friends(Person sender, Person reciever, boolean isAccepted) {
		super();
		this.sender = sender;
		this.reciever = reciever;
		this.isAccepted = isAccepted;
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
	public boolean isAccepted() {
		return isAccepted;
	}
	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}


	@Override
	public String toString() {
		return "Friends [sender=" + sender + ", reciever=" + reciever + ", isAccepted=" + isAccepted + "]";
	}
	
}
