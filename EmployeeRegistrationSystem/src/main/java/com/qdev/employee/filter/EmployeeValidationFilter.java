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
import jakarta.servlet.http.HttpServletRequest;

/**
 * Filter responsible for validating employee details during registration and
 * update.
 */
@WebFilter({ "/registeremployee", "/updateEmployee" })
public class EmployeeValidationFilter implements Filter {

	/**
	 * Regex for validating allowed username characters (letters, numbers,
	 * underscore).
	 */
	private static final String USERNAME_REGEX = "^[A-Za-z0-9_]+$";
	/** Password Regex*/
	private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
	/** Regex requiring contact number to be exactly 10 digits. */
	private static final String CONTACT_REGEX = "^[0-9]{10}$";
	/** Usernames that are not allowed to be registered. */
	private static final String[] BLOCKED_USERNAMES = { "admin", "root", "system", "support", "manager" };

	/**
	 * Performs validation on incoming employee data before allowing request
	 * processing.
	 *
	 * @param request  the incoming servlet request
	 * @param response the outgoing servlet response
	 * @param chain    filter chain to forward the request if validations pass
	 *
	 * @throws IOException      when an I/O error occurs
	 * @throws ServletException when request forwarding fails
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		boolean isRegistration = path.equals("/registeremployee");
		boolean isUpdate = path.equals("/updateEmployee");
		/** Get data from JSP*/
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");

		Map<String, String> errors = new HashMap<>();
		/* Validate First Name */
		if (isEmpty(firstName)) {
			errors.put("firstNameError", "Please enter First Name");
		} else if (!isLengthBetween(firstName, 2, 50)) {
			errors.put("firstNameError", "First Name must be between 2 and 50 characters");
		}
		/* Validate Last Name */
		if (isEmpty(lastName)) {
			errors.put("lastNameError", "Please enter Last Name");
		} else if (!isLengthBetween(lastName, 2, 50)) {
			errors.put("lastNameError", "Last Name must be between 2 and 50 characters");
		}
		/* Validate Username */
		if (isEmpty(userName)) {
			errors.put("userNameError", "Please enter Username");
		} else {
			if (!isLengthBetween(userName, 4, 30)) {
				errors.put("userNameError", "Username must be between 4 and 30 characters");
			} else if (userName.contains(" ")) {
				errors.put("userNameError", "Username cannot contain spaces");
			} else if (!Pattern.matches(USERNAME_REGEX, userName)) {
				errors.put("userNameError", "Username can contain only letters, digits, and underscore (_)");
			} else if (Character.isDigit(userName.charAt(0))) {
				errors.put("userNameError", "Username must start with a letter");
			} else {
				// Check blocked usernames
				for (String blocked : BLOCKED_USERNAMES) {
					if (userName.equalsIgnoreCase(blocked)) {
						errors.put("userNameError", "This username is not allowed.");
						break;
					}
				}
			}
		}
		/* Validate Password */
		if (isEmpty(password)) {
			errors.put("passwordError", "Please enter Password");
		} else if (!Pattern.matches(PASSWORD_REGEX, password)) {
			errors.put("passwordError", "Password must include uppercase, lowercase, digit, special character and be at least 8 characters long.");
		}
		/* Validate Contact Number */
		if (isEmpty(contactNo)) {
			errors.put("contactNoError", "Please enter Contact Number");
		} else if (!Pattern.matches(CONTACT_REGEX, contactNo)) {
			errors.put("contactNoError", "Contact Number must be exactly 10 digits");
		}
		/* Address length validation */
		if (!isEmpty(address) && address.length() > 200) {
			errors.put("addressError", "Address cannot exceed 200 characters");
		}
		/**
		 * Check uniqueness of username and contact number. For registration, checks
		 * against all records. For update, excludes the current employee record.
		 */
		if (errors.isEmpty()) {
			EmployeeDao dao = new EmployeeDao();
			if (isRegistration) {
				if (dao.checkUserName(userName)) {
					errors.put("userNameError", "Username already exists.");
				}
				if (dao.checkContactNumber(contactNo)) {
					errors.put("contactNoError", "Contact number already exists.");
				}
			} else if (isUpdate) {
				int empId = Integer.parseInt(id);
				if (dao.checkUserName(userName, empId)) {
					errors.put("userNameError", "Username already exists.");
				}
				if (dao.checkContactNumber(contactNo, empId)) {
					errors.put("contactNoError", "Contact number already exists.");
				}
			}
		}
		if (!errors.isEmpty()) {
			// Attach errors and previous values back to the request
			request.setAttribute("errors", errors);
			request.setAttribute("id", id);
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("address", address);
			request.setAttribute("contactNo", contactNo);
			if (isUpdate) {
				request.setAttribute("isEdit", true);
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}
	
	/**
	 * Checks whether the provided string is null or empty after trimming.
	 *
	 * @param val the string to check
	 * @return true if value is null or empty, false otherwise
	 */
	private boolean isEmpty(String val) {
		return val == null || val.trim().isEmpty();
	}
	
	/**
	 * Validates that a string's length is within the provided range.
	 *
	 * @param val the string to validate
	 * @param min minimum length allowed
	 * @param max maximum length allowed
	 * @return true if within range, false otherwise
	 */
	private boolean isLengthBetween(String val, int min, int max) {
		return val.length() >= min && val.length() <= max;
	}

}