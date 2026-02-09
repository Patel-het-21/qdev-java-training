package com.employeedatamanagement.service;

import java.util.List;

import com.employeedatamanagement.dto.EmployeeRequestDto;
import com.employeedatamanagement.dto.EmployeeResponseDto;

import jakarta.servlet.http.HttpSession;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Service interface for managing employee operations.
 */
public interface EmployeeService {

	/**
	 * Creates a new employee.
	 *
	 * @param dto the employee request data
	 * @return the created employee response
	 */
	EmployeeResponseDto createEmployee(EmployeeRequestDto dto);

	/**
	 * Updates an existing employee.
	 *
	 * @param id  the employee ID
	 * @param dto the updated employee data
	 * @return the updated employee response
	 */
	EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto);

	/**
	 * Retrieves an employee by ID.
	 *
	 * @param id the employee ID
	 * @return the employee details
	 */
	EmployeeResponseDto getEmployeeById(Long id);

	/**
	 * Retrieves all employees.
	 *
	 * @return a list of all employee responses
	 */
	List<EmployeeResponseDto> getAllEmployees();

	/**
	 * Deletes an employee by ID.
	 *
	 * @param id the employee ID
	 */
	void deleteEmployee(Long id);

	/**
	 * Searches employees by name.
	 * <p>
	 * Supports partial and case-insensitive matches.
	 * </p>
	 *
	 * @param name the name or partial name to search
	 * @return a list of matching employees
	 */
	List<EmployeeResponseDto> searchByName(String name);

	/**
	 * Checks whether an email address is already used by another employee.
	 * <p>
	 * The specified employee ID is excluded from the check, typically used during
	 * update operations.
	 * </p>
	 *
	 * @param email the email address to check
	 * @param excludingId the employee ID to exclude (nullable)
	 * @return true if the email is already taken, false otherwise
	 */
	boolean isEmailTaken(String email, Long excludingId);

	/**
	 * Checks whether a mobile number is already used by another employee.
	 *
	 * @param mobile the mobile number to check
	 * @param excludingId the employee ID to exclude (nullable)
	 * @return true if the mobile number is already taken, false otherwise
	 */
	boolean isMobileTaken(String mobile, Long excludingId);

}