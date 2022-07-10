package com.revature.project0.functionality;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.dao.account.AccountDAOPostgreImpl;
import com.revature.project0.dao.customer.CustomerDAOPostgreImpl;
import com.revature.project0.exceptions.InvalidInputException;
import com.revature.project0.user.Account;
import com.revature.project0.user.Customer;
import com.revature.project0.user.User;

public class CustomerFunctions {
	public static void applyBankAccount() {
		CustomerDAOPostgreImpl custDAO = new CustomerDAOPostgreImpl();
		
		User user = Login.getUser();
		if (user.getUser_type().equals("Customer")) {
			System.out.println("Apply For Your New Customer Account");
			Scanner sc = new Scanner(System.in);
			System.out.println("Type Y to confirm application for new bank account");
			String res = sc.nextLine();
			if (res.equals("Y")) {
				List<Integer> accNoList = custDAO.getCustomerAccountNos(user.getUser_id());
				int newAccNo = user.getUser_id()*1000+accNoList.size()+1;
				Customer c = new Customer(user,newAccNo);
				custDAO.createCustomer(c);
				System.out.println("Your New Account # is: #"+newAccNo);
				System.out.println("Please wait until an Employee approves your account to access your starting balance");
			}
			else {
				System.out.println("New Customer Account Application Cancelled");
			}
			sc.close();
		}
	}
	
	public static void checkBalance() throws InvalidInputException {
		CustomerDAOPostgreImpl custDAO = new CustomerDAOPostgreImpl();
		AccountDAOPostgreImpl accDAO = new AccountDAOPostgreImpl();
		System.out.println("Which account would you like to check the balance of?");
		List<Integer> accNoList;
		accNoList= custDAO.getCustomerAccountNos(Login.getUser().getUser_id());
		for (Integer accNo: accNoList)
			System.out.println("#"+accNo);
		Scanner sc = new Scanner(System.in);
		int acc = sc.nextInt();
		if (!accNoList.contains(acc)) {
			sc.close();
			throw new InvalidInputException();
		}
		else if (accNoList.contains(acc)) {
			Account a = accDAO.getAccount(acc);
			System.out.println("Your account #"+acc+" balance is: "+a.getBalance());
			sc.close();
		}
	}
}//end Class
