package practicals.oops.encapsulation;

/**
 * The Employee class demonstrates encapsulation by keeping fields private and
 * providing public getter and setter methods to access and modify them.
 * 
 * It also includes a displayInfo() method to print employee details.
 * 
 * Fields: - id: Employee ID - name: Employee name - salary: Employee salary
 * 
 * Provides a parameterized constructor and standard getter/setter methods.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Employee {

	// private fields
	private int id;
	private String name;
	private double salary;

	/**
	 * Constructor to initialize Employee object.
	 * 
	 * @param id     Employee ID
	 * @param name   Employee Name
	 * @param salary Employee Salary
	 */
	public Employee(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	/**
	 * Get the Employee ID
	 * 
	 * @return Employee ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * set the Employee ID
	 * 
	 * @param id New Employee ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the Employee Name
	 * 
	 * @return Employee name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the Employee Name
	 * 
	 * @param name New Employee Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the Employee Salary
	 * 
	 * @return Employee Salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * set the Employee Salary
	 * 
	 * @param salary New Employee Salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * Displays employee information (ID, name, and salary).
	 */
	public void displayInfo() {
		System.out.println("Employee ID: " + id);
		System.out.println("Employee Name: " + name);
		System.out.println("Employee Salary: ₹" + salary);
	}

}