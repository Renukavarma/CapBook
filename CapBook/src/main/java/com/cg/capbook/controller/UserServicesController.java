package com.cg.capbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capbook.beans.Person;
import com.cg.capbook.exceptions.UserNotFoundException;
import com.cg.capbook.services.UserServices;

@RestController
@CrossOrigin
public class UserServicesController {
	@Autowired
	UserServices userServices;
	@RequestMapping(value="/userSignup",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>createUser(@RequestBody Person user){
		userServices.createUserAccount(user);
		return new ResponseEntity<>("User signup successfull", HttpStatus.OK);
		
	}
	@RequestMapping(value="/getUserDetails",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<Person>getUserDetails(@RequestBody Person user) throws UserNotFoundException{
		userServices.getUserAccount(user);
		return new ResponseEntity<Person>(user, HttpStatus.OK);
	}

}
