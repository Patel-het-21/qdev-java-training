package com.employeedatamanagement.config.multitenant;

/**
 * {@code TenantContext} holds the current tenant identifier for the executing
 * thread using {@link ThreadLocal}.
 */
public class TenantContext {

	/**
	 * Thread-local storage for the current tenant identifier. Ensures tenant
	 * isolation across concurrent requests.
	 */
	private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

	/**
	 * Sets the tenant identifier for the current thread.
	 *
	 * @param tenantId the tenant ID to associate with the current thread
	 */
	public static void setTenantId(String tenantId) {
		currentTenant.set(tenantId);
	}

	/**
	 * Retrieves the tenant identifier associated with the current thread.
	 *
	 * @return the current tenant ID, or {@code null} if none is set
	 */
	public static String getTenantId() {
		return currentTenant.get();
	}

	/**
	 * Clears the tenant identifier from the current thread.
	 */
	public static void clear() {
		currentTenant.remove();
	}

}