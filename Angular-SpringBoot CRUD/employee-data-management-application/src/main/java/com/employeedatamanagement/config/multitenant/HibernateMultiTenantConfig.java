package com.employeedatamanagement.config.multitenant;

import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Hibernate multi-tenancy.
 * Configures entity manager factory with multi-tenant support.
 */
@Configuration
public class HibernateMultiTenantConfig {

    // Configuration for multi-tenant entity manager factory
    // This would typically configure the LocalContainerEntityManagerFactoryBean
    // with the MultiTenantConnectionProvider and TenantIdentifierResolver
}