package com.qdev.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an Employee entity with basic details such as name, username,
 * password, address, and contact information.
 * 
 * @author Het
 * @since 6/11/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String address;
	private String contactNo;

}