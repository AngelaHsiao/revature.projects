package com.revature.project0.functionality;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.dao.account.AccountDAOPostgreImpl;
import com.revature.project0.dao.customer.CustomerDAOPostgreImpl;
import com.revature.project0.dao.user.UserDAOPostgreImpl;
import com.revature.project0.exceptions.UserInvalidException;
import com.revature.project0.user.Account;
import com.revature.project0.user.Customer;
import com.revature.project0.user.User;

public class Login {
private static User user;

	public static boolean loginToAccount() {
		boolean isLog = false;
		System.out.println("----Login Page----");
		UserDAOPostgreImpl userDAO = new UserDAOPostgreImpl();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter Username: ");
			String username = sc.nextLine();
			User u = userDAO.getUser(username);
			System.out.println("Enter Password: ");
			String pwd = sc.nextLine();
			if (u.getPassword().equals(pwd)) {
				System.out.println("----Welcome User!----");
				isLog = true;
				user = u;
			}
			else {
				throw new UserInvalidException();
			}
		}catch(UserInvalidException uie) {
			System.out.println(uie.toString());
		}
		return isLog;
	}
	
	public static User getUser() {
		if (user==null) {
			loginToAccount();
		}
		return user;
	}


}//end class
