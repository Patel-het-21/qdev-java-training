package com.qdev.employee.model;

/**
 * Represents an Employee entity with basic details such as name, username,
 * password, address, and contact information.
 * 
 * @author Het
 * @since 6/11/25
 */
public class Employee {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String address;
	private String contactNo;

	/**
	 * 
	 * @param firstName the first name of the employee
	 * @param lastName  the last name of the employee
	 * @param userName  the username of the employee
	 * @param password  the password of the employee
	 * @param address   the residential address of the employee
	 * @param contactNo the contact number of the employee
	 */
	public Employee(String firstName, String lastName, String userName, String password, String address,
			String contactNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.contactNo = contactNo;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the first name of the employee.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the employee.
	 * 
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the employee.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the employee.
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the username of the employee.
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the username of the employee.
	 * 
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Returns the password of the employee.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the employee.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the address of the employee.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the employee.
	 * 
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the contact number of the employee.
	 * 
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * Sets the contact number of the employee.
	 * 
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}