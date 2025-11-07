package practicals.oops.inheritance.singlelevel;

/**
 * Demonstrates Single Level Inheritance in Java. Person → Employee
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Main {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		/**
		 * Create an Employee object
		 */
		Employee employee = new Employee();

		/**
		 * Set employee details and display that
		 */
		employee.setEmployeeId(1);
		employee.setName("PATEL");
		employee.displayEmployee();
	}

}