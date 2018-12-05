package com.cg.capbook.services;

import com.cg.capbook.beans.Person;
import com.cg.capbook.exceptions.IncorrectPasswordException;
import com.cg.capbook.exceptions.UserNotFoundException;


public interface UserServices {
	Person createUserAccount(Person user);
	Person getUserAccount(String emailId,String password) throws UserNotFoundException, IncorrectPasswordException;
	Person getUserAccountDetails(String emailId) throws UserNotFoundException;
	Person UpdatePersonalInfo(String emailId,String maritalStatus,String education,String address) throws UserNotFoundException;

}
