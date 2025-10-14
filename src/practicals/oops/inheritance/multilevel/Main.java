package practicals.oops.inheritance.multilevel;

/**
 * Author      : Het Patel  
 * Date        : 14/10/25  
 * Description : Multi Level Inheritance
 */
public class Main {

	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		
		Manager mgr = new Manager();
        mgr.setName("Bob");
        mgr.setEmployeeId(102);
        mgr.setDepartment("Developer");
        mgr.displayManager();
	}

}
