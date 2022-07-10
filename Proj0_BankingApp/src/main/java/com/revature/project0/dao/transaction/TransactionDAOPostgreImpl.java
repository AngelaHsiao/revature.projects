package com.revature.project0.dao.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.jdbc.utils.ConnUtils;
import com.revature.project0.user.Transaction;

public class TransactionDAOPostgreImpl implements TransactionDAO {

	@Override
	public Transaction getTransaction(int transact_id) {
		Transaction t = new Transaction();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchTransact = conn.prepareStatement("Select * from transactions where transaction_id = ?");
			fetchTransact.setInt(1, transact_id);
			ResultSet rs = fetchTransact.executeQuery();
			while (rs.next()) {
				t.setTransaction_id(rs.getInt(1));
				t.setAccount_no_in(rs.getInt(2));
				t.setAccount_no_out(rs.getInt(3));
				t.setTransact(rs.getDouble(4));
				t.setTr_approval(rs.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> allTrans = new ArrayList<Transaction>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchAllTrans = conn.prepareStatement("select * from transactions");
			ResultSet rs = fetchAllTrans.executeQuery();
			while (rs.next()) {
				Transaction t = new Transaction();
				t.setTransaction_id(rs.getInt(1));
				t.setAccount_no_in(rs.getInt(2));
				t.setAccount_no_out(rs.getInt(3));
				t.setTransact(rs.getDouble(4));				
				t.setTr_approval(rs.getBoolean(5));
				allTrans.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allTrans;
	}
	
	@Override
	public List<Transaction> getUnResolvedTransactions(int accNoOut){
		List<Transaction> listTran = new ArrayList<Transaction>();
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement fetchUnRes = conn.prepareStatement("select * from transactions where account_no_out =? and tr_approval is null");
			fetchUnRes.setInt(1, accNoOut);
			ResultSet rs = fetchUnRes.executeQuery();
			while(rs.next()) {
				Transaction t = new Transaction();
				t.setTransaction_id(rs.getInt(1));
				t.setAccount_no_in(rs.getInt(2));
				t.setAccount_no_out(rs.getInt(3));
				t.setTransact(rs.getDouble(4));				
				t.setTr_approval(rs.getBoolean(5));
				listTran.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTran;
	}

	@Override
	public void createTransaction(Transaction t) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			if (t.getAccount_no_out() != 0) {
			PreparedStatement insertTransaction = conn.prepareStatement("insert into transactions (account_no_in,account_no_out,transact) values (?,?,?)");
			insertTransaction.setInt(1, t.getAccount_no_in());
			insertTransaction.setInt(2, t.getAccount_no_out());
			insertTransaction.setDouble(3, t.getTransact());
			insertTransaction.execute();
			}
			else {
				PreparedStatement insertTransaction = conn.prepareStatement("insert into transactions (account_no_in,transact) values (?,?)");
				insertTransaction.setInt(1, t.getAccount_no_in());
				insertTransaction.setDouble(2, t.getTransact());
				insertTransaction.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateTransaction(Transaction t) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement updateTransaction = conn.prepareStatement("update transactions set tr_approval = ? where transaction_id = ?");
			updateTransaction.setBoolean(1, t.getTr_approval());
			updateTransaction.setInt(2, t.getTransaction_id());
			updateTransaction.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
