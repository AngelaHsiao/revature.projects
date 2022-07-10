package com.revature.project0.user;

public class User {
	private int user_id;
private String username;
private String password;
private String user_type;

public User(int user_id, String username, String password, String user_type) {
	super();
	this.user_id = user_id;
	this.username = username;
	this.password = password;
	this.user_type = user_type;
}
public User(int user_id) {
	super();
	this.user_id=user_id;
}
public User() {
	super();
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUser_type() {
	return user_type;
}
public void setUser_type(String user_type) {
	this.user_type = user_type;
}
@Override
public String toString() {
	return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", user_type=" + user_type
			+ "]";
}


}
