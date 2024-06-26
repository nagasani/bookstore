package com.example.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.example.bookstore.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> { 
	
	 UserDetails findByUsername(String username);
}