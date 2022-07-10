package com.revature.project0.functionality;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.dao.account.AccountDAOPostgreImpl;
import com.revature.project0.dao.customer.CustomerDAOPostgreImpl;
import com.revature.project0.dao.transaction.TransactionDAOPostgreImpl;
import com.revature.project0.exceptions.AccessDeniedException;
import com.revature.project0.exceptions.InvalidInputException;
import com.revature.project0.exceptions.InvalidMoneyTransactException;
import com.revature.project0.user.Account;
import com.revature.project0.user.Customer;
import com.revature.project0.user.Transaction;

public class MoneyTransferFunctions {
	private static CustomerDAOPostgreImpl custDAO = new CustomerDAOPostgreImpl();
	private static AccountDAOPostgreImpl accDAO = new AccountDAOPostgreImpl(); 
	private static TransactionDAOPostgreImpl tranDAO = new TransactionDAOPostgreImpl();
	
	private static Scanner sc = new Scanner(System.in);

	private static int getAccountInProcess() {
		System.out.println("Enter your account # : ");
		List<Integer> accounts = custDAO.getCustApprovedAccNos(Login.getUser().getUser_id());
		for (Integer acc:accounts)
			System.out.println("#"+acc);
		int accNo = sc.nextInt();
		return accNo;
	}
	
	public static void deposit() {
		try {
		int accNo = getAccountInProcess();
		Customer c = custDAO.getCustByAccNo(accNo);
		if (c.getUser_id() ==Login.getUser().getUser_id()) {
			System.out.println("Enter amount you wish to deposit: ");
			double amount = sc.nextDouble();
			if (amount <=0)
				throw new InvalidMoneyTransactException();
			else {
				double newBalance = accDAO.getAccount(accNo).getBalance()+amount;
				accDAO.updateAccountBalance(accNo, newBalance);
				Transaction t = new Transaction(accNo,amount,true);
				tranDAO.createTransaction(t);
				System.out.println("Your new balance is: "+newBalance);
			}
		}
		else {
			throw new InvalidMoneyTransactException();
		}
		}catch(InvalidMoneyTransactException m) {
			System.out.println(m.toString());
		}
	}
	public static void withdrawal() throws InvalidMoneyTransactException {
		int accNo = getAccountInProcess();
		Customer c = custDAO.getCustByAccNo(accNo);
		if (c.getUser_id()==Login.getUser().getUser_id()) {
			System.out.println("Enter amount you wish to withdraw: ");
			double amount = sc.nextDouble();
			double newBalance = accDAO.getAccount(accNo).getBalance()-amount;
			if (amount <= 0 || newBalance <=0)
				throw new InvalidMoneyTransactException();
			else {
				accDAO.updateAccountBalance(accNo, newBalance);
				Transaction t = new Transaction(accNo,amount,true);
				tranDAO.createTransaction(t);
				System.out.println("Your new balance is: "+newBalance);
			}
		}
	}
	public static void transferral() throws InvalidMoneyTransactException, InvalidInputException {
		int accNoIn = getAccountInProcess();
		Customer c = custDAO.getCustByAccNo(accNoIn);
		if (c.getUser_id() == Login.getUser().getUser_id()) {
		System.out.println("Which account would you like to transfer money to?");
		int accNoOut = sc.nextInt();
		if (!accDAO.getAllAccountNos().contains((Integer)accNoOut))
			throw new InvalidInputException();
		else {
			System.out.println("How much money would you like to transfer? ");
			double amount = sc.nextDouble();
			Account a = accDAO.getAccount(accNoIn);
			if (a.getBalance()-amount <0 || amount <=0)
				throw new InvalidMoneyTransactException();
				Transaction t = new Transaction(accNoIn,accNoOut,amount);
				tranDAO.createTransaction(t);
				System.out.println("Transferral request has been submitted");
			}
		}
	}
	
	public static void approveTransfer() throws InvalidInputException {
		int accNoOut = getAccountInProcess();
		Customer c = custDAO.getCustByAccNo(accNoOut);
		if (c.getUser_id() == Login.getUser().getUser_id()) {
		System.out.println("Which unapproved transfer requests would you like to handle?");
		List<Transaction> listTs = tranDAO.getUnResolvedTransactions(accNoOut);
		for (Transaction t: listTs)
			System.out.println(t);
		}
		int transact_id = sc.nextInt();
		Transaction t = tranDAO.getTransaction(transact_id);
		System.out.println("Approve or Reject?");
		String aor = sc.next();
		if (aor.equals("Approve")) {
			t.setTr_approval(true);
			tranDAO.updateTransaction(t);
			Account a1 = accDAO.getAccount(t.getAccount_no_out());
			Account a2 = accDAO.getAccount(t.getAccount_no_in());
			accDAO.updateAccountBalance(a1.getAccount_no(),a1.getBalance()+t.getTransact());
			accDAO.updateAccountBalance(a2.getAccount_no(), a2.getBalance()-t.getTransact());
		}
		else if (aor.equals("Reject")) {
			t.setTr_approval(false);
			tranDAO.updateTransaction(t);
		}
		else
			throw new InvalidInputException();
	}
	
	public static void viewAllTransactions() throws AccessDeniedException {
		if (!Login.getUser().getUser_type().equals("Employee")) {
			throw new AccessDeniedException();
		}
		else {
			TransactionDAOPostgreImpl tranDAO = new TransactionDAOPostgreImpl();
			List<Transaction> listTransact = tranDAO.getAllTransactions();
			for (Transaction t: listTransact)
				System.out.println(t);
		}
	}
}
