package com.employeedatamanagement.repository.master;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeedatamanagement.entity.master.Tenant;

/**
 * Repository interface for performing CRUD operations on {@link Tenant} entities.
 * <p>
 * This repository is backed by the master database and is responsible for
 * managing tenant metadata such as company names and schema/database mappings.
 * </p>
 */
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	/**
	 * Finds a tenant by its associated company name.
	 *
	 * @param companyName the company name linked to the tenant
	 * @return an {@link Optional} containing the tenant if found, otherwise empty
	 */
	Optional<Tenant> findByCompanyName(String companyName);

	/**
	 * Finds a tenant by its schema/database name.
	 *
	 * @param schemaName the schema name associated with the tenant
	 * @return an {@link Optional} containing the tenant if found, otherwise empty
	 */
	Optional<Tenant> findBySchemaName(String schemaName);

	/**
	 * Checks whether a tenant exists with the given company name.
	 *
	 * @param companyName the company name to check
	 * @return {@code true} if a tenant exists with the given company name, otherwise {@code false}
	 */
	boolean existsByCompanyName(String companyName);

	/**
	 * Checks whether a tenant exists with the given schema/database name.
	 *
	 * @param schemaName the schema name to check
	 * @return {@code true} if a tenant exists with the given schema name, otherwise {@code false}
	 */
	boolean existsBySchemaName(String schemaName);

}