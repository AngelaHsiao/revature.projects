package com.revature.project0.functionality;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.dao.account.AccountDAOPostgreImpl;
import com.revature.project0.dao.customer.CustomerDAOPostgreImpl;
import com.revature.project0.exceptions.AccessDeniedException;
import com.revature.project0.exceptions.InvalidInputException;
import com.revature.project0.user.Account;
import com.revature.project0.user.Customer;

public class EmployeeFunctions {
	public static void viewAllCustomers() throws AccessDeniedException {
		CustomerDAOPostgreImpl custDAO = new CustomerDAOPostgreImpl();
		if(!Login.getUser().getUser_type().equals("Employee")) {
			throw new AccessDeniedException();
		}
		else {
			List<Customer> allCs = custDAO.getAllCustomers();
			for(Customer c:allCs)
				System.out.println(c);
		}
	}
	
	public static void viewAllAccounts() throws AccessDeniedException {
		AccountDAOPostgreImpl accDAO = new AccountDAOPostgreImpl();
		if(!Login.getUser().getUser_type().equals("Employee")) {
			throw new AccessDeniedException();
		}
		else {
			List<Account> allAs = accDAO.getAllAccounts();
			for(Account a:allAs)
				System.out.println(a);
		}
	}
	
	public static void accApproveOrReject() throws AccessDeniedException, InvalidInputException {
		CustomerDAOPostgreImpl custDAO = new CustomerDAOPostgreImpl();
		AccountDAOPostgreImpl accDAO = new AccountDAOPostgreImpl();
		if (!Login.getUser().getUser_type().equals("Employee")) {
			throw new AccessDeniedException();
		}
		else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Which account would you like to approve/reject?");
			List<Integer> nullAccs = custDAO.getNullCustAccNos();
			for(Integer accNum:nullAccs)
				System.out.println(accNum);
			
			int accountNo = sc.nextInt();
			System.out.println("Would you like to Approve or Reject this account?");
			String aor = sc.next();
			if (aor.equals("Approve")) {
				custDAO.approveCustomer(accountNo);
				System.out.println("Account has been Approved.");
				Account a = new Account(accountNo);
				a.setBalance();
				accDAO.insertAccount(a);
			}
			else if (aor.equals("Reject")) {
				custDAO.rejectCustomer(accountNo);
				System.out.println("Account has been Rejected.");
			}
			else {
				throw new InvalidInputException();
			}
		}
		
	}
}
