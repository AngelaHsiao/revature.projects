package com.revature.project0.dao.transaction;

import java.util.List;

import com.revature.project0.user.Transaction;

public interface TransactionDAO {

	public Transaction getTransaction(int transact_id);
	
	public List<Transaction> getAllTransactions();
	
	public List<Transaction> getUnResolvedTransactions(int accNoOut);
	
	public void createTransaction(Transaction t);
	
	public void updateTransaction(Transaction t);
}
