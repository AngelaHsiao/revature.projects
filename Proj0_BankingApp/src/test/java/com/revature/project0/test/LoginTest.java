package com.revature.project0.test;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.project0.functionality.Login;

public class LoginTest {
	

	@Test
	public void LoginTest1() {
		System.out.println("Testing Login");
		boolean result = Login.loginToAccount();
		Assertions.assertEquals(true, result);
	}
}
