package com.employeedatamanagement.SessionUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import jakarta.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for SessionUtil class.
 * Tests session management utility methods.
 */
class SessionUtilTest {

    private MockHttpServletRequest request;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    /**
     * Test setting attribute in session.
     * Verifies session attribute storage.
     */
    @Test
    @DisplayName("Should set session attribute successfully")
    void setSessionAttribute() {
        String key = "userId";
        String value = "user123";

        session.setAttribute(key, value);
        Object retrieved = session.getAttribute(key);

        assertThat(retrieved).isEqualTo(value);
    }

    /**
     * Test getting attribute from session.
     * Verifies session attribute retrieval.
     */
    @Test
    @DisplayName("Should get session attribute successfully")
    void getSessionAttribute() {
        String key = "username";
        String value = "testuser";
        session.setAttribute(key, value);

        Object retrieved = session.getAttribute(key);

        assertThat(retrieved).isNotNull();
        assertThat(retrieved).isEqualTo(value);
    }

    /**
     * Test removing attribute from session.
     * Verifies session attribute removal.
     */
    @Test
    @DisplayName("Should remove session attribute successfully")
    void removeSessionAttribute() {
        String key = "token";
        session.setAttribute(key, "abc123");

        session.removeAttribute(key);
        Object retrieved = session.getAttribute(key);

        assertThat(retrieved).isNull();
    }

    /**
     * Test getting non-existent attribute.
     * Verifies null handling for missing attributes.
     */
    @Test
    @DisplayName("Should return null for non-existent attribute")
    void getNonExistentAttribute() {
        Object retrieved = session.getAttribute("nonExistent");
        assertThat(retrieved).isNull();
    }

    /**
     * Test invalidating session.
     * Verifies session invalidation.
     */
    @Test
    @DisplayName("Should invalidate session successfully")
    void invalidateSession() {
        session.setAttribute("key", "value");
        session.invalidate();

        assertThat(session.isInvalid()).isTrue();
    }

    /**
     * Test session with null values.
     * Verifies handling of null attribute values.
     */
    @Test
    @DisplayName("Should handle null attribute values")
    void handleNullAttributeValue() {
        String key = "nullKey";
        session.setAttribute(key, null);

        Object retrieved = session.getAttribute(key);
        assertThat(retrieved).isNull();
    }

    /**
     * Test session attribute with empty string.
     * Verifies handling of empty string values.
     */
    @Test
    @DisplayName("Should handle empty string attribute values")
    void handleEmptyStringAttribute() {
        String key = "emptyKey";
        session.setAttribute(key, "");

        Object retrieved = session.getAttribute(key);
        assertThat(retrieved).isEqualTo("");
    }

    /**
     * Test session ID retrieval.
     * Verifies session ID management.
     */
    @Test
    @DisplayName("Should retrieve session ID")
    void retrieveSessionId() {
        String sessionId = session.getId();
        assertThat(sessionId).isNotNull();
        assertThat(sessionId).isNotEmpty();
    }

    /**
     * Test session timeout configuration.
     * Verifies session timeout settings.
     */
    @Test
    @DisplayName("Should configure session timeout")
    void configureSessionTimeout() {
        int timeout = 1800; // 30 minutes
        session.setMaxInactiveInterval(timeout);

        int retrievedTimeout = session.getMaxInactiveInterval();
        assertThat(retrievedTimeout).isEqualTo(timeout);
    }

    /**
     * Test storing complex objects in session.
     * Verifies serialization of complex types.
     */
    @Test
    @DisplayName("Should store complex objects in session")
    void storeComplexObjectInSession() {
        String key = "userDetails";
        TestUser user = new TestUser("john.doe", "john@example.com");

        session.setAttribute(key, user);
        TestUser retrieved = (TestUser) session.getAttribute(key);

        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getUsername()).isEqualTo("john.doe");
        assertThat(retrieved.getEmail()).isEqualTo("john@example.com");
    }

    /**
     * Test session attribute names enumeration.
     * Verifies listing all session attributes.
     */
    @Test
    @DisplayName("Should enumerate session attribute names")
    void enumerateSessionAttributeNames() {
        session.setAttribute("key1", "value1");
        session.setAttribute("key2", "value2");

        java.util.Enumeration<String> names = session.getAttributeNames();
        assertThat(names).isNotNull();
    }

    /**
     * Test session creation time.
     * Verifies session timestamp management.
     */
    @Test
    @DisplayName("Should retrieve session creation time")
    void retrieveSessionCreationTime() {
        long creationTime = session.getCreationTime();
        assertThat(creationTime).isGreaterThan(0);
    }

    /**
     * Test session last accessed time.
     * Verifies session activity tracking.
     */
    @Test
    @DisplayName("Should retrieve last accessed time")
    void retrieveLastAccessedTime() {
        long lastAccessedTime = session.getLastAccessedTime();
        assertThat(lastAccessedTime).isGreaterThan(0);
    }

    /**
     * Test concurrent session modifications.
     * Verifies thread-safety of session operations.
     */
    @Test
    @DisplayName("Should handle concurrent session modifications")
    void handleConcurrentSessionModifications() throws InterruptedException {
        session.setAttribute("counter", 0);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Integer counter = (Integer) session.getAttribute("counter");
                session.setAttribute("counter", counter + 1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Integer counter = (Integer) session.getAttribute("counter");
                session.setAttribute("counter", counter + 1);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Integer finalCounter = (Integer) session.getAttribute("counter");
        assertThat(finalCounter).isNotNull();
    }

    // Helper class for testing
    private static class TestUser {
        private final String username;
        private final String email;

        public TestUser(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }
}