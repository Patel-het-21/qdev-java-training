package practicals.oops.inheritance.hierarchical;

/**
 * Author      : Het Patel  
 * Date        : 14/10/25  
 * Description : Hierarchical Inheritance
 */
public class Main {

	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		
		Teacher teacher = new Teacher();
        teacher.setName("Deep");
        teacher.setSubject("Math");
        teacher.displayTeacher();

        System.out.println();

        Student student = new Student();
        student.setName("Hiren");
        student.setRollNumber(25);
        student.displayStudent();
	}

}
