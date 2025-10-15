package practicals.oops.inheritance.multiple;

public interface Employee {
	
	//default display method of employee 
	default void display() {
		System.out.println("This is display method employee");
	}
}
