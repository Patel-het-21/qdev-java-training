/*
 * package com.employeedatamanagement.service.impl;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.employeedatamanagement.dto.LoginRequestDto; import
 * com.employeedatamanagement.dto.RegisterRequestDto; import
 * com.employeedatamanagement.entity.User; import
 * com.employeedatamanagement.repository.UserRepository; import
 * com.employeedatamanagement.service.AuthService;
 * 
 * import jakarta.servlet.http.HttpSession;
 * 
 * @Service public class AuthServiceImpl implements AuthService {
 * 
 * private final UserRepository userRepository; private final HttpSession
 * session;
 * 
 * @Autowired public AuthServiceImpl(UserRepository userRepository, HttpSession
 * session) { this.userRepository = userRepository; this.session = session; }
 * 
 * @Override public User register(RegisterRequestDto dto) { if
 * (userRepository.existsByEmail(dto.getEmail())) { throw new
 * IllegalArgumentException("Email already registered"); } if
 * (userRepository.existsByCompanyName(dto.getCompanyName())) { throw new
 * IllegalArgumentException("Company Name already taken"); }
 * 
 * User user = new User(); user.setCompanyName(dto.getCompanyName());
 * user.setEmail(dto.getEmail()); user.setPassword(dto.getPassword());
 * 
 * return userRepository.save(user); }
 * 
 * @Override public User login(LoginRequestDto dto) {
 * 
 * User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new
 * IllegalArgumentException("Invalid email or password"));
 * 
 * if (!user.getPassword().equals(dto.getPassword())) { throw new
 * IllegalArgumentException("Invalid email or password"); } // store user in
 * session session.setAttribute("LOGGED_IN_USER", user); return user; }
 * 
 * @Override public void logout() { session.invalidate(); }
 * 
 * }
 */

package com.employeedatamanagement.service.impl;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeedatamanagement.dto.LoginRequestDto;
import com.employeedatamanagement.dto.RegisterRequestDto;
import com.employeedatamanagement.entity.master.Tenant;
import com.employeedatamanagement.entity.master.User;
import com.employeedatamanagement.repository.master.TenantRepository;
import com.employeedatamanagement.repository.master.UserRepository;
import com.employeedatamanagement.service.AuthService;

import jakarta.servlet.http.HttpSession;

/**
 * @author Het Patel
 * @since 27/01/26
 * 
 * Service implementation for authentication and tenant management.
 * <p>
 * Handles user registration, login, logout, and tenant database creation
 * in a multi-tenant environment.
 * </p>
 */
@Service
public class AuthServiceImpl implements AuthService {

	/**
	 * Repository for managing users in the master schema.
	 */
	private final UserRepository userRepository;

	/**
	 * Repository for managing tenant mappings in the master schema.
	 */
	private final TenantRepository tenantRepository;

	/**
	 * HTTP session for storing authenticated user and tenant context.
	 */
	private final HttpSession session;

	/**
	 * DataSource used to create tenant databases dynamically.
	 */
	private final DataSource dataSource;

	/**
	 * Constructs AuthServiceImpl with required dependencies.
	 * 
	 * @param userRepository repository for user operations
	 * @param tenantRepository repository for tenant operations
	 * @param session HTTP session for storing login context
	 * @param dataSource datasource for database connections
	 */
	@Autowired
	public AuthServiceImpl(UserRepository userRepository, TenantRepository tenantRepository, HttpSession session,
			DataSource dataSource) {
		this.userRepository = userRepository;
		this.tenantRepository = tenantRepository;
		this.session = session;
		this.dataSource = dataSource;
	}

	/**
	 * Registers a new user and creates a dedicated tenant database.
	 * <p>
	 * This method:
	 * <ul>
	 *   <li>Validates uniqueness of email and company name</li>
	 *   <li>Stores the user in the master schema</li>
	 *   <li>Creates a new tenant database</li>
	 *   <li>Saves tenant metadata in the master schema</li>
	 * </ul>
	 * </p>
	 * 
	 * @param dto registration request data
	 * @return the saved user entity
	 */
	@Override
	public User register(RegisterRequestDto dto) {

		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
		if (userRepository.existsByCompanyName(dto.getCompanyName())) {
			throw new IllegalArgumentException("Company Name already taken");
		}

		// 1. Save user in master schema
		User user = new User();
		user.setCompanyName(dto.getCompanyName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword()); // later: BCrypt
		User savedUser = userRepository.save(user);

		// 2. Generate tenant schema name
		String schemaName = generateSchemaName(dto.getCompanyName());

		// 3. Create tenant database (MySQL schema)
		createTenantDatabase(schemaName);

		// 4. Save tenant mapping in master table
		Tenant tenant = new Tenant();
		tenant.setCompanyName(dto.getCompanyName());
		tenant.setSchemaName(schemaName);
		tenantRepository.save(tenant);

		return savedUser;
	}

	/**
	 * Authenticates a user and initializes tenant context in session.
	 * 
	 * @param dto login request data
	 * @return authenticated user
	 */
	@Override
	public User login(LoginRequestDto dto) {

		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

		if (!user.getPassword().equals(dto.getPassword())) {
			throw new IllegalArgumentException("Invalid email or password");
		}

		// Resolve tenant
		Tenant tenant = tenantRepository.findByCompanyName(user.getCompanyName())
						.orElseThrow(() -> new IllegalStateException("Tenant not found"));

		// Store session data
		session.setAttribute("LOGGED_IN_USER", user);
		session.setAttribute("TENANT", tenant.getSchemaName());

		return user;
	}

	/**
	 * Logs out the current user and invalidates the session.
	 */
	@Override
	public void logout() {
		session.invalidate();
	}

	/**
	 * Creates a tenant database and initializes required tables.
	 * 
	 * @param schemaName name of the tenant database schema
	 */
	private void createTenantDatabase(String schemaName) {
		try (Connection connection = dataSource.getConnection(); Statement stmt = connection.createStatement()) {
			stmt.execute("CREATE DATABASE IF NOT EXISTS " + schemaName);
			stmt.execute("USE " + schemaName);
			stmt.execute("CREATE TABLE IF NOT EXISTS employee (id BIGINT NOT NULL AUTO_INCREMENT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, date_of_birth DATE NOT NULL, mobile VARCHAR(10) NOT NULL, email VARCHAR(255) NOT NULL, address1 VARCHAR(255) NOT NULL, address2 VARCHAR(255), age INT NOT NULL, gender VARCHAR(10) NOT NULL, PRIMARY KEY (id), UNIQUE KEY uk_employee_mobile (mobile), UNIQUE KEY uk_employee_email (email))");
		} catch (Exception e) {
			throw new RuntimeException("Failed to create tenant database: " + schemaName, e);
		}
	}

	/**
	 * Generates a normalized tenant schema name from company name.
	 * 
	 * @param companyName the company name
	 * @return generated tenant schema name
	 */
	private String generateSchemaName(String companyName) {
		return "tenant_" + companyName.toLowerCase().replaceAll("[^a-z0-9]", "_");
	}

	/**
	 * Checks if a company name already exists.
	 * 
	 * @param companyName company name to check
	 * @return true if company exists, false otherwise
	 */
	@Override
	public boolean companyExists(String companyName) {
	    return userRepository.existsByCompanyName(companyName);
	}

	/**
	 * Checks if an email already exists.
	 * 
	 * @param email email to check
	 * @return true if email exists, false otherwise
	 */
	@Override
	public boolean emailExists(String email) {
	    return userRepository.existsByEmail(email);
	}

}