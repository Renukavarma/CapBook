package com.cg.capbook.beans;

public class Album {
	private String name ;
	private String date ;
	private String description ;
	private String time ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Album [name=" + name + ", date=" + date + ", description=" + description + ", time=" + time + "]";
	}
}
