package com.cg.capbook.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.capbook.beans.Notifications;
import com.cg.capbook.beans.Person;

public interface NotificationDAO extends JpaRepository<Notifications, Integer>{
	@Query("select a.message from Notifications a where a.person=:person")
	List<Notifications> getAllNotifications(@Param("person") Person person);
}
