package com.jon.web.JavaBelt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jon.web.JavaBelt.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
}
