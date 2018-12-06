package com.cg.capbook.beans;

import java.io.Serializable;

public class FriendsId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String sender;
	private String reciever;
	
	public FriendsId() {
		super();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public FriendsId(String sender, String reciever) {
		super();
		this.sender = sender;
		this.reciever = reciever;
	}

	

}
