package com.subir.jpa.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.subir.jpa.model.Users;

public interface UsersRepo extends CrudRepository<Users, String> {

	@Query("from Users where username=?1 and password=?2")
	List <Users> findByUsernameAndPassword(String username, String password);
	
	List <Users> findByUsername(String username);

}
