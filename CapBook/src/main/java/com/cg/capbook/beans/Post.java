package com.cg.capbook.beans;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Post implements Comparable<Post>{
	@Id
	@GeneratedValue
	private int postId;
	@ManyToOne
	@JoinColumn(referencedColumnName = "emailId")
	private Person user;
	private String message;
	private Date time;
	private int likes;
	
	public Post() {}
	
	public Post(Person user, String message, Date time) {
		super();
		this.user = user;
		this.message = message;
		this.time = time;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}	
	public Person getUser() {
		return user;
	}
	public void setUser(Person user) {
		this.user = user;
	}
	@Override
	public int compareTo(Post post) {
		return post.getTime().compareTo(getTime());
	}
}