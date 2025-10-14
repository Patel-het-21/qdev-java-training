package practicals.oops.inheritance.singlelevel;

/**
 * Author      : Het Patel  
 * Date        : 14/10/25  
 * Description : Single Level Inheritance
 */
public class Main {

	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setName("PATEL");
		employee.displayEmployee();
	}

}
