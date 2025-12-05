package com.qdev.employee.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.qdev.employee.dao.EmployeeDao;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

/**
 * {@code EmployeeValidationFilter} validates all user registration input fields
 * before the request reaches the {@code EmployeeServlet}. It ensures:
 *
 * If validation fails, the filter forwards the request back to
 * {@code index.jsp} with descriptive error messages and previously entered
 * values.
 *
 * @author Het
 * @since 06/11/2025
 */
@WebFilter("/registeremployee")
public class EmployeeValidationFilter implements Filter {

	/** Username allowed characters: letters, numbers, underscore. */
	private static final String USERNAME_REGEX = "^[A-Za-z0-9_]+$";
	/** Strong password pattern including upper, lower, digit, special char. */
	private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
	/** Contact number must be exactly 10 digits long. */
	private static final String CONTACT_REGEX = "^[0-9]{10}$";
	/** List of usernames that are not allowed for security reasons. */
	private static final String[] BLOCKED_USERNAMES = { "admin", "root", "system", "support", "manager" };

	/**
	 * Performs validation on employee registration input. If validation passes,
	 * request continues through the filter chain.
	 *
	 * @param request  incoming client request containing form data
	 * @param response response object to send to client
	 * @param chain    filter chain used to forward the request
	 *
	 * @throws IOException      if forwarding fails
	 * @throws ServletException if servlet cannot process forwarded request
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Retrieve form values
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");

		Map<String, String> errors = new HashMap<>();

		/*
		 * REQUIRED FIELD VALIDATION
		 */
		if (isEmpty(firstName)) {
			errors.put("firstNameError", "Please enter First Name");
		}
		if (isEmpty(lastName)) {
			errors.put("lastNameError", "Please enter Last Name");
		}
		if (isEmpty(userName)) {
			errors.put("userNameError", "Please enter Username");
		}
		if (isEmpty(password)) {
			errors.put("passwordError", "Please enter Password");
		}
		if (isEmpty(contactNo)) {
			errors.put("contactNoError", "Please enter Contact Number");
		}

		/**
		 * First Name must be between 2 and 50 characters.
		 */
		if (!isEmpty(firstName) && !isLengthBetween(firstName, 2, 50)) {
			errors.put("firstNameError", "First Name must be between 2 and 50 characters");
		}

		/**
		 * Last Name must be between 2 and 50 characters.
		 */
		if (!isEmpty(lastName) && !isLengthBetween(lastName, 2, 50)) {
			errors.put("lastNameError", "Last Name must be between 2 and 50 characters");
		}

		if (!isEmpty(userName)) {
			/** Username must be 4–30 characters long. */
			if (!isLengthBetween(userName, 4, 30)) {
				errors.put("userNameError", "Username must be between 4 and 30 characters");
			}
			/** Username must not contain spaces. */
			else if (userName.contains(" ")) {
				errors.put("userNameError", "Username cannot contain spaces");
			}
			/** Username must match allowed character set. */
			else if (!Pattern.matches(USERNAME_REGEX, userName)) {
				errors.put("userNameError", "Username can contain only letters, digits, and underscore (_)");
			}
			/** Username must begin with a letter. */
			else if (Character.isDigit(userName.charAt(0))) {
				errors.put("userNameError", "Username must start with a letter");
			}
			/** Username cannot be on the list of restricted names. */
			else {
				for (String blocked : BLOCKED_USERNAMES) {
					if (userName.equalsIgnoreCase(blocked)) {
						errors.put("userNameError", "This username is not allowed. Please choose another.");
						break;
					}
				}
			}
		}

		/**
		 * Password must follow strong password pattern.
		 */
		if (!isEmpty(password) && !Pattern.matches(PASSWORD_REGEX, password)) {
			errors.put("passwordError",
					"Password must include uppercase, lowercase, digit, special character and be at least 8 characters long.");
		}

		/**
		 * Contact number must contain exactly 10 digits.
		 */
		if (!isEmpty(contactNo) && !Pattern.matches(CONTACT_REGEX, contactNo)) {
			errors.put("contactNoError", "Contact Number must be exactly 10 digits");
		}

		/**
		 * Address can be optional but cannot exceed 200 characters.
		 */
		if (!isEmpty(address) && address.length() > 200) {
			errors.put("addressError", "Address cannot exceed 200 characters");
		}

		/*
		 * DATABASE UNIQUENESS CHECKS Checked ONLY if no other validation errors exist.
		 */
		if (errors.isEmpty()) {
			EmployeeDao dao = new EmployeeDao();

			/**
			 * Validates that the username is unique in database.
			 */
			if (dao.checkUserName(userName)) {
				errors.put("userNameError", "Username already exists. Enter another.");
			}

			/**
			 * Validates that the contact number is unique in database.
			 */
			if (dao.checkContactNumber(contactNo)) {
				errors.put("contactNoError", "Contact number already exists. Enter another.");
			}
		}

		/*
		 * IF ERRORS → RETURN TO JSP
		 */
		if (!errors.isEmpty()) {
			/* Attach errors */
			request.setAttribute("errors", errors);
			/* Preserve input values */
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("address", address);
			request.setAttribute("contactNo", contactNo);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// If no validation errors → continue filter chain
		chain.doFilter(request, response);
	}

	/**
	 * Checks whether a string is null or empty (after trimming).
	 *
	 * @param value the input string
	 * @return true if string is null or empty, false otherwise
	 */
	private boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Validates whether string length is within given range.
	 *
	 * @param value the input string
	 * @param min   minimum allowed length
	 * @param max   maximum allowed length
	 *
	 * @return true if the string length lies between min and max
	 */
	private boolean isLengthBetween(String value, int min, int max) {
		return value.length() >= min && value.length() <= max;
	}
}