package com.qdev.employee.servlet;

import java.io.IOException;

import com.qdev.employee.dao.EmployeeDao;
import com.qdev.employee.model.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * {@code EmployeeServlet} is responsible for handling employee registration
 * requests sent via the {@code /register} URL pattern.
 * 
 * @author Het
 * @since 6/11/25
 */
@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {

	/** DAO instance for performing employee database operations. */
	private EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * Handles HTTP {@code POST} requests for employee registration.
	 *
	 * @param request  the {@link HttpServletRequest} containing employee
	 *                 registration data
	 * @param response the {@link HttpServletResponse} used to return a response to
	 *                 the client
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an input or output error occurs during request
	 *                          processing
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * Retrieve validated form parameters
		 */
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");

		/**
		 * Create an Employee object using the form data
		 */
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUserName(userName);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContactNo(contactNo);

		/**
		 * Save employee details to the database
		 */
		int rowInserted = employeeDao.saveEmployee(employee);
		/**
		 * If the record was inserted successfully, show success message
		 */
		if (rowInserted == 1) {
			request.setAttribute("successMessage", "Employee registered successfully!");
		}
		/**
		 * Forward request back to index.jsp for user feedback
		 */
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

}