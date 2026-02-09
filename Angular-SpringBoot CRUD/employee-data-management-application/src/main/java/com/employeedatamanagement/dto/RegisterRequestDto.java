package com.employeedatamanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) used for user/company registration requests.
 * <p>
 * This DTO carries the necessary information required to register
 * a new company and user in the system.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

	/**
	 * Name of the company being registered.
	 * <p>
	 * Must not be blank and must be between 2 and 100 characters.
	 * </p>
	 */
	@NotBlank(message = "Company name is required")
	@Size(min = 2, max = 100)
	private String companyName;

	/**
	 * Password for the newly registered user.
	 * <p>
	 * Must contain at least 8 characters and cannot be blank.
	 * </p>
	 */
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;

	/**
	 * Email address for the newly registered user.
	 * <p>
	 * Must be a valid email format and cannot be blank.
	 * </p>
	 */
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

}