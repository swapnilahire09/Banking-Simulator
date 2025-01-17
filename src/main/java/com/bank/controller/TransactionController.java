package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entities.Transaction;
import com.bank.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/deposit")
	public String deposit(@RequestParam  int accountNumber, @RequestParam double amount) {
		return transactionService.deposit(accountNumber, amount);
	}
	@PutMapping("/withdraw")
	public String withdraw(@RequestParam int accountNumber,@RequestParam double amount) {
		return transactionService.withdraw(accountNumber, amount);
	}
	@GetMapping("/{accountNumber}")
	public List<Transaction> getTransaction(@PathVariable int accountNumber){
		return transactionService.getAllTranaction(accountNumber);
	}
	
}
