package com.revature.project0.dao.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.jdbc.utils.ConnUtils;
import com.revature.project0.user.Customer;

public class CustomerDAOPostgreImpl implements CustomerDAO {

	@Override
	public Customer getCustomer(int cust_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustByAccNo(int account_no) {
		Customer c = null;
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchCust = conn.prepareStatement("Select * from customers where account_no = ?");
			fetchCust.setInt(1, account_no);
			ResultSet rs = fetchCust.executeQuery();
			if (!rs.next())
				return null;
			else {
				do {
					c = new Customer();
					c.setUser_id(rs.getInt(1));
					c.setAccountNo(rs.getInt(2));
					c.setIs_Approved(rs.getBoolean(3));
				}while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> allCusts = new ArrayList<Customer>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchAllCust = conn.prepareStatement("select * from customers");
			ResultSet rs = fetchAllCust.executeQuery();
			while (rs.next()) {
				Customer c = new Customer(rs.getInt(1),rs.getInt(2),rs.getBoolean(3));
				allCusts.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return allCusts;
	}

	@Override
	public List<Integer> getCustomerAccountNos(int cust_id) {
		List<Integer> accNos = new ArrayList<Integer>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchAccNoByCust = conn.prepareStatement("select account_no from customers where cust_id =?");
			fetchAccNoByCust.setInt(1,cust_id);
			ResultSet rs = fetchAccNoByCust.executeQuery();
			if (!rs.next()) {
				return accNos;
			}
			else {
				do {
					accNos.add((Integer)rs.getInt(1));
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accNos;
	}

	@Override
	public void createCustomer(Customer c) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement createCust = conn.prepareStatement("insert into customers (cust_id,account_no) values (?,?)");
			createCust.setInt(1, c.getUser_id());
			createCust.setInt(2, c.getAccountNo());
			createCust.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void approveCustomer(int account_no) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement approveCust = conn.prepareStatement("update customers set is_approved = true where account_no =?");
			approveCust.setInt(1, account_no);
			approveCust.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void rejectCustomer(int account_no) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement approveCust = conn.prepareStatement("update customers set is_approved = false where account_no =?");
			approveCust.setInt(1, account_no);
			approveCust.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCustomer(int cust_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getNullCustAccNos() {
		List<Integer> nullAccsList = new ArrayList<Integer>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement nullAccs = conn.prepareStatement("Select account_no from customers where is_approved is null");
			ResultSet rs = nullAccs.executeQuery();
			if (!rs.next()) {
				System.out.println("No pending accounts");
			}
			else {
				do {
					Integer acc = (Integer)rs.getInt(1);
					nullAccsList.add(acc);
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nullAccsList;
	}

	@Override
	public List<Integer> getCustApprovedAccNos(int cust_id) {
		List<Integer> approvAccNos = new ArrayList<Integer>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement appAccs = conn.prepareStatement("Select account_no from customers where cust_id = ? and is_approved = true");
			appAccs.setInt(1, cust_id);
			ResultSet rs = appAccs.executeQuery();
			if (!rs.next())
				System.out.println("No approved accounts");
			else {
				do {
					Integer acc = (Integer)rs.getInt(1);
					approvAccNos.add(acc);
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return approvAccNos;
	}

	
	



}
