package com.cg.capbook.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.capbook.beans.Notifications;
import com.cg.capbook.beans.Person;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.InvalidUserDetailsException;
import com.cg.capbook.exceptions.UserNotFoundException;
import com.cg.capbook.services.StorageService;
import com.cg.capbook.services.UserServices;

@RestController
@CrossOrigin
public class UserServicesController {
	@Autowired
	UserServices userServices;
	@Autowired
	StorageService storageService;
	@RequestMapping(value="/userSignup",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>createUser(@RequestBody Person user){
		userServices.createUserAccount(user);
		return new ResponseEntity<>("User signup successfull", HttpStatus.OK);
		
	}
	@RequestMapping(value="/getUserDetails",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<Person>getUserDetails(@RequestParam String emailId,@RequestParam String password ) throws UserNotFoundException, IncorrectPasswordException{
		Person user=userServices.getUserAccount(emailId,password);
		return new ResponseEntity<Person>(user, HttpStatus.OK);
	}
	@RequestMapping(value="/userDetails",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<Person>userDetails(@RequestParam String emailId ) throws UserNotFoundException, IncorrectPasswordException{
		Person user=userServices.getUserAccountDetails(emailId);
		return new ResponseEntity<Person>(user, HttpStatus.OK);
	}
	@RequestMapping(value="/personalInfoUpdate",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<Person>updatePersonalInfo(@RequestParam String emailId,@RequestParam String maritalStatus,@RequestParam String education,@RequestParam String address) throws UserNotFoundException, IncorrectPasswordException{
		Person user=userServices.UpdatePersonalInfo(emailId, maritalStatus, education, address);
		return new ResponseEntity<Person>(user, HttpStatus.OK);
	}
	@PostMapping(value="/setProfilePic",consumes= {MediaType.ALL_VALUE},produces=MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> setImage(@RequestParam("Image") MultipartFile image) throws IOException, UserNotFoundException {
    	System.out.println("Image");
    	storageService.store(image);
    	//File file1=(File) storageService.loadFile(image.getOriginalFilename());
    	File file=new File("D:\\159932_Krishna_Priyanka\\Angular\\AngularApplications\\capbook\\src\\userimages"+image.getOriginalFilename());
    	//System.out.println(file);
    	image.transferTo(file);
    	//System.out.println(file);
    	FileInputStream fin=new FileInputStream(file);
    	//System.out.println(file);
        byte[] bytes = StreamUtils.copyToByteArray(fin);
        userServices.insertProfilePic(bytes);
        System.out.println(bytes);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
    
    @RequestMapping(value = "/getProfilePic", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(userServices.fetchProfilePic());
    }
    @RequestMapping(value="/getNotifications",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<List<Notifications>>getAllNotifications( ) throws UserNotFoundException{
		List<Notifications>notifications=userServices.getAllNotifications();
		return new ResponseEntity<List<Notifications>>(notifications, HttpStatus.OK);
	}
    @RequestMapping(value="/changePassword", produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<Void> updateUserDetails(@RequestParam String emailId,@RequestParam String password,@RequestParam String newPassword,@RequestParam String confirmNewPassword) throws UserNotFoundException ,IncorrectPasswordException, InvalidUserDetailsException{
			userServices.changePassword(emailId,password,newPassword,confirmNewPassword);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value="/forgotUserPassword", produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
	public ResponseEntity<Void> updateUserDetails(@RequestParam String emailId,@RequestParam String securityAnswer1,@RequestParam String securityAnswer2,@RequestParam String newPassword,@RequestParam String confirmNewPassword) throws UserNotFoundException ,IncorrectPasswordException, InvalidUserDetailsException{
			System.out.println(securityAnswer1);
			userServices.forgotPassword(emailId,securityAnswer1,securityAnswer2,newPassword,confirmNewPassword);
			return new ResponseEntity<>(HttpStatus.OK);
	}
}