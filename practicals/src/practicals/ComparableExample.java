package practicals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This program uses Comparable to sort Student objects by roll number.
 * 
 * @author Het Patel
 * @since 17/10/25
 */
class Student implements Comparable<Student> {

	private int rollno;
	private String name;

	/**
	 * Constructs a object with a specified roll number and name.
	 * 
	 * @param rollno the student's roll number
	 * @param name   the student's name
	 */
	public Student(int rollno, String name) {
		this.rollno = rollno;
		this.name = name;
	}

	/**
	 * Compares this student with the specified student for order based on roll
	 * number.
	 * 
	 * @param o the student to be compared
	 * @return a negative integer, zero, or a positive integer as this roll number
	 *         is less than, equal to, or greater than the specified student's roll
	 *         number
	 */
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return this.rollno - o.rollno;
	}

	/**
	 * @return a string in the format "Student [rollno=..., name=...]"
	 */
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + "]";
	}

}

public class ComparableExample {

	/**
	 * The entry point of the program. Creates a list of students, displays them
	 * before and after sorting.
	 * 
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student(3, "Manan"));
		list.add(new Student(1, "Om"));
		list.add(new Student(2, "Hiren"));

		System.out.println("Before sorting : ");
		for (Student s : list) {
			System.out.println(s);
		}

		Collections.sort(list);

		System.out.println("After sorting by roll number : ");
		for (Student s : list) {
			System.out.println(s);
		}
	}

}