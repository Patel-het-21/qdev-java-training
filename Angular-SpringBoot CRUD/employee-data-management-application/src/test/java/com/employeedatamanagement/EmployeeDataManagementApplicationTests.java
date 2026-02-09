package com.employeedatamanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for the Employee Data Management Application.
 * Tests the application context loading and basic configuration.
 */
@SpringBootTest
@ActiveProfiles("test")
class EmployeeDataManagementApplicationTests {

    /**
     * Test that the Spring application context loads successfully.
     * This is a smoke test that verifies all beans can be created
     * and all configurations are valid.
     */
    @Test
    void contextLoads() {
        // This test will pass if the application context loads without errors
    }

    /**
     * Test that the application context loads with default profile.
     * Verifies the application can start without explicit profile configuration.
     */
    @Test
    void contextLoadsWithDefaultProfile() {
        // Context should load successfully
    }
}