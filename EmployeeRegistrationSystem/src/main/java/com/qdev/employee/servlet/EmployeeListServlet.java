/**
 * 
 */
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
 * @author Het
 * @since 10/11/25
 */
@WebServlet("/listemployee")
public class EmployeeListServlet extends HttpServlet {

	/** DAO instance for performing employee database operations. */
	private EmployeeDao employeeDao = new EmployeeDao();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> employeeList = employeeDao.getAllEmployees();
		request.setAttribute("employeeList", employeeList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("listEmployee.jsp");
		requestDispatcher.forward(request, response);
	}

}