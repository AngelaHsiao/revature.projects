package com.revature.project0.dao.user;

import java.util.List;

import com.revature.project0.exceptions.UserInvalidException;
import com.revature.project0.user.User;


public interface UserDAO {

	public User getUser(int id); //get user based on user id
	
	public User getUser(String username) throws UserInvalidException; //get user based on username
	
	public List<User> getAllUsers(); //get all users
	
	public void createUser(User u); // create new User and add to db
	
	public void updateUser(int id); //update User
	
	public void deleteUser(int id); //delete User
}
