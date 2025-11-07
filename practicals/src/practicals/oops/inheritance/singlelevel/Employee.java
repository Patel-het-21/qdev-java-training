package practicals.oops.inheritance.singlelevel;

/**
 * Represents an Employee who inherits from Person. Demonstrates single-level
 * inheritance by adding employee-specific behavior.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Employee extends Person {

	private int employeeId;

	/**
	 * Sets the employee ID.
	 * 
	 * @param id The employee ID to be set
	 */
	public void setEmployeeId(int id) {
		this.employeeId = id;
	}

	/**
	 * Displays the employee's name and ID.
	 */
	public void displayEmployee() {
		displayName(); // from Person class
		System.out.println("Employee ID: " + employeeId);
	}

}