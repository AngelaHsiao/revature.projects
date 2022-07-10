package com.revature.project0.dao.account;

import java.util.List;

import com.revature.project0.user.Account;

public interface AccountDAO {

	public Account getAccount(int account_no);
	
	public List<Account> getAllAccounts();
	
	List<Integer> getAllAccountNos();
	
	void updateAccountBalance(int account_no, double balance);
	
	void insertAccount (Account a);
	
	
}
