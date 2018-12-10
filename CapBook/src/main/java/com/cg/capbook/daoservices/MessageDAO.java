package com.cg.capbook.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.capbook.beans.Friends;
import com.cg.capbook.beans.Messages;
import com.cg.capbook.beans.Person;

public interface MessageDAO extends JpaRepository<Messages, Integer>{
	@Query("select a from Messages a where a.sender=:sender ")
	List<Messages> findSentMessages(@Param("sender") Person sender);
	@Query("select a from Messages a where a.reciever=:reciever ")
	List<Messages>findRecievedMessages(@Param("reciever") Person reciever);

}
