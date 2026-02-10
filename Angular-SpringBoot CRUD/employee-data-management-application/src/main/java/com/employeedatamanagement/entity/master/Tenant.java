package com.employeedatamanagement.entity.master;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entity representing a tenant in the multi-tenant system.
 * <p>
 * Each tenant corresponds to a company and is mapped to a unique
 * database/schema used for isolating tenant data.
 * </p>
 */
@Entity
@Table(name = "tenants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {

	/**
	 * Primary key of the tenants table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Unique company name associated with the tenant.
	 * <p>
	 * This value is used to identify the tenant at the business level.
	 * </p>
	 */
	@NotBlank
	@Size(max = 255)
	@Column(name = "companyname", unique = true, nullable = false)
	private String companyName;

	/**
	 * Database schema name assigned to the tenant.
	 * <p>
	 * This schema is used internally for multi-tenant database routing.
	 * </p>
	 */
	@NotBlank
	@Size(max = 255)
	@Column(name = "schema_name", unique = true, nullable = false)
	private String schemaName;

}