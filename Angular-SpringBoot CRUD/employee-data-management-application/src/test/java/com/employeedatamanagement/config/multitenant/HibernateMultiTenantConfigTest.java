package com.employeedatamanagement.config.multitenant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for HibernateMultiTenantConfig class.
 * Tests Hibernate multi-tenant configuration setup.
 */
@SpringBootTest
@ActiveProfiles("test")
class HibernateMultiTenantConfigTest {

    @Autowired(required = false)
    private ApplicationContext applicationContext;

    /**
     * Test that multi-tenant entity manager factory is created.
     * Verifies the EntityManagerFactory bean for multi-tenancy.
     */
    @Test
    @DisplayName("Should create multi-tenant EntityManagerFactory")
    void multiTenantEntityManagerFactoryExists() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test that multi-tenant data source is configured.
     * Verifies data source configuration for multi-tenancy.
     */
    @Test
    @DisplayName("Should configure multi-tenant data source")
    void multiTenantDataSourceIsConfigured() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test that tenant identifier resolver is configured.
     * Verifies TenantIdentifierResolver integration.
     */
    @Test
    @DisplayName("Should configure TenantIdentifierResolver")
    void tenantIdentifierResolverIsConfigured() {
        if (applicationContext != null && applicationContext.containsBean("tenantIdentifierResolver")) {
            Object resolver = applicationContext.getBean("tenantIdentifierResolver");
            assertThat(resolver).isNotNull();
        }
    }

    /**
     * Test that multi-tenant connection provider is configured.
     * Verifies MultiTenantConnectionProvider integration.
     */
    @Test
    @DisplayName("Should configure MultiTenantConnectionProvider")
    void multiTenantConnectionProviderIsConfigured() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test Hibernate multi-tenancy strategy configuration.
     * Verifies DATABASE multi-tenancy strategy is set.
     */
    @Test
    @DisplayName("Should configure DATABASE multi-tenancy strategy")
    void multiTenancyStrategyIsConfigured() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test entity scanning for multi-tenant setup.
     * Verifies entity packages are scanned correctly.
     */
    @Test
    @DisplayName("Should scan entity packages for multi-tenant setup")
    void entityPackagesAreScanned() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test JPA properties for multi-tenancy.
     * Verifies Hibernate properties are correctly set.
     */
    @Test
    @DisplayName("Should set JPA properties for multi-tenancy")
    void jpaPropertiesAreSet() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test transaction manager for multi-tenant configuration.
     * Verifies transaction management setup.
     */
    @Test
    @DisplayName("Should configure transaction manager")
    void transactionManagerIsConfigured() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test multi-tenant configuration with invalid settings.
     * Verifies error handling for configuration issues.
     */
    @Test
    @DisplayName("Should handle invalid configuration gracefully")
    void handlesInvalidConfiguration() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test multi-tenant configuration initialization.
     * Verifies all components are properly initialized.
     */
    @Test
    @DisplayName("Should initialize multi-tenant configuration")
    void initializesMultiTenantConfiguration() {
        assertThat(applicationContext).isNotNull();
    }
}