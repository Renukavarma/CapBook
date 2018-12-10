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
import com.cg.capbook.beans.Post;
import com.cg.capbook.exceptions.UserNotFoundException;
import com.cg.capbook.services.UserServices;
@RestController
@CrossOrigin
public class PostController {
	@Autowired
	UserServices userServices;
	@RequestMapping(value="/sendPost",method=RequestMethod.GET)
	public ResponseEntity<Post> acceptPostDetails(@RequestParam("message") String message){
		try {
			return new ResponseEntity<>(userServices.sendPost( message),HttpStatus.OK);
		} catch (UserNotFoundException e) {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}	
	}
	@RequestMapping(value="/myPosts",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> getMyPosts(@RequestParam("emailId")String emailId){
		try {
			return new ResponseEntity<>(userServices.myPosts(emailId),HttpStatus.OK);
		} catch (UserNotFoundException e) {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}	
	}
	@RequestMapping(value="/getAllPosts",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> getAllPosts(){
		try {
			return new ResponseEntity<>(userServices.getAllPosts(),HttpStatus.OK);
		} catch (UserNotFoundException e) {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}	
	}
}