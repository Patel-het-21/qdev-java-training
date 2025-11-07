package practicals.oops.inheritance.hierarchical;

/**
 * Represents a student, which is a subclass of Person. Adds a roll number field
 * specific to students. Demonstrates hierarchical inheritance.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Student extends Person {

	private int rollNumber;

	/**
	 * Set the roll number of student
	 * 
	 * @param roll
	 */
	public void setRollNumber(int roll) {
		this.rollNumber = roll;
	}

	/**
	 * Displays the student's name and roll number.
	 */
	public void displayStudent() {
		displayName();
		System.out.println("Roll Number: " + rollNumber);
	}

}