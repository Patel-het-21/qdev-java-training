/**
 * 
 */
package com.qdev.employee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections. Loads the JDBC driver once
 * and provides a reusable method to get a database connection.
 * 
 * @author het
 * @since 10/11/25
 */
public class DBUtil {
	/**
	 * JDBC URL to connect to the MySQL employee database.
	 */
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee_db";
	/**
	 * Username used to connect to the database.
	 */
	private static final String JDBC_USER = "root";
	/**
	 * Password used to connect to the database.
	 */
	private static final String JDBC_PASSWORD = "root";

	static {
		try {
			/**
			 * Load JDBC driver
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Provides a new {@link Connection} object to the database.
	 * 
	 * @return a {@link Connection} to the database
	 * @throws SQLException if a database access error occurs
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
}