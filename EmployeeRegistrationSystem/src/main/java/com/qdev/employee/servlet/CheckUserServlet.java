package com.qdev.employee.servlet;

import java.io.IOException;

import com.qdev.employee.dao.EmployeeDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * {@code CheckUserServlet} is responsible for performing AJAX-based validation
 * checks for the {@code userName} and {@code contactNo} fields during employee
 * registration and update operations.
 *
 * @author Het
 * @since 18/11/2025
 */
@WebServlet("/CheckUserServlet")
public class CheckUserServlet extends HttpServlet {

	/**
	 * Handles GET requests for AJAX validation.
	 *
	 * @param request  the {@link HttpServletRequest} object containing parameters
	 *                 such as userName, contactNo, and optional id
	 * @param response the {@link HttpServletResponse} used to return JSON output
	 *
	 * @throws ServletException if the request cannot be handled
	 * @throws IOException      if an I/O error occurs during processing
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Retrieve parameters from AJAX request
		 */
		String userName = request.getParameter("userName");
		String contactNo = request.getParameter("contactNo");
		String idParam = request.getParameter("id");
		/*
		 * Parse current employee ID (used for update operation)
		 */
		int currentId = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : -1;

		boolean userNameExist = false;
		boolean contactNoExist = false;

		EmployeeDao employeeDao = new EmployeeDao();

		/*
		 * Username uniqueness validation
		 */
		if (userName != null) {
			if (currentId > 0) {
				userNameExist = employeeDao.checkUserName(userName, currentId);
			} else {
				userNameExist = employeeDao.checkUserName(userName);
			}
		}
		/*
		 * Contact number uniqueness validation
		 */
		if (contactNo != null) {
			if (currentId > 0) {
				contactNoExist = employeeDao.checkContactNumber(contactNo, currentId);
			} else {
				contactNoExist = employeeDao.checkContactNumber(contactNo);
			}
		}
		/*
		 * Build JSON response
		 */
		String json = "{\"userNameExists\":" + userNameExist + ",\"contactNoExists\":" + contactNoExist + "}";
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
