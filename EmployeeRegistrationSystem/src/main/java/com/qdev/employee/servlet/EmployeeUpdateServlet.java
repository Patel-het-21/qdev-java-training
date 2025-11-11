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

@WebServlet("/updateEmployee")
public class EmployeeUpdateServlet extends HttpServlet {

	private EmployeeDao employeeDao = new EmployeeDao();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contactNo = request.getParameter("contactNo");

		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUserName(userName);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContactNo(contactNo);

		int result = employeeDao.updateEmployee(employee);

		if (result > 0) {
			request.setAttribute("successMessage", "Employee updated successfully!");
		} else {
			request.setAttribute("errorMessage", "Failed to update employee!");
		}

		System.out.println("Data successfully inserted or updated");
		// Redirect back to employee list
		RequestDispatcher rd = request.getRequestDispatcher("/listemployee");
		rd.forward(request, response);
	}
}