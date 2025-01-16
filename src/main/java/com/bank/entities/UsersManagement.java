package com.bank.entities;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import com.bank.db.DataBase;

public class UsersManagement {
	Connection conn = DataBase.createConncetion();

	public void createUser(String name, long accountNo, double balance) {
		String sql = "insert into users(account_no,name,balance) values(?,?,?)";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, accountNo);
			ps.setString(2, name);
			ps.setDouble(3, balance);

			int i = ps.executeUpdate();
			if (i >= 1) {
				System.out.println("Data inserted");
			} else {
				System.out.println("Failed");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void viewUser(long ac, String name) {
		String sql = "select * from users where account_no=? and name=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ac);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					System.out.println("Account number :" + rs.getLong(1));
					System.out.println("Name :" + rs.getString(2));
					System.out.println("Balance :" + rs.getDouble(3));
				} while (rs.next());

			} else {
				System.out.println("No record found");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void updateUser(long ac,String name) {
		String sql = "select * from users where account_no=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ac);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				System.out.println("Account number :" + rs.getLong(1));
				System.out.println("Name :" + rs.getString(2));
				System.out.println("Balance :" + rs.getDouble(3));
				String sql2 = "update users set name=? where account_no=?";
				PreparedStatement ps2;
				ps2 = conn.prepareStatement(sql2);
				ps2.setLong(2, ac);
				ps2.setString(1, name);
				int i=ps2.executeUpdate();
				if(i>=1) {
					System.out.println("Data Updated");
				}
				else {
					System.out.println("Data not updated");
				}
				
			} else {
				System.out.println("No record found");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public void deleteUser(long ac) {
		String sql = "delete from users where account_no=? ";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, ac);

			int i = ps.executeUpdate();
			if (i >= 1) {
				System.out.println("Data Deleted");
			} else {
				System.out.println("Recored not found");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
