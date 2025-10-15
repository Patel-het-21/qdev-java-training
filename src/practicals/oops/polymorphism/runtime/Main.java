package practicals.oops.polymorphism.runtime;

/*
 * Author : Het Patel 
 * Date : 15/10/25
 * Description : Implement runtime polymorphisam
 */
public class Main {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		
		//create animal object 
		Animal animal = new Animal();
		
		//call animal class make sound method
		animal.makeSound();
		
		//create dog class object with reference to animal
		Animal dog = new Dog();
		
		//call animal class makeSound method
		dog.makeSound();
		
		//create cat class object with reference to animal
		Animal cat = new Cat();

		//call animal class make sound method
		cat.makeSound();
	}

}
