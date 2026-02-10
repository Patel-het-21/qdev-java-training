package com.employeedatamanagement.config.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

/**
 * Resolver responsible for determining the current tenant identifier
 * for Hibernate multi-tenancy.
 * <p>
 * This implementation retrieves the tenant identifier from {@link TenantContext}.
 * If no tenant is available, it falls back to the default (master) database.
 * </p>
 */
@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	/**
	 * Default tenant identifier used when no tenant is present in the context.
	 * <p>
	 * This typically points to the master database schema.
	 * </p>
	 */
	private static final String DEFAULT_TENANT = "employee_spring"; // master DB

	/**
	 * Resolves the current tenant identifier.
	 * <p>
	 * This method is called by Hibernate to determine which tenant (database/schema)
	 * should be used for the current request.
	 * </p>
	 *
	 * @return the current tenant identifier, or the default tenant if none is set
	 */
	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenant = TenantContext.getTenantId();
		return (tenant != null && !tenant.isBlank()) ? tenant : DEFAULT_TENANT;
	}

	/**
	 * Indicates whether Hibernate should validate existing sessions
	 * when the tenant identifier changes.
	 *
	 * @return {@code true} to allow validation of existing sessions
	 */
	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}