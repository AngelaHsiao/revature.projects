package com.revature.project0.user;

public class Transaction {
private int transaction_id;
private int account_no_in;
private int account_no_out;
private double transact;
private boolean tr_approval;
public Transaction() {
	super();
}
public Transaction(int transaction_id, int account_no_in, int account_no_out, double transact, boolean tr_approval) {
	super();
	this.transaction_id = transaction_id;
	this.account_no_in = account_no_in;
	this.account_no_out = account_no_out;
	this.transact = transact;
	this.tr_approval = tr_approval;
}
public Transaction(int transaction_id, int account_no_in, double transact, boolean tr_approval) {
	super();
	this.transaction_id = transaction_id;
	this.account_no_in = account_no_in;
	this.transact = transact;
	this.tr_approval = tr_approval;
}

public Transaction(int account_no_in, double transact, boolean tr_approval) {
	super();
	this.account_no_in = account_no_in;
	this.transact = transact;
	this.tr_approval = tr_approval;
}

public Transaction(int account_no_in, int account_no_out, double transact) {
	super();
	this.account_no_in = account_no_in;
	this.account_no_out = account_no_out;
	this.transact = transact;
}
public int getTransaction_id() {
	return transaction_id;
}
public void setTransaction_id(int transaction_id) {
	this.transaction_id = transaction_id;
}
public int getAccount_no_in() {
	return account_no_in;
}
public void setAccount_no_in(int account_no_in) {
	this.account_no_in = account_no_in;
}
public int getAccount_no_out() {
	return account_no_out;
}
public void setAccount_no_out(int account_no_out) {
	this.account_no_out = account_no_out;
}
public double getTransact() {
	return transact;
}
public void setTransact(double transact) {
	this.transact = transact;
}
public boolean getTr_approval() {
	return tr_approval;
}
public void setTr_approval(boolean tr_approval) {
	this.tr_approval = tr_approval;
}
@Override
public String toString() {
	return "Transaction [transaction_id=" + transaction_id + ", account_no_in=" + account_no_in + ", account_no_out="
			+ account_no_out + ", transact=" + transact + ", tr_approval=" + tr_approval + "]";
}


}
