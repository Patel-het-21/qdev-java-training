package com.employeedatamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) used to send user-related information
 * back to the client in API responses.
 * <p>
 * This DTO contains basic user details along with the associated
 * tenant schema name used for multi-tenant database operations.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	/**
	 * Unique identifier of the user.
	 */
	private Long id;

	/**
	 * Name of the company associated with the user.
	 */
	private String companyName;

	/**
	 * Email address of the user.
	 */
	private String email;

	/**
	 * Tenant schema/database name assigned to the company.
	 */
	private String tenantSchema;

}