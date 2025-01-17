package com.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entities.User;
import com.bank.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping
	public User createUser() {
		User user=new User();
		user.setBalance(4500);
		user.setEmail("swapnil@gmail.com");
		user.setName("swapnil");
		return userService.createUser(user);
	}
	
	@GetMapping("/{accountNumber}")
	public Optional<User> getUser(@PathVariable int accountNumber) {
		return userService.getUser(accountNumber);
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{accountNumber}")
	public void deleteUser(@PathVariable  int accountNumber) {
		userService.deleteUser(accountNumber);
	}
}
