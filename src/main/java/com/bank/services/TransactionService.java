package com.bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.entities.Transaction;
import com.bank.entities.User;
import com.bank.repository.TransactionRepository;
import com.bank.repository.UserRepository;

@Service
public class TransactionService{
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public String deposit(int accountNumber ,double amount) {
		User user=userRepository.findById(accountNumber).orElseThrow(()-> new RuntimeException("Account not fond"));
		user.setBalance(user.getBalance()+amount);
		userRepository.save(user);
		
		Transaction transaction=new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		transaction.setType("Deposit");
		transactionRepository.save(transaction);
		return "Deposited successful!";
	}
	
	@Transactional
	public String withdraw(int accountNumber,double amount) {
		User user=userRepository.findById(accountNumber).orElseThrow(()-> new RuntimeException("Account not found"));
		if (user.getBalance()<amount) {
			throw new RuntimeException("Insufficient balance");
		}
		user.setBalance(user.getBalance()-amount);
		userRepository.save(user);
		
		Transaction transaction=new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		transaction.setType("Withdrawal");
		transactionRepository.save(transaction);
		return "Withdrawal successful!";
	}
	@Transactional
	public List<Transaction> getAllTranaction(int accountNumber){
		return transactionRepository.findByAccountNumber(accountNumber);
	}
}