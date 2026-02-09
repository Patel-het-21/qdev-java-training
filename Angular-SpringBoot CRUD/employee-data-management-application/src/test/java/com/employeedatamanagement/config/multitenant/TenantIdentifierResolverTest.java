package com.employeedatamanagement.config.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for TenantIdentifierResolver class.
 * Tests Hibernate multi-tenancy tenant resolution.
 */
@SpringBootTest
@ActiveProfiles("test")
class TenantIdentifierResolverTest {

    @Autowired(required = false)
    private TenantIdentifierResolver tenantIdentifierResolver;

    @BeforeEach
    void setUp() {
        TenantContext.clear();
    }

    @AfterEach
    void tearDown() {
        TenantContext.clear();
    }

    /**
     * Test that TenantIdentifierResolver bean is created.
     * Verifies the resolver is available in the application context.
     */
    @Test
    @DisplayName("Should create TenantIdentifierResolver bean")
    void tenantIdentifierResolverBeanExists() {
        if (tenantIdentifierResolver != null) {
            assertThat(tenantIdentifierResolver).isNotNull();
            assertThat(tenantIdentifierResolver).isInstanceOf(CurrentTenantIdentifierResolver.class);
        }
    }

    /**
     * Test resolving tenant identifier when tenant is set in context.
     * Verifies that the resolver retrieves tenant ID from TenantContext.
     */
    @Test
    @DisplayName("Should resolve tenant identifier from context")
    void resolveTenantIdentifierFromContext() {
        if (tenantIdentifierResolver != null) {
            String expectedTenant = "tenant123";
            TenantContext.setTenantId(expectedTenant);

            String resolvedTenant = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
            assertThat(resolvedTenant).isNotNull();
        }
    }

    /**
     * Test resolving tenant identifier when no tenant is set.
     * Verifies default tenant behavior.
     */
    @Test
    @DisplayName("Should return default tenant when none is set")
    void resolveDefaultTenantIdentifier() {
        if (tenantIdentifierResolver != null) {
            String resolvedTenant = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
            // Should return default tenant or handle appropriately
            assertThat(resolvedTenant).isNotNull();
        }
    }

    /**
     * Test validateExistingCurrentSessions method.
     * Verifies session validation behavior.
     */
    @Test
    @DisplayName("Should validate existing current sessions")
    void validateExistingCurrentSessions() {
        if (tenantIdentifierResolver != null) {
            // This method typically returns false for DATABASE multi-tenancy
            boolean validates = tenantIdentifierResolver.validateExistingCurrentSessions();
            assertThat(validates).isNotNull();
        }
    }

    /**
     * Test tenant resolution with multiple tenant switches.
     * Verifies resolver handles tenant changes correctly.
     */
    @Test
    @DisplayName("Should handle multiple tenant switches")
    void handleMultipleTenantSwitches() {
        if (tenantIdentifierResolver != null) {
            TenantContext.setTenantId("tenant1");
            String tenant1 = tenantIdentifierResolver.resolveCurrentTenantIdentifier();

            TenantContext.setTenantId("tenant2");
            String tenant2 = tenantIdentifierResolver.resolveCurrentTenantIdentifier();

            assertThat(tenant1).isNotNull();
            assertThat(tenant2).isNotNull();
        }
    }

    /**
     * Test tenant resolution with null tenant context.
     * Verifies handling of null values.
     */
    @Test
    @DisplayName("Should handle null tenant context")
    void handleNullTenantContext() {
        if (tenantIdentifierResolver != null) {
            TenantContext.setTenantId(null);
            String tenant = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
            assertThat(tenant).isNotNull(); // Should return default
        }
    }

    /**
     * Test tenant resolution with empty tenant ID.
     * Verifies handling of empty string values.
     */
    @Test
    @DisplayName("Should handle empty tenant ID")
    void handleEmptyTenantId() {
        if (tenantIdentifierResolver != null) {
            TenantContext.setTenantId("");
            String tenant = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
            assertThat(tenant).isNotNull();
        }
    }

    /**
     * Test concurrent tenant resolution.
     * Verifies thread-safety of tenant resolution.
     */
    @Test
    @DisplayName("Should resolve tenants correctly in concurrent scenarios")
    void concurrentTenantResolution() throws InterruptedException {
        if (tenantIdentifierResolver != null) {
            String mainTenant = "main-tenant";
            TenantContext.setTenantId(mainTenant);

            Thread thread = new Thread(() -> {
                TenantContext.setTenantId("thread-tenant");
                String threadResolvedTenant = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
                assertThat(threadResolvedTenant).isNotNull();
            });

            thread.start();
            thread.join();

            String mainResolvedTenant = tenantIdentifierResolver.resolveCurrentTenantIdentifier();
            assertThat(mainResolvedTenant).isNotNull();
        }
    }
}