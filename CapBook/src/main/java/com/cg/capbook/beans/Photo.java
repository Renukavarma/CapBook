/*package com.cg.capbook.beans;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="photoIDGenerator")
	@SequenceGenerator(name="photoIDGenerator", initialValue=1, allocationSize=0)
	private int photoID;
	private String location;
	private String photoDate, photoTime;
	@OneToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	@MapKey
	private Map<String, Person> taggedFriends;
	@ManyToOne
	private Album album;
	private int likesCount, dislikesCount;
	public int getPhotoID() {
		return photoID;
	}
	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhotoDate() {
		return photoDate;
	}
	public void setPhotoDate(String photoDate) {
		this.photoDate = photoDate;
	}
	public String getPhotoTime() {
		return photoTime;
	}
	public void setPhotoTime(String photoTime) {
		this.photoTime = photoTime;
	}
	public Map<String, Person> getTaggedFriends() {
		return taggedFriends;
	}
	public void setTaggedFriends(Map<String, Person> taggedFriends) {
		this.taggedFriends = taggedFriends;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public int getDislikesCount() {
		return dislikesCount;
	}
	public void setDislikesCount(int dislikesCount) {
		this.dislikesCount = dislikesCount;
	}
	
}*/