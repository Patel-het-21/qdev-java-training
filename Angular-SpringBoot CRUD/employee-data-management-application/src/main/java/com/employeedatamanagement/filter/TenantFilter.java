package com.employeedatamanagement.filter;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.employeedatamanagement.config.multitenant.TenantContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet filter responsible for resolving and setting the tenant context
 * for each incoming HTTP request.
 * <p>
 * This filter extracts the tenant identifier from the request (typically
 * from a request header) and sets it into {@link TenantContext} so that
 * Hibernate multi-tenancy can route database connections to the correct
 * tenant schema/database.
 * </p>
 * <p>
 * The tenant context is cleared after the request is processed to avoid
 * tenant data leaking across requests.
 * </p>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantFilter extends HttpFilter {

	/**
	 * Intercepts every incoming HTTP request and resolves the tenant identifier.
	 * <p>
	 * The tenant identifier is expected to be present in the request header
	 * {@code X-Tenant-ID}. If present, it is stored in {@link TenantContext}
	 * for the duration of the request.
	 * </p>
	 *
	 * @param request  the incoming HTTP request
	 * @param response the outgoing HTTP response
	 * @param chain    the filter chain used to pass control to the next filter
	 * @throws IOException      if an I/O error occurs during filtering
	 * @throws ServletException if the request could not be handled
	 */
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * Read tenant from request header
		 */
		try {
			String tenantFromHeader = request.getHeader("X-Tenant-ID");
			System.out.println(">>> Incoming Tenant Header: " + tenantFromHeader);
			if (tenantFromHeader != null && !tenantFromHeader.isBlank()) {
				TenantContext.setTenantId(tenantFromHeader);
			}
			chain.doFilter(request, response);
		} finally {
			/*
			 * Clear tenant after request is processed
			 */
			TenantContext.clear();
		}
	}

}