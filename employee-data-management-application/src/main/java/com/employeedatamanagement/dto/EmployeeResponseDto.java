package com.employeedatamanagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Data Transfer Object (DTO) used to send employee data in API responses.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {

	/**
	 * Unique identifier of the employee.
	 */
	private Long id;
	/**
	 * Employee's first name.
	 */
	private String firstName;
	/**
	 * Employee's last name.
	 */
	private String lastName;
	/**
	 * Employee's date of birth.
	 */
	private LocalDate dateOfBirth;
	/**
	 * Employee's calculated age.
	 */
	private int age;
	/**
	 * Employee's mobile number.
	 */
	private String mobile;
	/**
	 * Employee's email address.
	 */
	private String email;
	/**
	 * Employee's gender.
	 */
	private String gender;
	/**
	 * Employee's primary address.
	 */
	private String address1;
	/**
	 * Employee's secondary address.
	 */
	private String address2;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}