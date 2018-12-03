package com.cg.capbook.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.capbook.beans.Person;

public interface UserDAO extends JpaRepository<Person, String>{

}
