package com.revature.project0.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtils {
private Connection conn;
private static ConnUtils connUtils;

public Connection getConn() {
	return conn;
}

private ConnUtils() throws SQLException {
	String url = System.getenv("DB_URL");
	String dbName = "proj0BankApp";
	String user = System.getenv("DB_USER");
	String pwd = System.getenv("DB_PASS");
	
	try {
		Class.forName("org.postgresql.Driver");
		this.conn = DriverManager.getConnection(url+dbName,user,pwd);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Failed to connect");
	}
}

public static ConnUtils getInstance() throws SQLException {
	if (connUtils == null) {
		connUtils = new ConnUtils();
	}
	else if (connUtils.getConn().isClosed()) {
		connUtils = new ConnUtils();
	}
	return connUtils;
}
}//end class
