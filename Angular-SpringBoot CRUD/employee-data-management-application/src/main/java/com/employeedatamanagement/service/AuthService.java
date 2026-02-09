package com.employeedatamanagement.service;

import com.employeedatamanagement.dto.LoginRequestDto;
import com.employeedatamanagement.dto.RegisterRequestDto;
import com.employeedatamanagement.entity.master.User;

/**
 * @author Het Patel
 * @since 27/01/26
 * 
 * Service interface for authentication and user management operations.
 * <p>
 * Defines contract for user registration, login, logout, and validation checks
 * used in a multi-tenant environment.
 * </p>
 */
public interface AuthService {

	/**
	 * Registers a new user in the system.
	 * 
	 * @param dto registration request data
	 * @return the registered user entity
	 */
	User register(RegisterRequestDto dto);

	/**
	 * Authenticates a user using login credentials.
	 * 
	 * @param dto login request data
	 * @return authenticated user entity
	 */
	User login(LoginRequestDto dto);

	/**
	 * Logs out the currently authenticated user.
	 */
	void logout();
	
	/**
	 * Checks whether a company name already exists in the system.
	 * 
	 * @param companyName company name to check
	 * @return true if company exists, false otherwise
	 */
	boolean companyExists(String companyName);

	/**
	 * Checks whether an email already exists in the system.
	 * 
	 * @param email email address to check
	 * @return true if email exists, false otherwise
	 */
	boolean emailExists(String email);

}