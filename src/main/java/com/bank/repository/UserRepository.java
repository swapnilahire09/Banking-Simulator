package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
