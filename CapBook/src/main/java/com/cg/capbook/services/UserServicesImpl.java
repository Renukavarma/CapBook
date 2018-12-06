package com.cg.capbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.capbook.beans.Friends;
import com.cg.capbook.beans.Person;
import com.cg.capbook.daoservices.FriendsDAO;
import com.cg.capbook.daoservices.UserDAO;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.UserNotFoundException;
@Component("userServices")
public class UserServicesImpl implements UserServices {
	@Autowired
private UserDAO userDao;
	@Autowired
	private FriendsDAO friendsDAO;
	
	@Override
	public Person createUserAccount(Person user) {
		return userDao.save(user);
	}

	@Override
	public Person getUserAccount(String emailId,String password) throws UserNotFoundException, IncorrectPasswordException{
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!"));
		if(user.getPassword().equals(password))
		return user;
		else
			throw new IncorrectPasswordException("Password Incorrect!!Please try with new password");
	}

	@Override
	public Person UpdatePersonalInfo(String emailId,String maritalStatus,String education,String address) throws UserNotFoundException {
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		user.setMaritalStatus(maritalStatus);
		user.setAddress(address);
		user.setEducation(education);
		return userDao.save(user);
	}

	@Override
	public Person getUserAccountDetails(String emailId) throws UserNotFoundException {
		return userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
	}
	@Override
	public Friends friendRequest(String senderEmailId, String recieverEmailId) throws UserNotFoundException {
		Person sender=userDao.findById(senderEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Person reciever=userDao.findById(recieverEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Friends friends=new Friends(sender, reciever, false); 
		friendsDAO.save(friends);
		return friends;
	}

	@Override
	public Friends acceptFriendRequest(String senderEmailId, String recieverEmailId) throws UserNotFoundException {
		Person reciever=userDao.findById(recieverEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Person sender =userDao.findById(senderEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Friends friends=new Friends(sender, reciever, true); 
		friendsDAO.save(friends);
		return friends;
		
	}
	@Override
	public List<Person> friendList(String emailId) throws UserNotFoundException {
		Person person =userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		List<Person> friends = friendsDAO.findAcceptedFriends(person);
		 friends.addAll(friendsDAO.findRequestedFriends(person));
		return friends;
	}

	@Override
	public List<Friends> friendRequests(String emailId) throws UserNotFoundException {
		Person person =userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!"));  
		List<Friends> friendrequests = friendsDAO.findFriendRequests(person);
		return friendrequests;
	}

	
}
