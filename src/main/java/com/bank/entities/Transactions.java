package com.bank.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.db.DataBase;
import com.bank.inputvalidator.InputValidator;

public class Transactions {

	// Deposite Module
	public void deposite(long ac, double amount) {
		if (!InputValidator.isPositiveAmount(amount)) {
			System.out.println("Invalid Amount format.Please try again");
			return;
		}
		if (!InputValidator.isValidAccountNumber(ac)) {
			System.out.println("Invalid Account Number.Please try again");
			return;
		}

		String updateTransaction = "update users set balance= balance+? where account_no=?";
		String insertTransaction = "insert into transactions(account_no,type,amount) values(?,'Deposit',?)";
		PreparedStatement ps2, ps3;
		try {
			Connection conn = DataBase.createConncetion();
			conn.setAutoCommit(false);
			ps2 = conn.prepareStatement(updateTransaction);
			ps3 = conn.prepareStatement(insertTransaction);
			ps2.setLong(2, ac);
			ps2.setDouble(1, amount);
			ps3.setLong(1, ac);
			ps3.setDouble(2, amount);
			int i = ps2.executeUpdate();
			int j = ps3.executeUpdate();
			if (i >= 1 && j >= 1) {
				conn.commit();
				System.out.println(amount + " Deposited in Ac:" + ac);
			} else {
				System.out.println("Transaction Failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Withdrawal Module
	public void withdrawal(long ac, double amount) {
		if (!InputValidator.isPositiveAmount(amount)) {
			System.out.println("Invalid Amount format.Please try again");
			return;
		}
		if (!InputValidator.isValidAccountNumber(ac)) {
			System.out.println("Invalid Account Number.Please try again");
			return;
		}
		String checkBalance = "select balance from users where account_no=?";
		String update = "update users set balance=balance-? where account_no=?";
		String insertTransaction = "insert into transactions(account_no,type,amount) values(?,'Withdrawal',?)";
		try (Connection conn = DataBase.createConncetion()) {
			conn.setAutoCommit(false);

			try {
				PreparedStatement checkStmt = conn.prepareStatement(checkBalance);
				PreparedStatement updateStmt = conn.prepareStatement(update);
				PreparedStatement insertStmt = conn.prepareStatement(insertTransaction);
				checkStmt.setLong(1, ac);
				ResultSet rs = checkStmt.executeQuery();
				if (rs.next()) {
					double balance = rs.getDouble("balance");
					if (balance >= amount) {
						updateStmt.setDouble(1, amount);
						updateStmt.setLong(2, ac);
						updateStmt.executeUpdate();

						insertStmt.setLong(1, ac);
						insertStmt.setDouble(2, amount);
						insertStmt.executeUpdate();

						conn.commit();
						System.out.println("Withdraw Successful");
					} else {
						System.out.println("Insufficient Funds");
					}

				} else {
					System.out.println("Account not found");
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Transfer Module
	public void transfer(long ac, double amount, long toAc) {
		if (!InputValidator.isPositiveAmount(amount)) {
			System.out.println("Invalid Amount.Please try again");
			return;
		}
		if (!InputValidator.isValidAccountNumber(toAc)) {
			System.out.println("Invalid Receiver Account Number.Please try again");
			return;
		}
		if (!InputValidator.isValidAccountNumber(ac)) {
			System.out.println("Invalid Sender Account number.Please try again");
			return;
		}

		String checkBalance = "select balance from users where account_no=?";
		String updateFrom = "update users set balance=balance-? where account_no=?";
		String updateTo = "update users set balance=balance+? where account_no=?";
		String insertTransaction = "insert into transactions(account_no,type,amount) values(?,'Transfer',?)";

		try (Connection conn = DataBase.createConncetion();) {
			conn.setAutoCommit(false);
			PreparedStatement checkStmt = conn.prepareStatement(checkBalance);
			PreparedStatement updateFromStmt = conn.prepareStatement(updateFrom);
			PreparedStatement updateToStmt = conn.prepareStatement(updateTo);
			PreparedStatement insertStmt = conn.prepareStatement(insertTransaction);

			checkStmt.setLong(1, ac);
			ResultSet rs = checkStmt.executeQuery();
			if (rs.next()) {
				double balance = rs.getDouble("balance");
				if (balance >= amount) {
					updateFromStmt.setDouble(1, amount);
					updateFromStmt.setLong(2, ac);
					updateFromStmt.executeUpdate();

					updateToStmt.setDouble(1, amount);
					updateToStmt.setLong(2, toAc);
					updateToStmt.executeUpdate();

					insertStmt.setLong(1, toAc);
					insertStmt.setDouble(2, amount);
					insertStmt.executeUpdate();
					conn.commit();
					System.out.println("Transfer Successful");
				} else {
					System.out.println("insufficient Founds");
					conn.rollback();
				}

			} else {
				System.out.println("Sender account not found ");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
