package practicals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class represents a Student1 with roll number and name.
 * 
 * @author Het Patel
 * @since 17/10/25
 */
class Student1 {

	int rollNo;
	String name;

	/**
	 * Constructor to initialize Student1 object.
	 * 
	 * @param rollNo Roll number of the student
	 * @param name   Name of the student
	 */
	public Student1(int rollNo, String name) {
		this.name = name;
		this.rollNo = rollNo;
	}

	/**
	 * Returns a string representation of the Student1 object.
	 * 
	 * @return Formatted string with roll number and name
	 */
	@Override
	public String toString() {
		return "Student1 [rollNo=" + rollNo + ", name=" + name + "]";
	}

}

/**
 * Comparator implementation to compare Student1 objects based on their names.
 * It performs a case-insensitive comparison of the student names.
 * 
 */
class NameComparator implements Comparator<Student1> {

	/**
	 * Compares two Student1 objects based on their name in a case-insensitive
	 * manner.
	 * 
	 * @param o1 First Student1 object
	 * @param o2 Second Student1 object
	 * @return Negative integer, zero, or positive integer if the name of o1 is less
	 *         than, equal to, or greater than the name of o2 respectively.
	 */
	@Override
	public int compare(Student1 o1, Student1 o2) {
		return o1.name.compareToIgnoreCase(o2.name);
	}

}

/**
 * This class demonstrates sorting a list of Student1 objects using a
 * Comparator. It sorts students by their names.
 * 
 * The output shows the list before and after sorting.
 * 
 */
public class ComparatorExample {

	/**
	 * The main method where the program execution starts. It creates a list of
	 * Student1 objects, displays them before sorting, sorts them by name using
	 * NameComparator, and displays after sorting.
	 * 
	 * @param args Command line arguments (not used here)
	 */
	public static void main(String[] args) {
		List<Student1> list = new ArrayList<>();
		list.add(new Student1(1, "Om"));
		list.add(new Student1(2, "Hiren"));
		list.add(new Student1(3, "Amit"));
		list.add(new Student1(4, "Deep"));
		list.add(new Student1(5, "Manan"));

		System.out.println("Before Sorting : ");
		for (Student1 s : list) {
			System.out.println(s);
		}

		// Sorting the list using NameComparator (by student name)
		Collections.sort(list, new NameComparator());

		System.out.println("After Sorting : ");
		for (Student1 s : list) {
			System.out.println(s);
		}
	}

}