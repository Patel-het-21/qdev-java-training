package practicals.oops.inheritance.hybrid;

public class Student extends Person{
	protected String course;
	protected String university;
	
	public Student(String name,int age,String course,String university) {
		super(name,age);
		this.course = course;
		this.university = university;
	}
	
	public void showStudentDetails() {
		System.out.println("Course : " + course);
		System.out.println("University : " + university);
	}
}
