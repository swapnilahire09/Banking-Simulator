package com.bank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.User;
import com.bank.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	public Optional<User> getUser(int accountNumber){
		return userRepository.findById(accountNumber);
	}
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	public void deleteUser(int accountNumber) {
		userRepository.deleteById(accountNumber);;
	}
}
