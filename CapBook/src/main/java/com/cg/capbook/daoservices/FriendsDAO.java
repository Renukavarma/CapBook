package com.cg.capbook.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cg.capbook.beans.Friends;
import com.cg.capbook.beans.Person;

public interface FriendsDAO extends JpaRepository<Friends, Person>{
	@Query("select a.sender from Friends a where a.reciever=:reciever AND a.isAccepted is true")
	List<Person> findAcceptedFriends(@Param("reciever") Person reciever);
	@Query("select a.reciever from Friends a where a.sender=:sender AND a.isAccepted is true")
	List<Person> findRequestedFriends(@Param("sender") Person sender);
	@Query("select a.reciever from Friends a where a.sender=:sender AND a.isAccepted is false")
	List<Person> findRequestedFriendsNotApproved(@Param("sender") Person sender);
	@Query("select a from Friends a where a.reciever=:reciever AND a.isAccepted is false")
	List<Friends>findFriendRequests(@Param("reciever") Person reciever);
	@Transactional
	@Modifying
	@Query("Delete  from Friends a where a.sender=:sender ")
	void rejectFriendRequest(@Param("sender")Person sender);
}
