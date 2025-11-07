package practicals.oops.inheritance.hybrid;

/**
 * Employee interface demonstrating abstraction and part of hybrid inheritance.
 * It provides methods that all employees must implement, and a default method.
 * 
 * @author Het
 * @since 15/10/25
 */
public interface Employee {

	/**
	 * Displays the job title of the employee.
	 */
	void getJobTitle();

	/**
	 * Displays the salary of the employee.
	 */
	void getSalary();

	/**
	 * Displays default employee-related details.
	 */
	default void showEmployeeDetails() {
		System.out.println("Employee working on Project");
	}

}