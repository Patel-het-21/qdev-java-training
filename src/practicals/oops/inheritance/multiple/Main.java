package practicals.oops.inheritance.multiple;

/*
 * Author : Patel Het
 * Date : 15/11/25
 * Description : Implement Multilevel inheritance
 */
public class Main implements Person, Employee, Student{
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		Person.super.display();
		Employee.super.display();
		Student.super.display();
		
		System.out.println("This is display method of Main");
		
	}
	
	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String args[]) {
		Main main = new Main();
		main.display();
	}

	
}
