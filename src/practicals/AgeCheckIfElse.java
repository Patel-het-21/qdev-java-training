package practicals;

import java.util.Scanner; 

/*
 * Author : Het Patel
 * Date : 11/10/25
 * Description: If/Else condition (age check)
 */
public class AgeCheckIfElse {

	/**
     * This is the main method, the entry point for the Java application.
     * whether a person is eligible for voting based on their age.
     *
     * @param args Command line arguments (not used in this program).
     */
	public static void main(String[] args) {
		
		//create a Scanner object to read input form the user
		Scanner scanner = new Scanner(System.in); 
		
		//Prompt the user to enter their age
		System.out.print("Enter your Age : "); 
		
		//Read the age as an integer from user input
		int age = scanner.nextInt(); 
		
		
	    //Check if age is greater than or equal to 18
		if(age >= 18) {
			//if true, print eligible message
			System.out.println("You are eligible for voting"); 
		}else {
			//if false, print not eligible message
			System.out.println("You are not eligible for voting"); 
		}
		
	}

}
