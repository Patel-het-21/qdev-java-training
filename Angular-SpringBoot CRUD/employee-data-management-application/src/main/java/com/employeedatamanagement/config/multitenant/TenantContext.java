package com.employeedatamanagement.config.multitenant;

/**
 * Thread-local context holder for tenant identification.
 * Manages the current tenant identifier for multi-tenant operations.
 */
public class TenantContext {

    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

    /**
     * Sets the tenant identifier for the current thread.
     *
     * @param tenantId the tenant identifier to set
     */
    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * Gets the tenant identifier for the current thread.
     *
     * @return the current tenant identifier, or null if not set
     */
    public static String getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * Clears the tenant identifier for the current thread.
     */
    public static void clear() {
        TENANT_ID.remove();
    }
}