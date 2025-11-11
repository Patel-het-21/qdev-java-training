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
 * Servlet implementation class EmployeeEditServlet.
 * 
 * @author Het
 * @since 10/11/25
 */
@WebServlet("/editemployee")
public class EmployeeEditServlet extends HttpServlet {

	/**
	 * Data Access Object for performing database operations related to Employee.
	 */
	private EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * Handles the HTTP GET method for editing an employee.
	 *
	 * @param request  the HttpServletRequest object containing client request data
	 * @param response the HttpServletResponse object for sending a response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during processing
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * Retrieve the "id" parameter from the request
		 */
		String idParam = request.getParameter("id");

		/**
		 * Check if ID parameter is missing
		 */
		if (idParam == null || idParam.isEmpty()) {
			request.setAttribute("errorMessage", "Employee ID is missing!");
			RequestDispatcher rd = request.getRequestDispatcher("listEmployee.jsp");
			rd.forward(request, response);
			return;
		}

		try {
			/**
			 * Parse the ID to an integer
			 */
			int id = Integer.parseInt(idParam);

			/**
			 * Retrieve the employee with the specified ID
			 */
			Employee employee = employeeDao.getEmployeeId(id);

			if (employee != null) {
				/**
				 * Forward to the edit page with employee data
				 */
				request.setAttribute("employee", employee);
				RequestDispatcher rd = request.getRequestDispatcher("editEmployee.jsp");
				rd.forward(request, response);
			} else {
				// Employee not found
				request.setAttribute("errorMessage", "Employee not found!");
				RequestDispatcher rd = request.getRequestDispatcher("listEmployee.jsp");
				rd.forward(request, response);
			}

		} catch (NumberFormatException e) {
			/**
			 * Invalid ID format
			 */
			request.setAttribute("errorMessage", "Invalid Employee ID!");
			RequestDispatcher rd = request.getRequestDispatcher("listEmployee.jsp");
			rd.forward(request, response);
		}
	}

}