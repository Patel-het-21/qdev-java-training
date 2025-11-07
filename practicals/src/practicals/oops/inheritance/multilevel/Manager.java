package practicals.oops.inheritance.multilevel;

/**
 * Represents a manager, which is a subclass of Employee (and indirectly of
 * Person). Adds manager-specific field (department).
 * 
 * Demonstrates multi-level inheritance: Person → Employee → Manager
 * 
 * @author Het
 * @since 14/10/25
 */
public class Manager extends Employee {

	private String department;

	/**
	 * Sets the manager's department.
	 * 
	 * @param dept Department name
	 */
	public void setDepartment(String dept) {
		this.department = dept;
	}

	/**
	 * Displays manager details: name, employee ID, and department.
	 */
	public void displayManager() {
		displayEmployee(); // calls displayName() too
		System.out.println("Department: " + department);
	}

}