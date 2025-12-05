package com.qdev.employee.servlet;

import java.io.IOException;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * {@code UserServlet} is a simple demo servlet used to check whether a given
 * name exists in a predefined list of user names.
 *
 * @author Het
 * @since 18/11/2025
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	/**
	 * A predefined list of 10 user names used for existence checking.
	 */
	private final String[] names = { "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Ivan",
			"Jack" };

	/**
	 * Handles GET requests to check whether the provided name exists in the list.
	 *
	 * @param request  the {@link HttpServletRequest} containing the "name"
	 *                 parameter
	 * @param response the {@link HttpServletResponse} used to send back a JSON
	 *                 result
	 *
	 * @throws ServletException if servlet-level processing fails
	 * @throws IOException      if there is an input/output error writing the
	 *                          response
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String inputName = request.getParameter("name");
		boolean exists = false;

		// Search for name in predefined list
		if (inputName != null) {
			for (String name : names) {
				if (name.equalsIgnoreCase(inputName)) {
					exists = true;
					break;
				}
			}
		}

		// Create JSON response
		JSONObject json = new JSONObject();
		json.put("exists", exists);

		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
}
