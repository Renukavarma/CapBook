package com.cg.capbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cg.capbook.beans.Person;

import com.cg.capbook.daoservices.UserDAO;
import com.cg.capbook.exceptions.UserNotFoundException;
@Component("userServices")
public class UserServicesImpl implements UserServices {
	@Autowired
private UserDAO userDao;
	
	@Override
	public Person createUserAccount(Person user) {
		/*userDao.save(user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));*/
		userDao.save(user);
		return user;
	}

	@Override
	public Person getUserAccount(String emailId,String password) throws UserNotFoundException{
		Person user=userDao.findById(emailId).orElseThrow(()->new UserNotFoundException("Sorry User Not Found!!!"));
		/*if(bCryptPasswordEncoder.matches(password, user.getPassword()))*/
		return user;
		/*else
			return null;*/
	}

}
