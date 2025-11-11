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
 * 
 */
@WebServlet("/deleteemployee")
public class EmployeeDeleteServlet extends HttpServlet {

	EmployeeDao employeeDao = new EmployeeDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		if (idParam != null) {
			try {
				int id = Integer.parseInt(idParam);
				employeeDao.deleteEmployee(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		// Redirect to the employee list page after deletion
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listemployee");
		requestDispatcher.forward(request, response);
	}

}