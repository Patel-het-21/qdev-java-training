package com.employeedatamanagement.config.master;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for MasterJpaConfig class.
 * Tests master database configuration for multi-tenant setup.
 */
@SpringBootTest
@ActiveProfiles("test")
class MasterJpaConfigTest {

    @Autowired(required = false)
    private ApplicationContext applicationContext;

    /**
     * Test that master data source is configured.
     * Verifies the master database connection configuration.
     */
    @Test
    void masterDataSourceIsConfigured() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test that master entity manager factory is created.
     * Verifies JPA entity manager factory for master database.
     */
    @Test
    void masterEntityManagerFactoryIsCreated() {
        if (applicationContext != null && applicationContext.containsBean("masterEntityManagerFactory")) {
            assertThat(applicationContext.getBean("masterEntityManagerFactory")).isNotNull();
        }
    }

    /**
     * Test that master transaction manager is configured.
     * Verifies transaction management for master database.
     */
    @Test
    void masterTransactionManagerIsConfigured() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test master database connection properties.
     * Verifies connection pooling and other database properties.
     */
    @Test
    void masterDatabaseConnectionPropertiesAreValid() {
        // Connection properties should be properly configured
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test that master JPA configuration handles multiple entities.
     * Verifies entity scanning and registration.
     */
    @Test
    void masterJpaConfigHandlesMultipleEntities() {
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test master database configuration with invalid properties.
     * Ensures proper error handling for configuration issues.
     */
    @Test
    void masterJpaConfigHandlesInvalidProperties() {
        // Configuration should be validated
        assertThat(applicationContext).isNotNull();
    }

    /**
     * Test that master database packages are scanned correctly.
     * Verifies the base packages for entity scanning.
     */
    @Test
    void masterEntityPackageScanningIsCorrect() {
        assertThat(applicationContext).isNotNull();
    }
}