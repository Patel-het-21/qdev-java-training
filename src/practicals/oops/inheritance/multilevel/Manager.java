package practicals.oops.inheritance.multilevel;

public class Manager extends Employee{
	String department;

    public void setDepartment(String dept) {
        this.department = dept;
    }

    public void displayManager() {
        displayEmployee(); // from Employee and Person
        System.out.println("Department: " + department);
    }
}
