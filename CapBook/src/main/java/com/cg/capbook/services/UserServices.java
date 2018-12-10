package com.cg.capbook.services;

import java.util.List;

import com.cg.capbook.beans.Friends;
import com.cg.capbook.beans.Messages;
import com.cg.capbook.beans.Notifications;
import com.cg.capbook.beans.Person;
import com.cg.capbook.beans.Post;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.InvalidUserDetailsException;
import com.cg.capbook.exceptions.UserNotFoundException;


public interface UserServices {
	Person createUserAccount(Person user);
	
	Person getUserAccount(String emailId,String password) throws UserNotFoundException, IncorrectPasswordException;
	
	Person getUserAccountDetails(String emailId) throws UserNotFoundException;
	
	Person UpdatePersonalInfo(String emailId,String maritalStatus,String education,String address) throws UserNotFoundException;
	
	String friendRequest(String senderEmailId, String recieverEmailId) throws UserNotFoundException;
	
	Friends acceptFriendRequest(String senderEmailId, String recieverEmailId) throws UserNotFoundException;
	
	List<Person> friendList(String emailId) throws UserNotFoundException;
	
	List<Friends> friendRequests(String emailId) throws UserNotFoundException;
	
	List<Person> findFriends(String emailId) throws UserNotFoundException;
	
	Person insertProfilePic(byte[] profilePic) throws UserNotFoundException;
	
	byte[] fetchProfilePic();
	
	List<Notifications> getAllNotifications() throws UserNotFoundException;
	
	Person changePassword(String emailId, String password, String newPassword, String confirmNewPassword)
			throws UserNotFoundException, IncorrectPasswordException, InvalidUserDetailsException;
	
	Person forgotPassword(String emailId, String securityAnswer1, String securityAnswer2, String newPassword,
			String confirmNewPassword)
			throws UserNotFoundException, IncorrectPasswordException, InvalidUserDetailsException;
	
	List<Messages> getAllSentMessages() throws UserNotFoundException;
	
	List<Messages> getAllRecievedMessages() throws UserNotFoundException;
	
	Messages sendMessage(String recieverEmailId,String message) throws UserNotFoundException;
	
	Post sendPost( String message) throws UserNotFoundException;
	
	List<Post> getAllPosts() throws UserNotFoundException;
	
	List<Post> myPosts(String emailId) throws UserNotFoundException;
	
	void rejectFriendRequest(String senderEmailId) throws UserNotFoundException;

}
