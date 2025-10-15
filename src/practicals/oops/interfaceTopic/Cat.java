package practicals.oops.interfaceTopic;

// cat class implements Animal interface
public class Cat implements Animal{

	//Implementation of makeSound() for cat   
	public void makeSound() {
		System.out.println("Cat meows");
	}
	
	//cat-specific method
	void scratch() {
		System.out.println("Cat scratches the furniture");
	}
	
}
