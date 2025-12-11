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
 * {@code EmployeeEditServlet} is responsible for retrieving an employee's
 * details and forwarding them to the form page for editing.
 *
 * @author Het
 * @since 10/11/25
 */
@WebServlet("/editemployee")
public class EmployeeEditServlet extends HttpServlet {

	private EmployeeDao employeeDao = new EmployeeDao();
	/**
	 * Handles the GET request to load the employee data and forward it to the edit
	 * form.
	 *
	 * @param request  the {@link HttpServletRequest} object containing the employee
	 *                 ID
	 * @param response the {@link HttpServletResponse} used for redirection or
	 *                 forwarding
	 *
	 * @throws ServletException if request forwarding fails
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		/*
		 *  Validate ID parameter
		 */
		if (idParam == null || idParam.isEmpty()) {
			request.getSession().setAttribute("errorMessage", "Employee ID is missing!");
			response.sendRedirect("listemployee");
			return;
		}
		try {
			/*
			 *  Fetch employee data
			 */
			Employee employee = employeeDao.getEmployeeById(Integer.parseInt(idParam));
			if (employee != null) {
				request.setAttribute("employee", employee);
				request.setAttribute("isEdit", true); // Flag for JSP to switch to edit mode

				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else {
				request.getSession().setAttribute("errorMessage", "Employee not found!");
				response.sendRedirect("listemployee");
			}
		} catch (NumberFormatException e) {
			request.getSession().setAttribute("errorMessage", "Invalid Employee ID!");
			response.sendRedirect("listemployee");
		}
	}

}