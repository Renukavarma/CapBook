package com.cg.capbook.daoservices;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.capbook.beans.Person;
import com.cg.capbook.beans.Post;
public interface PostDAO extends JpaRepository<Post, Integer> {
	@Query("SELECT p from Post p where p.user=:user")
	List<Post> getMyPosts(@Param("user")Person user);
}