package com.employeedatamanagement.config.multitenant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for TenantContext class.
 * Tests thread-local tenant identifier management.
 */
class TenantContextTest {

    @BeforeEach
    void setUp() {
        // Clear tenant context before each test
        TenantContext.clear();
    }

    @AfterEach
    void tearDown() {
        // Clean up tenant context after each test
        TenantContext.clear();
    }

    /**
     * Test setting and getting tenant ID.
     * Verifies basic tenant context operations.
     */
    @Test
    @DisplayName("Should set and retrieve tenant ID correctly")
    void setAndGetTenantId() {
        String tenantId = "tenant123";
        TenantContext.setTenantId(tenantId);

        String retrievedTenantId = TenantContext.getTenantId();
        assertThat(retrievedTenantId).isEqualTo(tenantId);
    }

    /**
     * Test clearing tenant context.
     * Verifies that tenant ID is removed after clear.
     */
    @Test
    @DisplayName("Should clear tenant context successfully")
    void clearTenantContext() {
        TenantContext.setTenantId("tenant123");
        TenantContext.clear();

        String tenantId = TenantContext.getTenantId();
        assertThat(tenantId).isNull();
    }

    /**
     * Test getting tenant ID when not set.
     * Verifies default behavior when no tenant is set.
     */
    @Test
    @DisplayName("Should return null when tenant ID is not set")
    void getTenantIdWhenNotSet() {
        String tenantId = TenantContext.getTenantId();
        assertThat(tenantId).isNull();
    }

    /**
     * Test setting null tenant ID.
     * Verifies handling of null values.
     */
    @Test
    @DisplayName("Should handle null tenant ID")
    void setNullTenantId() {
        TenantContext.setTenantId(null);
        String tenantId = TenantContext.getTenantId();
        assertThat(tenantId).isNull();
    }

    /**
     * Test setting empty tenant ID.
     * Verifies handling of empty string values.
     */
    @Test
    @DisplayName("Should handle empty tenant ID")
    void setEmptyTenantId() {
        TenantContext.setTenantId("");
        String tenantId = TenantContext.getTenantId();
        assertThat(tenantId).isEmpty();
    }

    /**
     * Test tenant context isolation between threads.
     * Verifies that tenant context is thread-local.
     */
    @Test
    @DisplayName("Should isolate tenant context between threads")
    void tenantContextIsThreadLocal() throws InterruptedException {
        String mainThreadTenantId = "main-tenant";
        TenantContext.setTenantId(mainThreadTenantId);

        Thread thread = new Thread(() -> {
            String threadTenantId = TenantContext.getTenantId();
            assertThat(threadTenantId).isNull();

            TenantContext.setTenantId("thread-tenant");
            assertThat(TenantContext.getTenantId()).isEqualTo("thread-tenant");
        });

        thread.start();
        thread.join();

        // Main thread tenant should remain unchanged
        assertThat(TenantContext.getTenantId()).isEqualTo(mainThreadTenantId);
    }

    /**
     * Test updating tenant ID multiple times.
     * Verifies that tenant ID can be changed.
     */
    @Test
    @DisplayName("Should update tenant ID correctly")
    void updateTenantId() {
        TenantContext.setTenantId("tenant1");
        assertThat(TenantContext.getTenantId()).isEqualTo("tenant1");

        TenantContext.setTenantId("tenant2");
        assertThat(TenantContext.getTenantId()).isEqualTo("tenant2");
    }

    /**
     * Test tenant context with special characters.
     * Verifies handling of various tenant ID formats.
     */
    @Test
    @DisplayName("Should handle tenant IDs with special characters")
    void tenantIdWithSpecialCharacters() {
        String specialTenantId = "tenant-123_ABC@test.com";
        TenantContext.setTenantId(specialTenantId);
        assertThat(TenantContext.getTenantId()).isEqualTo(specialTenantId);
    }

    /**
     * Test tenant context with very long tenant ID.
     * Verifies handling of boundary conditions.
     */
    @Test
    @DisplayName("Should handle long tenant IDs")
    void longTenantId() {
        String longTenantId = "a".repeat(1000);
        TenantContext.setTenantId(longTenantId);
        assertThat(TenantContext.getTenantId()).isEqualTo(longTenantId);
    }
}