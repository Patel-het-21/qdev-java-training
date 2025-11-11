package com.qdev.employee.servlet;

import java.io.IOException;

import com.qdev.employee.dao.EmployeeDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeDeleteServlet. This servlet handles the
 * deletion of an employee record from the database.
 * 
 * @author Het
 * @since 6/11/25
 */
@WebServlet("/deleteemployee")
public class EmployeeDeleteServlet extends HttpServlet {

	/**
	 * Data Access Object for performing database operations related to Employee.
	 */
	private EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * Handles the HTTP GET method for deleting an employee.
	 *
	 * @param request  the HttpServletRequest object containing client request data
	 * @param response the HttpServletResponse object for sending a response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during processing
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Retrieve the "id" parameter from the request
		 */
		String idParam = request.getParameter("id");

		if (idParam != null) {
			try {
				/**
				 * Parse the ID to an integer
				 */
				int id = Integer.parseInt(idParam);

				/**
				 * Delete the employee with the specified ID
				 */
				employeeDao.deleteEmployee(id);

			} catch (NumberFormatException e) {
				/**
				 * Handle invalid ID format
				 */
				e.printStackTrace();
			}
		}
		/**
		 * Forward the request to the employee listing page after deletion
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listemployee");
		requestDispatcher.forward(request, response);
	}

}