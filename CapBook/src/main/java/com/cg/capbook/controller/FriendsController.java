package com.cg.capbook.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capbook.beans.Friends;
import com.cg.capbook.beans.Person;
import com.cg.capbook.exceptions.UserNotFoundException;
import com.cg.capbook.services.UserServices;

@RestController
@CrossOrigin
public class FriendsController {
	@Autowired
	UserServices userServices;
	private String message;
	@RequestMapping(value="/friendRequest",method=RequestMethod.GET)
	public ResponseEntity<String> friendRequest(@RequestParam("senderEmailId") String senderEmailId,@RequestParam("recieverEmailId") String recieverEmailId){

			try {
				 message=userServices.friendRequest(senderEmailId, recieverEmailId);
				return new ResponseEntity<>(message,HttpStatus.OK);
			} catch (UserNotFoundException e) {
				return null;
			}
	}
	@RequestMapping(value="/acceptFriendRequest",method=RequestMethod.GET)
	public ResponseEntity<Friends> acceptFriendRequest(@RequestParam("recieverEmailId") String recieverEmailId,@RequestParam("senderEmailId") String senderEmailId){

			try {
				Friends friends=userServices.acceptFriendRequest(senderEmailId, recieverEmailId);
				return new ResponseEntity<>(friends,HttpStatus.OK);
			} catch (UserNotFoundException e) {
				return null;
			}	
	}
	@RequestMapping(value="/rejectFriendRequest",method=RequestMethod.GET)
	public ResponseEntity<String> rejectFriendRequest(@RequestParam("senderEmailId") String senderEmailId){

			try {
				userServices.rejectFriendRequest(senderEmailId);
				return new ResponseEntity<>("Rejected friend Request",HttpStatus.OK);
			} catch (UserNotFoundException e) {
				return null;
			}	
	}
	@RequestMapping(value="/findFriends",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<List<Person>> findFriends(@RequestParam("emailId") String emailId) throws UserNotFoundException{

			List<Person> friends=userServices.friendList(emailId);
			return new ResponseEntity<>(friends,HttpStatus.OK);
	}
	@RequestMapping(value="/findFriendRequests",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<List<Friends>> findFriendRequests(@RequestParam("emailId") String emailId) throws UserNotFoundException{

			List<Friends> friendRequests=userServices.friendRequests(emailId);
			return new ResponseEntity<>(friendRequests,HttpStatus.OK);
	}
	@RequestMapping(value="/findNewFriends",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<List<Person>> findNewFriends(@RequestParam("emailId") String emailId) throws UserNotFoundException{
			List<Person> friends=userServices.findFriends(emailId);
			return new ResponseEntity<>(friends,HttpStatus.OK);
	}

}
