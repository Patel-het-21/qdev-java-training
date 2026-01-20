package com.employeedatamanagement.dto;

import java.time.LocalDate;

import com.employeedatamanagement.validation.AgeRange;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Data Transfer Object (DTO) used for employee create and update requests.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {

	/**
	 * Employee's first name.
	 */
	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[A-Za-z ]+$", message = "First name must contain only alphabets and spaces")
	private String firstName;
	/**
	 * Employee's last name.
	 */
	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Last name must contain only alphabets and spaces")
	private String lastName;
	/**
	 * Employee's date of birth.
	 * <p>
	 * Age must be between 18 and 110 years.
	 * </p>
	 */
	@NotNull(message = "Date of birth is required")
	@AgeRange(min = 18, max = 110, message = "Age must be between 18 and 110")
	private LocalDate dateOfBirth;
	/**
	 * Employee's mobile number.
	 */
	@NotBlank(message = "Mobile is required")
	@Pattern(regexp = "^[1-9][0-9]{9}$", message = "Mobile must be 10 digits and not start with 0")
	private String mobile;
	/**
	 * Primary address of the employee.
	 */
	@NotBlank(message = "Address1 is required")
	@Size(max = 255)
	private String address1;
	/**
	 * Secondary address of the employee (optional).
	 */
	@Size(max = 255)
	private String address2;
	/**
	 * Employee's gender.
	 */
	@NotBlank(message = "Gender is required")
	private String gender;
	/**
	 * Employee's email address.
	 */
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 254)
	private String email;

}