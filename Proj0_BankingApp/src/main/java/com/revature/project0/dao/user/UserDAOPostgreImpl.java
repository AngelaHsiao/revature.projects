package com.revature.project0.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.project0.exceptions.UserInvalidException;
import com.revature.project0.jdbc.utils.ConnUtils;
import com.revature.project0.user.User;

public class UserDAOPostgreImpl implements UserDAO{

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User getUser(String username) throws UserInvalidException{
		User u = new User();
		try {
			Connection connect = ConnUtils.getInstance().getConn();
			PreparedStatement fetchUserByName = connect.prepareStatement("select * from users where username = ?");
			fetchUserByName.setString(1, username);
			ResultSet rs = fetchUserByName.executeQuery();
			if (!rs.next()) {
				throw new UserInvalidException();
			}
			else {
			do {
				u.setUser_id(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setUser_type(rs.getString(4));
			}while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(int id) {
		try {
			Connection conn = ConnUtils.getInstance().getConn();
			PreparedStatement updateUserType = conn.prepareStatement("update users set user_type = 'Customer' where user_id =?");
			updateUserType.setInt(1, id);
			updateUserType.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	

}
