package com.employeedatamanagement.SessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for session management operations.
 * Provides helper methods for working with HTTP sessions.
 */
public class SessionUtil {

    /**
     * Gets an attribute from the session.
     *
     * @param request the HTTP request
     * @param key     the attribute key
     * @return the attribute value, or null if not found
     */
    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        return (session != null) ? session.getAttribute(key) : null;
    }

    /**
     * Sets an attribute in the session.
     *
     * @param request the HTTP request
     * @param key     the attribute key
     * @param value   the attribute value
     */
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
    }

    /**
     * Removes an attribute from the session.
     *
     * @param request the HTTP request
     * @param key     the attribute key
     */
    public static void removeAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    /**
     * Invalidates the current session.
     *
     * @param request the HTTP request
     */
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}