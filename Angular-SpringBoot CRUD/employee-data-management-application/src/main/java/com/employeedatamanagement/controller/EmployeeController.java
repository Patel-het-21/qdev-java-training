package com.employeedatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeedatamanagement.dto.EmployeeRequestDto;
import com.employeedatamanagement.dto.EmployeeResponseDto;
import com.employeedatamanagement.service.EmployeeService;

import jakarta.validation.Valid;

/**
 * @author Het Patel
 * @since 11/12/2025
 * 
 * REST controller for managing employee-related operations.
 */
@RestController
@RequestMapping("/api/v1/employees")
@Validated
@CrossOrigin(
	    origins = {
	        "http://localhost:4200",
	        "http://localhost:9090"
	    },
	    allowCredentials = "true"
	)
public class EmployeeController {

	private final EmployeeService service;

	/**
	 * Constructs the EmployeeController with the required service.
	 *
	 * @param service the employee service layer
	 */
	@Autowired
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	/**
	 * Creates a new employee.
	 *
	 * @param dto the employee request data
	 * @return the created employee details
	 */
	@PostMapping
	public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
		EmployeeResponseDto created = service.createEmployee(dto);
		return ResponseEntity.ok(created);
	}

	/**
	 * Updates an existing employee by ID.
	 *
	 * @param id  the employee ID
	 * @param dto the updated employee data
	 * @return the updated employee details
	 */
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id,
			@Valid @RequestBody EmployeeRequestDto dto) {
		EmployeeResponseDto updated = service.updateEmployee(id, dto);
		return ResponseEntity.ok(updated);
	}

	/**
	 * Retrieves all employees.
	 *
	 * @return a list of all employees
	 */
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> getAll() {
		return ResponseEntity.ok(service.getAllEmployees());
	}

	/**
	 * Retrieves an employee by ID.
	 *
	 * @param id the employee ID
	 * @return the employee details
	 */
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getEmployeeById(id));
	}

	/**
	 * Deletes an employee by ID.
	 *
	 * @param id the employee ID
	 * @return HTTP 204 No Content on successful deletion
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Searches employees by name.
	 *
	 * @param name the employee name or partial name
	 * @return a list of matching employees
	 */
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeResponseDto>> search(@RequestParam("name") String name) {
		return ResponseEntity.ok(service.searchByName(name));
	}

	/**
	 * Checks whether an email is already used by another employee.
	 *
	 * @param email the email address to check
	 * @param id    the employee ID (optional)
	 * @return true if the email is already taken, false otherwise
	 */
	@GetMapping("/check-email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam String email, @RequestParam(required = false) Long id) {
		return ResponseEntity.ok(service.isEmailTaken(email, id));
	}

	/**
	 * Checks whether a mobile number is already used by another employee.
	 *
	 * @param mobile the mobile number to check
	 * @param id     the employee ID (optional)
	 * @return true if the mobile number is already taken, false otherwise
	 */
	@GetMapping("/check-mobile")
	public ResponseEntity<Boolean> checkMobile(@RequestParam String mobile, @RequestParam(required = false) Long id) {
		return ResponseEntity.ok(service.isMobileTaken(mobile, id));
	}

}