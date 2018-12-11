package com.cg.capbook.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.capbook.beans.Friends;
import com.cg.capbook.beans.Messages;
import com.cg.capbook.beans.Notifications;
import com.cg.capbook.beans.Person;
import com.cg.capbook.beans.Post;
import com.cg.capbook.daoservices.FriendsDAO;
import com.cg.capbook.daoservices.MessageDAO;
import com.cg.capbook.daoservices.NotificationDAO;
import com.cg.capbook.daoservices.PostDAO;
import com.cg.capbook.daoservices.UserDAO;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.InvalidUserDetailsException;
import com.cg.capbook.exceptions.UserNotFoundException;
@Component("userServices")
public class UserServicesImpl implements UserServices {
	@Autowired
	private UserDAO userDao;
	@Autowired
	private FriendsDAO friendsDAO;
	@Autowired
	private NotificationDAO notificationDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private PostDAO postDAO;
	static String sessionEmailId=null;
	
	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
		}

		return hash.toString();
	}


	@Override
	public Person createUserAccount(Person user) {
		if(user.getPassword().compareTo(user.getConfirmPassword())==0) {
		user.setPassword(generateHash(user.getPassword()));
		return userDao.save(user);
		}
		else
			return null;
	}

	@Override
	public Person getUserAccount(String emailId,String password) throws UserNotFoundException, IncorrectPasswordException{
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!"));
		if(user.getPassword().compareTo(generateHash(password))==0) {
			sessionEmailId=user.getEmailId();
			return user;
		}
		else
			throw new IncorrectPasswordException("Password Incorrect!!Please try with new password");
	}

	@Override
	public Person UpdatePersonalInfo(String emailId,String maritalStatus,String education,String address) throws UserNotFoundException {
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		user.setMaritalStatus(maritalStatus);
		user.setAddress(address);
		user.setEducation(education);
		List<Person>friends =friendList(emailId);	
		for (Person friend : friends) {
			Notifications notifications=new Notifications(friend, user.getFirstName()+" has updated his profile");
			notificationDAO.save(notifications);
		}
		return userDao.save(user);
	}

	@Override
	public Person getUserAccountDetails(String emailId) throws UserNotFoundException {
		return userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
	}
	@Override
	public String friendRequest(String senderEmailId, String recieverEmailId) throws UserNotFoundException {
		Person sender=userDao.findById(senderEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Person reciever=userDao.findById(recieverEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Friends friends=new Friends(sender, reciever, false); 
		friendsDAO.save(friends);
		Notifications notifications=new Notifications(reciever, sender.getFirstName()+" has sent you a friend Request");
		notificationDAO.save(notifications);
		Notifications notifications1=new Notifications(sender,"You have sent friend request to "+ reciever.getFirstName());
		notificationDAO.save(notifications1);
		return "Friend Request sent";
	}

	@Override
	public Friends acceptFriendRequest(String senderEmailId, String recieverEmailId) throws UserNotFoundException {
		Person reciever=userDao.findById(recieverEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Person sender =userDao.findById(senderEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Friends friends=new Friends(sender, reciever, true); 
		friendsDAO.save(friends);
		Notifications notifications=new Notifications(reciever, sender.getFirstName()+" and you are now Friends");
		notificationDAO.save(notifications);
		Notifications notifications1=new Notifications(sender,"Your friend request is accepted by "+ reciever.getFirstName());
		notificationDAO.save(notifications1);
		return friends;
	}
	@Override
	public void rejectFriendRequest(String senderEmailId) throws UserNotFoundException{
		Person reciever=userDao.findById(sessionEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		Person sender =userDao.findById(senderEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		friendsDAO.rejectFriendRequest(sender);
		Notifications notifications1=new Notifications(sender,"Your friend request is rejected by "+ reciever.getFirstName());
		notificationDAO.save(notifications1);
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


	@Override
	public List<Person> findFriends(String emailId) throws UserNotFoundException {
		Person person = getUserAccountDetails(emailId);
		List<Person> friends= userDao.findNewFriends(emailId);
		friends.removeAll(friendList(emailId));
		friends.removeAll(friendsDAO.findRequestedFriendsNotApproved(person));
		friends.removeAll(friendRequests(emailId));
		return friends;
	}
	@Override
	public Person insertProfilePic(byte[] profilePic) throws UserNotFoundException {
		Person person=userDao.findById(sessionEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		person.setProfilePic(profilePic);
		List<Person>friends =friendList(sessionEmailId);	
		for (Person friend : friends) {
			Notifications notifications=new Notifications(friend, person.getFirstName()+" has updated his profile pic");
			notificationDAO.save(notifications);
		}
		return userDao.save(person);
	}
	@Override
	public byte[] fetchProfilePic() {
		Person person=userDao.findById(sessionEmailId).get();
		return person.getProfilePic();
	}

	@Override
	public List<Notifications> getAllNotifications() throws UserNotFoundException {
		Person person=userDao.findById(sessionEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		return notificationDAO.getAllNotifications(person);
	}

	@Override
	public Person changePassword(String emailId,String password,String newPassword,String confirmNewPassword) throws UserNotFoundException,IncorrectPasswordException, InvalidUserDetailsException {
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		if(user.getPassword().equals(password)) {
			if(newPassword.equals(confirmNewPassword)) {
				user.setPassword(newPassword);
				return userDao.save(user);
			}
			else
				throw new InvalidUserDetailsException("New Password Mismatch!!Please enter the same password twice");
		}
		else
			throw new  IncorrectPasswordException("Password Incorrect!!Please try with new password");			
	}	
	@Override
	public Person forgotPassword(String emailId,String securityAnswer1,String securityAnswer2,String newPassword,String confirmNewPassword) throws UserNotFoundException,IncorrectPasswordException, InvalidUserDetailsException {
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		if(user.getSecurityAnswer1().equalsIgnoreCase(securityAnswer1)&&user.getSecurityAnswer2().equalsIgnoreCase(securityAnswer2)) {
			if(newPassword.equals(confirmNewPassword)) {
				user.setPassword(generateHash(newPassword));
				return userDao.save(user);
			}
			else
				throw new InvalidUserDetailsException("New Password Mismatch!!Please enter the same password twice");
		}
		else
			throw new  InvalidUserDetailsException("Security Answers incorrect!!Answer correctly.");			
	}

	@Override
	public List<Messages> getAllSentMessages() throws UserNotFoundException {
		Person sender=userDao.findById(sessionEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		List<Messages>messages=messageDAO.findSentMessages(sender);
		return messages;
	}

	@Override
	public Messages sendMessage(String recieverEmailId, String message) throws UserNotFoundException {
		Person sender=userDao.findById(sessionEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!"));
		Person reciever=userDao.findById(recieverEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!"));
		Messages messages=new Messages(sender, reciever, message);
		Notifications notifications=new Notifications(reciever, sender.getFirstName()+" has sent you a message");
		notificationDAO.save(notifications);
		return messageDAO.save(messages);
	}

	@Override
	public List<Messages> getAllRecievedMessages() throws UserNotFoundException {
		Person reciever=userDao.findById(sessionEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		List<Messages>messages=messageDAO.findRecievedMessages(reciever);
		return messages;
	}	

	@Override
	public Post sendPost( String message) throws UserNotFoundException {
		Person user=userDao.findById(sessionEmailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		Post post =new Post(user, message,  new Date());
		List<Person>friends =friendList(sessionEmailId);	
		for (Person friend : friends) {
			notificationDAO.save(new Notifications(friend,getUserAccountDetails(sessionEmailId).getFirstName()+" posted on his timeline"));
		}
		postDAO.save(post);	
		return post;
	}
	@Override
	public List<Post> getAllPosts() throws UserNotFoundException {
		List<Post> posts=new ArrayList<Post>();
		posts.addAll(myPosts(sessionEmailId));
		for(Person friend:friendList(sessionEmailId))
			posts.addAll(myPosts(friend.getEmailId()));
		Collections.sort(posts);
		return posts;
	}
	@Override
	public List<Post> myPosts(String emailId) throws UserNotFoundException {
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!")); 
		List<Post> myPosts= postDAO.getMyPosts(user);
		Collections.sort(myPosts);
		return myPosts;
	}

}