package com.revature.project0.dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.jdbc.utils.ConnUtils;
import com.revature.project0.user.Account;

public class AccountDAOPostgreImpl implements AccountDAO {

	@Override
	public Account getAccount(int account_no) {
		Account a = new Account();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchAccount = conn.prepareStatement("select * from accounts where account_no =?");
			fetchAccount.setInt(1, account_no);
			ResultSet rs = fetchAccount.executeQuery();
			while(rs.next()) {
				a.setAccount_no(rs.getInt(1));
				a.setBalance(rs.getDouble(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> allAccs = new ArrayList<Account>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchAllAccs = conn.prepareStatement("select * from accounts");
			ResultSet rs = fetchAllAccs.executeQuery();
			while (rs.next()){
				Account a = new Account(rs.getInt(1),rs.getDouble(2));
				allAccs.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allAccs;
	}
	
	@Override
	public List<Integer> getAllAccountNos(){
		List<Integer> allAccNos = new ArrayList<Integer>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchAccNos = conn.prepareStatement("select account_no from accounts");
			ResultSet rs = fetchAccNos.executeQuery();
			while (rs.next()) {
				Integer i = (Integer) rs.getInt(1);
				allAccNos.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allAccNos;
	}

	@Override
	public void updateAccountBalance(int account_no, double balance) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement updateBal = conn.prepareStatement("update accounts set balance =? where account_no=?");
			updateBal.setDouble(1, balance);
			updateBal.setInt(2, account_no);
			updateBal.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void insertAccount(Account a) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement insertAcc = conn.prepareStatement("insert into accounts (account_no,balance) values (?,?)");
			insertAcc.setInt(1, a.getAccount_no());
			insertAcc.setDouble(2,a.getBalance());
			insertAcc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
