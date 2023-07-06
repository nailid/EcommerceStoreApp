package com.example.demo.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@PutMapping("/users")
	 public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception{
		 if(user.getId() == null) {
			 throw new Exception("User not exists !!");
		 }
		 User addedUser = this.userService.addUser(user);
		 return new ResponseEntity<User>(addedUser, HttpStatus.OK);
	 }
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> addUser(@PathVariable("id") Long id){
		Optional<User> user = userService.findUser(id);
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") Long id){
		this.userService.delete(id);
		}
	
		
}
