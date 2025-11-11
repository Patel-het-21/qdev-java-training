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

@WebServlet("/editemployee")
public class EmployeeEditServlet extends HttpServlet {

	private EmployeeDao employeeDao = new EmployeeDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");

		if (idParam == null || idParam.isEmpty()) {
			request.setAttribute("errorMessage", "Employee ID is missing!");
			RequestDispatcher rd = request.getRequestDispatcher("listEmployee.jsp");
			rd.forward(request, response);
			return;
		}

		try {
			int id = Integer.parseInt(idParam);
			Employee employee = employeeDao.getEmployeeId(id);

			if (employee != null) {
				request.setAttribute("employee", employee);
				RequestDispatcher rd = request.getRequestDispatcher("editEmployee.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Employee not found!");
				RequestDispatcher rd = request.getRequestDispatcher("listEmployee.jsp");
				rd.forward(request, response);
			}

		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid Employee ID!");
			RequestDispatcher rd = request.getRequestDispatcher("listEmployee.jsp");
			rd.forward(request, response);
		}
	}
}
