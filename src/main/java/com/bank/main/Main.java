package com.bank.main;

import com.bank.entities.Transactions;
import com.bank.entities.UsersManagement;

public class Main {

	public static void main(String[] args) {
		UsersManagement usersManagement=new UsersManagement();
//		usersManagement.createUser("swapnil Ahire", 1234567896, 60000);
//		usersManagement.viewUser(1234567896, "swapnil");
//		usersManagement.updateUser(1234567896, "Ahire");
//		usersManagement.deleteUser(1234567896);
		Transactions transactions=new Transactions();
//		transactions.deposite(1234567895, 5200);

//		transactions.deposite(1234567895, 5000);
//		transactions.transfer(1234567895, -4000, 1234567896);
//		transactions.withdrawal(1234567895, 10000);
		transactions.transfer(1234567896, 5000, 1234567895);
		transactions.deposite(1234567895, 5000);
	}

}
