package com.employeedatamanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Controller responsible for handling page navigation requests.
 */
@Controller
public class PageController {

	/**
	 * Displays the home page.
	 *
	 * @return the employee form JSP view name
	 */
	@GetMapping("/")
	public String home() {
		return "employee-form"; // Loads employee-form.jsp
	}

	/**
	 * Displays the employee form page.
	 *
	 * @return the employee form JSP view name
	 */
	@GetMapping("/employee-form")
	public String form() {
		return "employee-form"; // Loads employee-form.jsp
	}

	/**
	 * Displays the employee list page.
	 *
	 * @return the employee list JSP view name
	 */
	@GetMapping("/employee-list")
	public String list() {
		return "employee-list"; // Loads employee-list.jsp
	}

}