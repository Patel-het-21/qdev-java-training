package practicals;

/**
 * Author      : Het Patel  
 * Date        : 14/10/25  
 * Description : Use this and super keywords to access class members and constructors.
 */
public class StaticDemo {
	
	//static variable
	static int count=0; 
	
	//static block
	static {
		System.out.println("This is static block"); 
		count = 10; 
	}
	
	//another static block
	static {
		System.out.println("This is 2nd static block"); 
	}
	
	//non static block
	{
		System.out.println("Non static block"); 
	}
	
	//another non static block
	{
		System.out.println("This is 2nd non static block"); 
	}
	
	//static method
	static void addition() {
		int number1 = 20; 
		int number2 = 30; 
		
		System.out.println("Addition of 20 and 30 is " + (number1 + number2)); 
	}
	
	//non static method
	void multiplication() {
		int number1 = 2; 
		int number2 = 4; 
		
		System.out.println("Multiplication of 2 and 4 is " + (number1 * number2)); 
	}
	
	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		
		StaticDemo staticDemo = new StaticDemo(); 
		staticDemo.multiplication(); 
		
		//call static method 
		StaticDemo.addition(); 

		System.out.println("Count is " + StaticDemo.count); 
		
		StaticDemo staticDemo2 = new StaticDemo(); 
		staticDemo2.multiplication(); 
	}

}
