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
 * Data Access Object (DAO) class responsible for performing database operations
 * related to the {@link Employee} entity. This class provides methods for
 * inserting employee details into the database.
 * 
 * @author Het
 * @since 6/11/25
 */
public class EmployeeDao {

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
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertEmployee)) {

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
		} catch (SQLException e) {
			/**
			 * Thrown for any SQL-related errors
			 */
			e.printStackTrace();
		}
		return rowInserted;
	}

	public int updateEmployee(Employee employee) {
		String updateEmployee = "Update employee set firstName = ?, lastName = ?, userName = ?, password = ?, address = ?, contactNo = ? where id = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateEmployee)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUserName());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContactNo());
			preparedStatement.setInt(7, employee.getId());

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteEmployee(int id) {
		String deleEmployee = "Delete from employee where id = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleEmployee)) {

			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		String getEmployee = "Select * from employee";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(getEmployee)) {

			ResultSet resultSet = preparedStatement.executeQuery();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}

	public Employee getEmployeeId(int id) {
		Employee employee = null;
		String getEmployee = "Select * from employee where id = ?";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(getEmployee)) {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}

}