package com.revature.project0.bankappface;

import java.util.Scanner;

import com.revature.project0.exceptions.AccessDeniedException;
import com.revature.project0.exceptions.InvalidInputException;
import com.revature.project0.exceptions.InvalidMoneyTransactException;
import com.revature.project0.functionality.CustomerFunctions;
import com.revature.project0.functionality.EmployeeFunctions;
import com.revature.project0.functionality.Login;
import com.revature.project0.functionality.MoneyTransferFunctions;
import com.revature.project0.functionality.UserFunctions;

public class BankAppFace {
	static Scanner sc = new Scanner(System.in);
public static void main(String[] args) {
	boolean isLoggedIn = false;
	do {
		isLoggedIn =Login.loginToAccount();
	}while(isLoggedIn == false);
	
	
	try {
		if (Login.getUser().getUser_type().equals("User")) {
			userOptions();
		}
		else if (Login.getUser().getUser_type().equals("Customer")) {
			custOptions();
			
		}
		else if (Login.getUser().getUser_type().equals("Employee")) {
			employeeOptions();
		}
	}catch(InvalidInputException | AccessDeniedException | InvalidMoneyTransactException e ) {
		System.out.println(e.getMessage());
	} 
	finally {
		sc.close();
		System.out.println("Thank you for using this Bank App");
	}
	
}//end main

private static void userOptions() throws InvalidInputException {
	System.out.println("Options: 1. Register for customer account");
	sc = new Scanner(System.in);
	int input = sc.nextInt();
	if (input == 1)
		UserFunctions.registerCustAccount();
	else
		throw new InvalidInputException();
}
private static void custOptions() throws InvalidInputException, InvalidMoneyTransactException {
	System.out.println("Options: 1. Apply for new account, 2. Check account balance, 3. Perform transaction");
	sc = new Scanner(System.in);
	int input = sc.nextInt();
	if (input == 1)
		CustomerFunctions.applyBankAccount();
	else if (input == 2)
		CustomerFunctions.checkBalance();
	else if (input == 3) {
		System.out.println("Transaction Options: 1. Deposit, 2. Withdrawal, 3. Money Transfer, 4. Approve Money Transfer");
		int newIn = sc.nextInt();
		if (newIn == 1)
			MoneyTransferFunctions.deposit();
		else if (newIn == 2)
			MoneyTransferFunctions.withdrawal();
		else if (newIn == 3)
			MoneyTransferFunctions.transferral();
		else if (newIn == 4)
			MoneyTransferFunctions.approveTransfer();
		else
			throw new InvalidInputException();
	}
	else
		throw new InvalidInputException();
}

private static void employeeOptions() throws InvalidInputException, AccessDeniedException {
	System.out.println("Options: 1. View all Customers, 2. View all Accounts, 3. Approve or reject account, 4. View all Transactions");
	sc = new Scanner(System.in);
	int input = sc.nextInt();
	if (input == 1)
		EmployeeFunctions.viewAllCustomers();
	else if (input == 2)
		EmployeeFunctions.viewAllAccounts();
	else if (input == 3)
		EmployeeFunctions.accApproveOrReject();
	else if (input == 4)
		MoneyTransferFunctions.viewAllTransactions();
	else
		throw new InvalidInputException();
}
}//end class
