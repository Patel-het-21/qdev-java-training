package practicals.oops.interfaceTopic;

// Dog class implements Animal interface
public class Dog implements Animal{

	// implementation of makeSound() for Dog
	public void makeSound() {
		System.out.println("Dog Barks");
	}
	
    // Dog-specific method
	void fetch() {
		System.out.println("Dog fetches the ball");
	}
}
