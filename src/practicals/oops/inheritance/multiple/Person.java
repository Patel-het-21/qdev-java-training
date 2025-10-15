package practicals.oops.inheritance.multiple;

public interface Person {
	
	//default display method of person
	default void display() {
		System.out.println("This is display method of person");
	}
}
