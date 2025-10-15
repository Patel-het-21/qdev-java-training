package practicals.oops.inheritance.hybrid;

public interface Employee {
	void getJobTitle();
	void getSalary();
	
	default void showEmployeeDetails() {
		System.out.println("Employee working on Project");
	}
}
