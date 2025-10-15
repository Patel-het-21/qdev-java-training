package practicals.oops.interfaceTopic;

/*
 * Author : Het Patel
 * Date : 13/10/25
 * DEscription : Implement inteface topic
 */

public class Main {
	
	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String args[]) {
		
		// Declare interface reference
		Animal animal;
		
		//Assign dog object to Animal reference
		animal = new Dog();
		animal.makeSound();
		
		// Downcast to Dog to call dog-specific methods
		if(animal instanceof Dog) {
			Dog dog = (Dog) animal;
			dog.fetch();
		}
		
		// Assign cat object to Animal reference
		animal = new Cat();
		animal.makeSound();
		
		//downcast cat object to Animal reference
		if(animal instanceof Cat) {
			Cat cat = (Cat) animal;
			cat.scratch();
		}
	}
}

