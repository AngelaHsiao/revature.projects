package com.revature.project0.dao.customer;

import java.util.List;

import com.revature.project0.user.Customer;

public interface CustomerDAO {
	public Customer getCustomer(int cust_id);
	
	public Customer getCustByAccNo(int account_no);
	
	public List<Customer> getAllCustomers();
	
	public List<Integer> getCustomerAccountNos(int cust_id);
	
	void createCustomer (Customer c);
	
	void approveCustomer(int cust_id); //approve a customer account no EMPLOYEE ACCESSIBLE
	
	void rejectCustomer(int cust_id); //reject a customer account no EMPLOYEE ACCESSIBLE
	
	void deleteCustomer(int cust_id);
	
	List<Integer> getNullCustAccNos(); //get all the unapproved customer account numbers EMPLOYEE ACCESSIBLE
	
	List<Integer> getCustApprovedAccNos(int cust_id); //get the approved accounts of a customer CUSTOMER ACCESSIBLE
}
