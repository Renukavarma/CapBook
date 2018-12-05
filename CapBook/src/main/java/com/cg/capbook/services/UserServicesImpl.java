package com.cg.capbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.capbook.beans.Person;
import com.cg.capbook.beans.PersonalInfo;
import com.cg.capbook.daoservices.UserDAO;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.UserNotFoundException;
@Component("userServices")
public class UserServicesImpl implements UserServices {
	@Autowired
private UserDAO userDao;
	
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
	public Person UpdatePersonalInfo(String emailId,PersonalInfo personalInfo) throws UserNotFoundException {
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
		user.setPersonalInfo(personalInfo);
		return userDao.save(user);
	}

	@Override
	public Person getUserAccountDetails(String emailId) throws UserNotFoundException {
		return userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Details Not Found!!!"));
	}

}
