package practicals.oops.inheritance.hierarchical;

public class Student extends Person{
	int rollNumber;

    public void setRollNumber(int roll) {
        this.rollNumber = roll;
    }

    public void displayStudent() {
        displayName();
        System.out.println("Roll Number: " + rollNumber);
    }
}
