package com.qdev.employee.servlet;

import java.io.IOException;

import com.qdev.employee.dao.EmployeeDao;
import com.qdev.employee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeUpdateServlet.
 * 
 * @author Het
 * @since 11/11/25
 */
@WebServlet("/updateEmployee")
public class EmployeeUpdateServlet extends HttpServlet {

	/** DAO instance for performing employee database operations. */
	private EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * Handles the HTTP POST method to update an employee's information.
	 * 
	 * @param request  the HttpServletRequest object containing client request data
	 * @param response the HttpServletResponse object for sending a response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during processing
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Parse request parameters
		 */
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String address = request.getParameter("address").trim();
		String contactNo = request.getParameter("contactNo").trim();
		/**
		 * Create and populate Employee object
		 */
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUserName(userName);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContactNo(contactNo);

		/**
		 * Update employee in the database
		 */
		int result = employeeDao.updateEmployee(employee);
		/**
		 * Set request attributes based on result
		 */
		if (result > 0) {
			request.getSession().setAttribute("successMessage", "Employee updated successfully!");
		} else {
			request.getSession().setAttribute("errorMessage", "Failed to update employee!");
		}
		/**
		 * Forward back to the employee list page
		 */
		//RequestDispatcher rd = request.getRequestDispatcher("/listemployee");
		//rd.forward(request, response);
		response.sendRedirect("listemployee");
	}

}