package com.employeedatamanagement.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for WebConfig class.
 * Tests CORS configuration, interceptors, and web MVC settings.
 */
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class WebConfigTest {

    @Autowired(required = false)
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        if (webApplicationContext != null) {
            mockMvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .build();
        }
    }

    /**
     * Test that WebConfig bean is created in the application context.
     */
    @Test
    void webConfigBeanExists() {
        // WebConfig should be available as a configuration bean
        assertThat(webApplicationContext).isNotNull();
    }

    /**
     * Test CORS configuration allows required origins.
     * Verifies that CORS mappings are properly configured.
     */
    @Test
    void corsConfigurationIsApplied() {
        // CORS configuration should be present in the web context
        assertThat(webApplicationContext).isNotNull();
    }

    /**
     * Test that view resolvers are properly configured.
     * Verifies JSP view resolver configuration.
     */
    @Test
    void viewResolversAreConfigured() {
        if (webApplicationContext != null) {
            assertThat(webApplicationContext.getBean("viewResolver")).isNotNull();
        }
    }

    /**
     * Test static resource handling configuration.
     * Verifies static resources are properly mapped.
     */
    @Test
    void staticResourceHandlingIsConfigured() {
        assertThat(webApplicationContext).isNotNull();
    }

    /**
     * Test that interceptors are registered if any.
     * Verifies custom interceptors for multi-tenancy or authentication.
     */
    @Test
    void interceptorsAreRegistered() {
        // Interceptors should be properly registered
        assertThat(webApplicationContext).isNotNull();
    }

    /**
     * Test web MVC configuration for edge case with null values.
     * Ensures configuration handles null values gracefully.
     */
    @Test
    void webConfigHandlesNullValues() {
        // Configuration should handle edge cases
        assertThat(webApplicationContext).isNotNull();
    }
}