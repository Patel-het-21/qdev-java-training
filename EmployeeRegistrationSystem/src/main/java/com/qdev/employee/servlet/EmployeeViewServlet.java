/**
 * 
 */
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
 * 
 */
@WebServlet("/viewemployee")
public class EmployeeViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee = null;
		
		int id = Integer.parseInt (request.getParameter("id"));
		employee = employeeDao.getEmployeeId(id);
		
		if(employee!=null) {
			request.setAttribute("employee", employee);
		}else {
			request.setAttribute("errorMessage", "Employee Not Found");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewEmployee.jsp");
		requestDispatcher.forward(request, response);
	}

}