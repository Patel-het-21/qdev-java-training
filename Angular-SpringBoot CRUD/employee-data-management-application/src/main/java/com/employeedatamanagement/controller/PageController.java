package com.employeedatamanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

/**
 * @author Het Patel
 * @since 11/12/25
 * 
 * Controller responsible for handling page navigation requests.
 */
@Controller
public class PageController {

	@Autowired
	private HttpSession session;
	
	/**
	 * Displays the home page.
	 *
	 * @return the employee form JSP view name
	 */
	@GetMapping("/")
	public String home() {
		//return "employee-form"; // Loads employee-form.jsp
		return "signin"; // loads signin.jsp
	}

	/**
	 * Sign In page
	 */
	@GetMapping("/signin")
	public String signin() {
		return "signin"; // signin.jsp
	}

	/**
	 * Sign Up page
	 */
	@GetMapping("/signup")
	public String signup() {
		return "signup"; // signup.jsp
	}
	
	/**
	 * Displays the employee form page.
	 *
	 * @return the employee form JSP view name
	 */
	@GetMapping("/employee-form")
	public String form() {
		if (session.getAttribute("LOGGED_IN_USER") == null) {
			return "redirect:/signin";
		}
		return "employee-form"; // Loads employee-form.jsp
	}

	/**
	 * Displays the employee list page.
	 *
	 * @return the employee list JSP view name
	 */
	@GetMapping("/employee-list")
	public String list() {
		if (session.getAttribute("LOGGED_IN_USER") == null) {
			return "redirect:/signin";
		}
		return "employee-list"; // Loads employee-list.jsp
	}

}