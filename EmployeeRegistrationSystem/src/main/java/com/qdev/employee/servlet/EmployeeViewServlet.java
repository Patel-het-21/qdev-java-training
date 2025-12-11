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
 * {@code EmployeeViewServlet} is responsible for displaying the details of a
 * specific employee based on the employee ID provided via the request.
 *
 * @author Het
 * @since 18/11/2025
 */
@WebServlet("/viewemployee")
public class EmployeeViewServlet extends HttpServlet {

	/**
	 * Handles the GET request to retrieve and display an employee's details.
	 *
	 * @param request  the {@link HttpServletRequest} containing the employee ID
	 * @param response the {@link HttpServletResponse} used to forward data to JSP
	 *
	 * @throws ServletException if request forwarding fails
	 * @throws IOException      if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee = null;
		/*
		 * Fetch employee details from database
		 */
		employee = employeeDao.getEmployeeById(Integer.parseInt(request.getParameter("id")));
		/*
		 * Attach employee or error message to request scope
		 */
		if (employee != null) {
			request.setAttribute("employee", employee);
		} else {
			request.setAttribute("errorMessage", "Employee Not Found");
		}
		/*
		 * Forward to JSP page for rendering
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewEmployee.jsp");
		requestDispatcher.forward(request, response);
	}

}