package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService 
{

	@Autowired
	private UserRepository userRepository;

	public User insert(User user) 
	{
		return userRepository.save(user);
	}

	public List<User> findAll() 
	{
		List<User> target = new ArrayList<>();
		userRepository.findAll().forEach(target::add);
		return target;
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	public User updateUser(int id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
}