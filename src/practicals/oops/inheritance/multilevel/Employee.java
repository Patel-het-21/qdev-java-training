package practicals.oops.inheritance.multilevel;

/**
 * Represents an employee, extending the Person class. Adds employee-specific
 * field (employeeId).
 * 
 * @author Het
 * @since 14/10/25
 */
public class Employee extends Person {

	private int employeeId;

	/**
	 * Sets the employee ID.
	 * 
	 * @param id ID to assign to the employee
	 */
	public void setEmployeeId(int id) {
		this.employeeId = id;
	}

	/**
	 * Displays the employee's name and ID.
	 */
	public void displayEmployee() {
		displayName();
		System.out.println("Employee ID: " + employeeId);
	}

}