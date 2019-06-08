package com.shinycatcher.api.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shinycatcher.api.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("select * from user where upper(user_name) = upper(:userName)")
	Iterable<User> findByUserName(@Param("userName") String userName);

}
