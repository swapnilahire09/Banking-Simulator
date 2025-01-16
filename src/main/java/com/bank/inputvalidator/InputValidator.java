package com.bank.inputvalidator;

import java.util.regex.Pattern;

public class InputValidator {
	//Validation for email
	public static boolean isValidEmail(String email) {
		String emailRegex="^[A-Za-z0-9+_.-]+@(.+)$";
		return Pattern.matches(emailRegex, email);
	}
	//Validation For Amount Positive
	public static boolean isPositiveAmount(double amount) {
		return amount>0;
	}
	//Validation for Account number
	public static boolean isValidAccountNumber(long ac) {
		return ac>1000000000;
	}

}
