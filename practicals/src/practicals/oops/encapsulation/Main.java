package practicals.oops.encapsulation;

/**
 * Description : Demonstrates encapsulation with private fields and public
 * getters/setters.
 * 
 * @author Het Patel
 * @since 14/10/2025
 */
public class Main {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		/**
		 * sCreate Employee object using parameterized constructor
		 */
		Employee employee = new Employee(101, "Patel Deep", 25000);
		/**
		 * Display initial employee information
		 */
		employee.displayInfo();
		/**
		 * Update employee details using setters
		 */
		employee.setId(100);
		employee.setName("Patel Hiren");
		employee.setSalary(5000);
		/**
		 * Display updated employee information
		 */
		employee.displayInfo();
	}

}