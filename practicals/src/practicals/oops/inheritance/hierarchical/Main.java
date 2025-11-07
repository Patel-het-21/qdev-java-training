package practicals.oops.inheritance.hierarchical;

/**
 * Description : Hierarchical Inheritance
 * 
 * @since 14/10/25
 * @author Het Patel
 */
public class Main {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		/**
		 * Create and display teacher
		 */
		Teacher teacher = new Teacher();
		teacher.setName("Deep");
		teacher.setSubject("Math");
		teacher.displayTeacher();
		System.out.println();
		/**
		 * Create and display student details
		 */
		Student student = new Student();
		student.setName("Hiren");
		student.setRollNumber(25);
		student.displayStudent();
	}

}