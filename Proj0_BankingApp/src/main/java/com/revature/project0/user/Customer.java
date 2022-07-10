package com.revature.project0.user;

public class Customer extends User{
private int accountNo;
private boolean is_Approved;

public Customer() {
	super();
}

public Customer(int user_id, String username, String password, String user_type, int accountNo, boolean is_Approved) {
	super(user_id, username, password, user_type);
	this.accountNo = accountNo;
	this.is_Approved=is_Approved;
}

public Customer(User u,int accountNo) {
	super(u.getUser_id(),u.getUsername(),u.getPassword(),u.getUser_type());
	this.accountNo=accountNo;
}

public Customer(int user_id, int accountNo, boolean is_Approved) {
	super(user_id);
	this.accountNo = accountNo;
	this.is_Approved = is_Approved;
}

public int getAccountNo() {
	return accountNo;
}

public void setAccountNo(int accountNo) {
	this.accountNo = accountNo;
}


public boolean isIs_Approved() {
	return is_Approved;
}

public void setIs_Approved(boolean is_Approved) {
	this.is_Approved = is_Approved;
}

@Override
public String toString() {
	return "Customer [user_id= "+ getUser_id() + " accountNo=" + accountNo + " is_Approved= "+is_Approved+"]";
}

}//end class
