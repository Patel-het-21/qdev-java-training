package com.employeedatamanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.employeedatamanagement.dto.LoginRequestDto;
import com.employeedatamanagement.dto.RegisterRequestDto;
import com.employeedatamanagement.dto.UserResponseDto;
import com.employeedatamanagement.entity.master.Tenant;
import com.employeedatamanagement.entity.master.User;
import com.employeedatamanagement.repository.master.TenantRepository;
import com.employeedatamanagement.service.AuthService;

import jakarta.validation.Valid;

/**
 * REST controller responsible for handling authentication-related APIs.
 * <p>
 * This controller exposes endpoints for user registration, login, logout,
 * and validation checks for existing company names and email addresses.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/auth")
@Validated
@CrossOrigin(
	    origins = {
	        "http://localhost:4200",
	        "http://localhost:9090"
	    },
	    allowCredentials = "true"
	)public class AuthController {

	/**
	 * Service responsible for authentication and user-related operations.
	 */
	private final AuthService authService;

	/**
	 * Repository used to resolve tenant information for a given company.
	 */
    private final TenantRepository tenantRepository;

	/**
	 * Constructor-based dependency injection for {@link AuthController}.
	 *
	 * @param authService service handling authentication logic
	 * @param tenantRepository repository used to fetch tenant details
	 */
	@Autowired
	public AuthController(AuthService authService,TenantRepository tenantRepository) {
		this.authService = authService;
		this.tenantRepository = tenantRepository;
	}

	/**
	 * Registers a new company/user in the system.
	 * <p>
	 * This endpoint creates a new user in the master database and initializes
	 * the tenant database/schema for the company.
	 * </p>
	 *
	 * @param dto registration request containing company name, email, and password
	 * @return response containing user details and tenant schema name
	 */
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequestDto dto) {
		User user = authService.register(dto);
		//return ResponseEntity.ok(user);
		 Tenant tenant = tenantRepository.findByCompanyName(user.getCompanyName()).orElseThrow(() -> new IllegalStateException("Tenant not found"));
		return ResponseEntity.ok(
	            new UserResponseDto(user.getId(), user.getCompanyName(), user.getEmail(),tenant.getSchemaName())
	        );
	}

	/**
	 * Authenticates a user using email and password.
	 * <p>
	 * On successful authentication, the tenant schema is resolved and
	 * returned along with user details.
	 * </p>
	 *
	 * @param dto login request containing email and password
	 * @return response containing user details and tenant schema name
	 */
	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
		User user = authService.login(dto);
		//return ResponseEntity.ok("Login Success");
	    Tenant tenant = tenantRepository.findByCompanyName(user.getCompanyName()).orElseThrow(() -> new IllegalStateException("Tenant not found"));
		return ResponseEntity.ok(	
	            new UserResponseDto(user.getId(), user.getCompanyName(), user.getEmail(),tenant.getSchemaName())
	        );
	}

	/**
	 * Logs out the currently authenticated user.
	 * <p>
	 * This typically clears the server-side session associated with the user.
	 * </p>
	 *
	 * @return confirmation message indicating successful logout
	 */
	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		authService.logout();
		//return ResponseEntity.ok().build();
		return ResponseEntity.ok("Logged out successfully");
	}
	
	/**
	 * Checks whether a company name already exists in the system.
	 *
	 * @param companyName the company name to validate
	 * @return {@code true} if the company name already exists, otherwise {@code false}
	 */
	@GetMapping("/check-company")
	public ResponseEntity<Boolean> checkCompanyExists(@RequestParam String companyName) {
	    boolean exists = authService.companyExists(companyName);
	    return ResponseEntity.ok(exists);
	}

	/**
	 * Checks whether an email address is already registered in the system.
	 *
	 * @param email the email address to validate
	 * @return {@code true} if the email already exists, otherwise {@code false}
	 */
	@GetMapping("/check-email")
	public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
	    boolean exists = authService.emailExists(email);
	    return ResponseEntity.ok(exists);
	}

}