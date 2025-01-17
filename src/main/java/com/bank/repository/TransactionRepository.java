package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	List<Transaction> findByAccountNumber(int accountNumber);

}
