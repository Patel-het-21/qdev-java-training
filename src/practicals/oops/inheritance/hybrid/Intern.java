package practicals.oops.inheritance.hybrid;

import java.util.Scanner;

/*
 * 	Author : Patel Het
 * 	Date : 15/10/25
 * 	Description : Implement hybrid inheritance 
 */
public class Intern extends Student implements Employee{
	
	private String jobTitle;
	private double salary;
	
	
	public Intern(String name, int age, String course, String university, String jobTitle, double salary) {
		super(name, age, course, university);
		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	@Override
	public void getJobTitle() {
		System.out.println("Job Title : " + jobTitle);
	}

	@Override
	public void getSalary() {
		System.out.println("Salary : " + salary);
	}

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String args[]) {
		
		//scanner class object
		Scanner scanner = new Scanner(System.in);
		
		//take input from user
		System.out.println("Enter Name : ");
		String name = scanner.nextLine();
		System.out.println("Enter Age : ");
		int age = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter course : ");
		String course = scanner.nextLine();
		System.out.println("Enter unniversity : ");
		String university = scanner.nextLine();
		System.out.println("Enter Job title : ");
		String jobtitle = scanner.nextLine();
		System.out.println("Enter salary : ");
		double salary = scanner.nextDouble();
	
		//create object of intern class and pass parameters for constructor
		Intern intern = new Intern(name, age, course, university, jobtitle, salary);
		
		//call person class method
		intern.showPersonDetails();

		//call student class method
		intern.showStudentDetails();
		
		//call employee interface method
		intern.showEmployeeDetails();
		
		//get job title and salary
		intern.getJobTitle();
		intern.getSalary();
	}
	
}
