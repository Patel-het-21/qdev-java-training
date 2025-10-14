package practicals.oops.encapsulation;

/*
 * Author : Het Patel
 * Date : 14/10/2025
 * Description : Demonstrates encapsulation with private fields and public getters/setters.
 */
public class Main {
	
	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		Employee employee = new Employee(101, "Patel Deep", 25000);
		
		employee.displayInfo();
		
		employee.setId(100);
		employee.setName("Patel Hiren");
		employee.setSalary(5000);
		employee.displayInfo();
	}
}
