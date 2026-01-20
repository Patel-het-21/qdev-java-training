package com.employeedatamanagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for employee report data.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReportDto {

	/**
	 * Unique identifier of the employee.
	 */
	private Long id;
	/**
	 * First name of the employee.
	 */
	private String firstName;
	/**
	 * Last name of the employee.
	 */
	private String lastName;
	/**
	 * Gender of the employee.
	 */
	private String gender;
	/**
	 * Mobile phone number of the employee.
	 */
	private String mobile;
	/**
	 * Age of the employee.
	 */
	private Integer age;
	/**
	 * Date of birth of the employee.
	 */
	private LocalDate dateOfBirth;
	/**
	 * Email address of the employee.
	 */
	private String email;

}