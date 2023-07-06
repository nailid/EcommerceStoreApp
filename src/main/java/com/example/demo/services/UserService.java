package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User addUser(User user) {
		user = userRepo.save(user);
		return user;
	}
	
	public Optional<User> findUser(Long userId){
		Optional<User> user = this.userRepo.findById(userId);
		return user;
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public void delete(Long userId) {
		Optional<User> user = this.userRepo.findById(userId);
		this.userRepo.deleteById(userId);
	}

	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}
}
