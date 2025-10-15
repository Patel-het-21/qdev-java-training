package practicals.oops.polymorphism.compiletime;

/*
 * Author : Het Patel 
 * Date : 15/10/25
 * Description : Implement compile time polymorphisam
 */
public class AdditionOfNumbers {

	//addition with 2 numbers
	public void addition(int number1,int number2) {
		System.out.println("Addition of int : " + number1 + " + " + number2 + " = " + (number1+number2));
	}
	
	//addition with 3 number
	public void addition(int number1,int number2, int number3) {
		System.out.println("Addition of int : " + number1 + " + " + number2 + " + " + number3 + " = " + (number1+number2+number3));
	}
	
	//addition with 4 number
	public void addition(int number1,int number2, int number3, int number4) {
		System.out.println("Addition of int : " + number1 + " + " + number2 + " + " + number3 + " + " + number4 +  " = " + (number1+number2+number3+number4));
	}
	
	//addition of 2 number but parameter datatype is float
	public void addition(double number1,double number2) {
		System.out.println("Addition of double : " + number1 + " + " + number2 + " = " + (number1+number2));
	}
	
	
	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		
		AdditionOfNumbers additionOfNumbers = new AdditionOfNumbers();
		//call method of 2 parameters
		additionOfNumbers.addition(12,30);
		
		//call method of 2 parameters datatype float
		additionOfNumbers.addition(12d,30f);

		//call method of 3 parameter
		additionOfNumbers.addition(12,30,8);
		
		//call method of 4 parameter
		additionOfNumbers.addition(12,30,8,10);

	}

}
