package practicals.oops.inheritance.hierarchical;

/**
 * Represents a teacher, which is a subclass of Person. Adds a subject field
 * specific to teachers.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Teacher extends Person {

	private String subject;

	/**
	 * Set the subject name
	 * 
	 * @param subject subject name
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Display teacher name and subject
	 */
	public void displayTeacher() {
		displayName();
		System.out.println("Teaches: " + subject);
	}

}