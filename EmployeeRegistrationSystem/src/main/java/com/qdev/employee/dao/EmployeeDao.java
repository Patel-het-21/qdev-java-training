package com.qdev.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qdev.employee.model.Employee;
import com.qdev.employee.util.DBUtil;

/**
 * Data Access Object (DAO) responsible for handling all CRUD operations related
 * to the {@link Employee} entity. This class interacts directly with the
 * database to perform insert, update, delete, and search operations.
 * 
 * Provides database utility methods such as checking unique fields (username,
 * contact number) with and without excluding the current record.
 * 
 * @author Het
 * @since 06/11/2025
 */
public class EmployeeDao {

	/**
	 * Inserts a new employee record into the database.
	 * 
	 * @param employee the {@link Employee} object containing details to be inserted
	 * @return number of rows affected (1 if successful, 0 if failed)
	 */
	public int saveEmployee(Employee employee) {
		int rowInserted = 0;
		String insertEmployee = "INSERT INTO employee (firstName, lastName, userName, password, address, contactNo) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertEmployee)) {
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUserName());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContactNo());
			rowInserted = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowInserted;
	}

	/**
	 * Updates an existing employee's details.
	 *
	 * @param employee the {@link Employee} object containing updated values
	 * @return number of rows affected (1 if update successful, 0 otherwise)
	 */
	public int updateEmployee(Employee employee) {
		String updateEmployee = "UPDATE employee SET firstName = ?, lastName = ?, userName = ?, password = ?, address = ?, contactNo = ? WHERE id = ?";
		try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(updateEmployee)) {
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUserName());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContactNo());
			preparedStatement.setInt(7, employee.getId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Deletes an employee based on the provided ID.
	 *
	 * @param id the employee ID to delete
	 * @return number of rows affected (1 if deleted successfully, 0 otherwise)
	 */
	public int deleteEmployee(int id) {
		String deleteEmployee = "DELETE FROM employee WHERE id = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleteEmployee)) {
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Fetches and returns all employees from the database.
	 *
	 * @return a {@link List} of {@link Employee} objects
	 */
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		String query = "SELECT * FROM employee";
		try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				employee.setUserName(resultSet.getString("userName"));
				employee.setPassword(resultSet.getString("password"));
				employee.setAddress(resultSet.getString("address"));
				employee.setContactNo(resultSet.getString("contactNo"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	/**
	 * Retrieves a single employee based on ID.
	 *
	 * @param id the unique employee ID
	 * @return an {@link Employee} object if found, otherwise null
	 */
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		String query = "SELECT * FROM employee WHERE id = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					employee = new Employee();
					employee.setId(resultSet.getInt("id"));
					employee.setFirstName(resultSet.getString("firstName"));
					employee.setLastName(resultSet.getString("lastName"));
					employee.setUserName(resultSet.getString("userName"));
					employee.setPassword(resultSet.getString("password"));
					employee.setAddress(resultSet.getString("address"));
					employee.setContactNo(resultSet.getString("contactNo"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	/**
	 * Checks whether a username already exists in the database.
	 *
	 * @param userName the username to check
	 * @return true if username exists, false otherwise
	 */
	public boolean checkUserName(String userName) {
		String query = "SELECT COUNT(*) FROM employee WHERE userName = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, userName);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				resultSet.next();
				return resultSet.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Checks whether a username exists, excluding the current employee ID (used
	 * during update operation).
	 *
	 * @param userName  the username to check
	 * @param currentId the ID of the employee being updated
	 * @return true if username exists for another employee, false otherwise
	 */
	public boolean checkUserName(String userName, int currentId) {
		String query = "SELECT COUNT(*) FROM employee WHERE userName = ? AND id != ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, userName);
			preparedStatement.setInt(2, currentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Checks whether a contact number already exists in the database.
	 *
	 * @param contactNumber the contact number to check
	 * @return true if contact number exists, false otherwise
	 */
	public boolean checkContactNumber(String contactNumber) {
		String query = "SELECT COUNT(*) FROM employee WHERE contactNo = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, contactNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Checks whether a contact number exists for any other employee except the one
	 * currently being updated.
	 *
	 * @param contactNumber the contact number to validate
	 * @param currentId     the employee ID to exclude
	 * @return true if contact number exists for another employee, false otherwise
	 */
	public boolean checkContactNumber(String contactNumber, int currentId) {
		String query = "SELECT COUNT(*) FROM employee WHERE contactNo = ? AND id != ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, contactNumber);
			preparedStatement.setInt(2, currentId);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}