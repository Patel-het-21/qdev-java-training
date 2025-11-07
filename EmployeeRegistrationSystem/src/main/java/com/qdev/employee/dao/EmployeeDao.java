package com.qdev.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qdev.employee.model.Employee;

/**
 * Data Access Object (DAO) class responsible for performing database operations
 * related to the {@link Employee} entity. This class provides methods for
 * inserting employee details into the database.
 * 
 * @author Het
 * @since 6/11/25
 */
public class EmployeeDao {

	/** JDBC driver class for MySQL database connection. */
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

	/** JDBC URL to connect to the MySQL employee database. */
	private String jdbcURL = "jdbc:mysql://localhost:3306/employee_db";

	/** Username used to connect to the database. */
	private String jdbcUserName = "root";

	/** Password used to connect to the database. */
	private String jdbcPassword = "root";

	/**
	 * Saves the given {@link Employee} object to the database.
	 * 
	 * @param employee the {@link Employee} object containing details to be stored
	 *                 in the database
	 * @return an integer representing the number of rows inserted (1 if successful,
	 *         0 otherwise)
	 */
	public int saveEmployee(Employee employee) {
		int rowInserted = 0;
		String insertEmployee = "INSERT INTO employee (firstName, lastName, userName, password, address, contactNo) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			/**
			 * Load JDBC driver
			 */
			Class.forName(jdbcDriver);

			/**
			 * Establish database connection
			 */
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);

			/**
			 * Create a prepared statement to prevent SQL injection
			 */
			PreparedStatement preparedStatement = connection.prepareStatement(insertEmployee);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUserName());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContactNo());

			/**
			 * Execute update and store result
			 */
			rowInserted = preparedStatement.executeUpdate();

			/**
			 * Close resources
			 */
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			/**
			 * Thrown when JDBC driver class is not found
			 */
			e.printStackTrace();
		} catch (SQLException e) {
			/**
			 * Thrown for any SQL-related errors
			 */
			e.printStackTrace();
		}

		return rowInserted;
	}

}