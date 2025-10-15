package practicals.oops.inheritance.multiple;

public interface Student {
	
	//default display method of student
	default void display() {
		System.out.println("This is display method of Student");
	}
}
