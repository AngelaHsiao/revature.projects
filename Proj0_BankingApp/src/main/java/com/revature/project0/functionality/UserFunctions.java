package com.revature.project0.functionality;

import java.util.Scanner;

import com.revature.project0.dao.user.UserDAOPostgreImpl;
import com.revature.project0.user.User;

public class UserFunctions {
	
public static void registerCustAccount() {
	UserDAOPostgreImpl userDAO = new UserDAOPostgreImpl();
	
	User user = Login.getUser();
	if (user.getUser_type().equals("User")) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Type Y to confirm customer account registration");
		String in = sc.nextLine();
		if (in.equals("Y")) {
			userDAO.updateUser(user.getUser_id());
			System.out.println("Registration Complete!");
		}
		else {
			System.out.println("Registration Cancelled...");
		}
	}
	else {
		System.out.println("Invalid option");
	}
}
}
