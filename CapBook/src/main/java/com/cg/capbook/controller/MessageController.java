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

import com.cg.capbook.beans.Messages;
import com.cg.capbook.beans.Person;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.UserNotFoundException;
import com.cg.capbook.services.UserServices;

@RestController
@CrossOrigin
public class MessageController {
	@Autowired
	UserServices userServices;
	@RequestMapping(value="/getAllSentMessages",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<List<Messages>>getAllSentMessages( ) throws UserNotFoundException, IncorrectPasswordException{
		List<Messages>messages=userServices.getAllSentMessages();
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	@RequestMapping(value="/getAllRecievedMessages",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<List<Messages>>getAllRecievedMessages( ) throws UserNotFoundException, IncorrectPasswordException{
		List<Messages>messages=userServices.getAllRecievedMessages();
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	@RequestMapping(value="/sendMessage",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public ResponseEntity<Messages>sendMessage(@RequestParam String recieverEmailId,@RequestParam String message ) throws UserNotFoundException, IncorrectPasswordException{
		Messages messages=userServices.sendMessage(recieverEmailId, message);
		return new ResponseEntity<Messages>(messages, HttpStatus.OK);
	}

}
