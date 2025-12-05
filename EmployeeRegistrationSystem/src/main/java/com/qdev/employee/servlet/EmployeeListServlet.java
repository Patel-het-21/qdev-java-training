package com.qdev.employee.servlet;

import java.io.IOException;
import java.util.List;

import com.qdev.employee.dao.EmployeeDao;
import com.qdev.employee.model.Employee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeListServlet.
 * 
 * @author Het
 * @since 10/11/25
 */
@WebServlet("/listemployee")
public class EmployeeListServlet extends HttpServlet {

	/** DAO instance for performing employee database operations. */
	private EmployeeDao employeeDao = new EmployeeDao();

	/**
	 * Handles the HTTP GET method to fetch all employees and forward to the JSP
	 * page.
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
		 * Retrieve all employees from the database
		 */
		List<Employee> employeeList = employeeDao.getAllEmployees();
		/**
		 * Set the employee list as a request attribute
		 */
		request.setAttribute("employeeList", employeeList);
		/**
		 * Forward the request to the JSP page for rendering
		 */
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("listEmployee.jsp");
		requestDispatcher.forward(request, response);
	}

}