package com.qdev.employee.servlet;

import java.io.IOException;

import com.qdev.employee.dao.EmployeeDao;
import com.qdev.employee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The {@code EmployeeSaveUpdateServlet} handles both employee registration and
 * employee update operations.
 * 
 * @author Het
 * @since 12/11/25
 */
@WebServlet({ "/registeremployee", "/updateEmployee" })
public class EmployeeSaveUpdateServlet extends HttpServlet {

	/** DAO instance used to interact with the employee database. */
	private final EmployeeDao employeeDao = new EmployeeDao();
	/**
	 * Handles HTTP {@code POST} requests for both employee creation and employee
	 * update.
	 *
	 * @param request  the {@link HttpServletRequest} containing employee form data
	 * @param response the {@link HttpServletResponse} used to redirect back to the employee list
	 *
	 * @throws ServletException if the servlet encounters an internal error
	 * @throws IOException      if an input/output error occurs during processing
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Determine whether the request is for updating an existing employee or
		 * registering a new one based on the servlet path.
		 */
		String path = request.getServletPath();
		boolean isUpdate = path.equals("/updateEmployee");
		/**
		 * Extract form parameters. Input validation is already handled in the filter
		 * layer.
		 */
		Integer id = isUpdate ? Integer.parseInt(request.getParameter("id")) : null;

		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String address = request.getParameter("address").trim();
		String contactNo = request.getParameter("contactNo").trim();
		/**
		 * Create and populate the Employee object based on the form data. If the
		 * operation is an update, the employee ID is also set.
		 */
		Employee employee = new Employee();
		if (isUpdate) {
			employee.setId(id);
		}
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUserName(userName);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContactNo(contactNo);
		/**
		 * Perform the appropriate database operation: - Insert new employee for
		 * registration - Update existing employee for update operation
		 */
		int rowsAffected = isUpdate ? employeeDao.updateEmployee(employee) : employeeDao.saveEmployee(employee);
		/**
		 * Set success or error messages in the session scope based on the result of the
		 * database operation.
		 */
		if (rowsAffected > 0) {
			String msg = isUpdate ? "Employee updated successfully!" : "Employee registered successfully!";
			request.getSession().setAttribute("successMessage", msg);
		} else {
			String msg = isUpdate ? "Failed to update employee!" : "Failed to register employee!";
			request.getSession().setAttribute("errorMessage", msg);
		}
		/**
		 * Redirect to employee list page after operation completes.
		 */
		response.sendRedirect("listemployee");
	}

}