package practicals.oops.inheritance.hybrid;

import java.util.Scanner;

/**
 * Represents an Intern, which extends the Student class and implements the
 * Employee interface. Demonstrates hybrid inheritance in Java (class +
 * interface). Combines personal, academic, and job-related details in one
 * class.
 * 
 * @author Het
 * @since 15/10/25
 */
public class Intern extends Student implements Employee {

	private String jobTitle;
	private double salary;

	/**
	 * Constructor to initialize all intern details.
	 * 
	 * @param name       Name of the intern
	 * @param age        Age of the intern
	 * @param course     Course the intern is studying
	 * @param university University of the intern
	 * @param jobTitle   Job title of the intern
	 * @param salary     Salary or stipend of the intern
	 */
	public Intern(String name, int age, String course, String university, String jobTitle, double salary) {
		super(name, age, course, university);
		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	/**
	 * Displays the job title of the intern.
	 */
	@Override
	public void getJobTitle() {
		System.out.println("Job Title : " + jobTitle);
	}

	/**
	 * Displays the salary or stipend of the intern.
	 */
	@Override
	public void getSalary() {
		System.out.println("Salary : " + salary);
	}

	/**
	 * Main method – entry point of the program. Accepts user input, creates an
	 * Intern object, and displays all details.
	 * 
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		/**
		 * Accept input from user
		 */
		System.out.print("Enter Name : ");
		String name = scanner.nextLine();
		System.out.print("Enter Age : ");
		int age = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Course : ");
		String course = scanner.nextLine();
		System.out.print("Enter University : ");
		String university = scanner.nextLine();
		System.out.print("Enter Job Title : ");
		String jobTitle = scanner.nextLine();
		System.out.print("Enter Salary : ");
		double salary = scanner.nextDouble();

		/**
		 * Create Intern object
		 */
		Intern intern = new Intern(name, age, course, university, jobTitle, salary);

		/**
		 * Display details
		 */
		System.out.println("\n--- Intern Details ---");
		intern.showPersonDetails(); // From Person
		intern.showStudentDetails(); // From Student
		intern.showEmployeeDetails(); // From Employee (default method)
		intern.getJobTitle(); // From Employee (implemented)
		intern.getSalary(); // From Employee (implemented)
		scanner.close();
	}

}