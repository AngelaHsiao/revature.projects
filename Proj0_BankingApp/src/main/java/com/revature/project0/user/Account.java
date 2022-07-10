package com.revature.project0.user;

public class Account {
private int account_no;
private double balance;

private static double defaultBalance = 200;
public Account() {
	super();
}
public Account(int account_no) {
	super();
	this.account_no = account_no;
}

public Account(int account_no, double balance) {
	super();
	this.account_no = account_no;
	this.balance = balance;
}
public int getAccount_no() {
	return account_no;
}
public void setAccount_no(int account_no) {
	this.account_no = account_no;
}
public double getBalance() {
	return balance;
}
public void setBalance() {
	this.balance = defaultBalance;
}
public void setBalance(double balance) {
	this.balance = balance;
}

@Override
public String toString() {
	return "Account [account_no=" + account_no + ", balance=" + balance + "]";
}


}
