package com.sudha;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection implements ProjectConfig {
	static Connection conn = null;

	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(connUrl, username, pwd);
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}
}