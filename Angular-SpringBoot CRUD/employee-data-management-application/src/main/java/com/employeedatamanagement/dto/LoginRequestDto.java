package com.employeedatamanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) used for login requests.
 * <p>
 * This DTO carries user credentials from the client to the server
 * during the authentication (login) process.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

	/**
	 * Email address of the user attempting to log in.
	 * <p>
	 * Must be a valid email format and cannot be blank.
	 * </p>
	 */
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	/**
	 * Password provided by the user for authentication.
	 * <p>
	 * Must contain at least 8 characters and cannot be blank.
	 * </p>
	 */
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;

}