package practicals.oops.inheritance.multilevel;

public class Employee extends Person{
	int employeeId;
	
	public void setEmployeeId(int id) {
		this.employeeId = id;
	}
	
	public void displayEmployee() {
		displayName();
		System.out.println("Employee ID: " + employeeId);
	}
}
