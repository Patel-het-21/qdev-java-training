package practicals.oops.inheritance.hybrid;

/**
 * Represents a student, which is a subclass of Person.
 * 
 * @author Hetb Patel
 * @since 15/10/25
 * @see Person
 * @see Intern
 */
public class Student extends Person {

	protected String course;
	protected String university;

	/**
	 * Constructor to initialize student details.
	 * 
	 * @param name       Name of the student
	 * @param age        Age of the student
	 * @param course     Course name
	 * @param university University name
	 */
	public Student(String name, int age, String course, String university) {
		super(name, age);
		this.course = course;
		this.university = university;
	}

	/**
	 * Displays course and university details.
	 */
	public void showStudentDetails() {
		System.out.println("Course : " + course);
		System.out.println("University : " + university);
	}

}