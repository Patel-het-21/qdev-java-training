package com.qdev.employee.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * {@code EmployeeUpdateValidationFilter} is a servlet filter that performs
 * input validation on employee update requests before the request reaches the
 * {@code EmployeeUpdateServlet}.
 * 
 * @author Het
 * @since 18/11/25
 */
@WebFilter("/updateEmployee")
public class EmployeeUpdateValidationFilter implements Filter {

	/**
	 * Intercepts incoming update requests and validates input fields.
	 *
	 * @param request  the {@link ServletRequest} object containing client-submitted
	 *                 data
	 * @param response the {@link ServletResponse} object for sending data back to
	 *                 the client
	 * @param chain    the {@link FilterChain} used to invoke the next filter or
	 *                 servlet in the processing chain
	 * @throws IOException      if an input or output error occurs during the
	 *                          filtering process
	 * @throws ServletException if the request cannot be handled
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Retrieve form parameters from the request
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");
		// Map to store validation errors
		Map<String, String> errors = new HashMap<>();

		// Field-level validation
		if (firstName == null || firstName.trim().isEmpty()) {
			errors.put("firstNameError", "Please enter first name");
		}
		if (lastName == null || lastName.trim().isEmpty()) {
			errors.put("lastNameError", "Please enter last name");
		}
		if (userName == null || userName.trim().isEmpty()) {
			errors.put("userNameError", "Please enter user name");
		}
		if (password == null || password.trim().isEmpty()) {
			errors.put("passwordError", "Please enter password");
		} else if (!isValidPassword(password)) {
			errors.put("passwordError",
					"Password must be at least 8 characters long, include uppercase, lowercase, digit, and special character.");
		}
		if (contactNo == null || contactNo.trim().isEmpty()) {
			errors.put("contactNoError", "Please enter contact number");
		} else if (!isValidContact(contactNo)) {
			errors.put("contactNoError", "Contact number must be exactly 10 digits.");
		}

		// If there are validation errors, forward back to editEmployee.jsp
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			// Preserve previously entered values
			request.setAttribute("id", id);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("address", address);
			request.setAttribute("contactNo", contactNo);

			request.setAttribute("isEdit", true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return; // Stop further processing
		}

		// Validation passed, continue to the servlet
		chain.doFilter(request, response);
	}

	/**
	 * Validates a password based on strong password rules. Must contain at least
	 * one uppercase, one lowercase, one digit, one special character, and be at
	 * least 8 characters long.
	 *
	 * @param password the password to validate
	 * @return true if the password is valid, false otherwise
	 */
	private boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
		return Pattern.matches(passwordRegex, password);
	}

	/**
	 * Validates that a contact number contains exactly 10 digits.
	 *
	 * @param contact the contact number to validate
	 * @return true if the contact number is valid, false otherwise
	 */
	private boolean isValidContact(String contact) {
		return Pattern.matches("^[0-9]{10}$", contact);
	}
}
