package practicals;

/**
 * Author      : Het Patel  
 * Date        : 13/10/25  
 * Description : Use this and super keywords to access class members and constructors.
 */

class Animal {
	String name  = "Animal"; 
	
	//Parent class Method
	void makeSound() {
		System.out.println("Animal ---> Make Sound"); 
	}
}

class Dog extends Animal{
	String name = "Dog";
	
	//default constructor
	Dog(){
		this("Default Breed");
		System.out.println("Dog default constructor calls"); 
	}
	
	//Parameterized constructor
	Dog(String breed){
			System.out.println("Dog Parameterized constructor called with breed : " + breed); 
	}
	
	//Method to demonstrate 'this' and 'super'
	void displayNames() {
		System.out.println("this.name= " + this.name); 
		
		System.out.println("super.name = " + super.name); 
	}
	
	//override parent methods
	void makeSound() {
		
		//call parent class method
		super.makeSound(); 
		
		System.out.println("Dog Barks"); 
	}
}

public class ThisSuperKeywordUsage {

	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		Dog dog = new Dog(); 
		
		dog.displayNames(); 
		
		dog.makeSound(); 
	}

}


