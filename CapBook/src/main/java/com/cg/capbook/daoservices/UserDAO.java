package com.cg.capbook.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.capbook.beans.Person;

public interface UserDAO extends JpaRepository<Person, String>{
	@Query("select a from Person a where not a.emailId=:emailId  ")
	List<Person> findNewFriends(@Param("emailId") String emailId);
}
