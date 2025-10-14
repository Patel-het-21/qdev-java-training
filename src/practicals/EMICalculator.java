package practicals;

import java.util.Scanner;

/**
 * Practical 8: EMI Calculator using class, object, variables, operators, and datatypes.
 * Author      : Het Patel
 * Date        : 11/10/25
 * Description : This program calculates EMI based on loan amount, interest rate,and loan tenure (in months) using proper Java coding practices.
 */
public class EMICalculator {
	
	//variables
	double loanAmount; 
	double annualInterestRate;  
	float loanTenureInMonths; 
	
	/*
	 * Parameterized Constructor
	 * 
	 * @param loanAmount	      	Principal loan amount
	 * @param annualInterestRate	Annual interest rate (in %)
	 * @param loanTenureInMonths	Loan tenure in months
	 */
	public EMICalculator(double loanAmount, double annualInterestRest, float loanTenureInMonths) {
		this.loanAmount = loanAmount; 
		this.annualInterestRate = annualInterestRest; 
		this.loanTenureInMonths = loanTenureInMonths; 
	}
	
	public double calculateEmi() {
		// Convert annual interest to monthly interest rate
		double monthlyInterestRate = annualInterestRate / 12 / 100; 

		// EMI formula
		double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTenureInMonths))/(Math.pow(1+monthlyInterestRate, loanTenureInMonths)-1); 
		return emi; 
	}
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in); 
		
		//getting input from user
		System.out.print("Enter Loan Amount : "); 
		double loanAmount = scanner.nextDouble(); 
		System.out.print("Enter Annual InterestRate : "); 
		double annualInterestRate = scanner.nextDouble(); 
		System.out.print("Enter Loan TenureInMonths : "); 
		float loanTenureInMonths = scanner.nextFloat(); 
		
		EMICalculator emiCalculator = new EMICalculator(loanAmount, annualInterestRate, loanTenureInMonths); 
		double emi = emiCalculator.calculateEmi(); 
		
		System.out.printf("Loan Amount : %.2f\n",loanAmount); 
		System.out.printf("Annual Interest Rate : %.2f\n",annualInterestRate); 
		System.out.printf("Loan Tenure : %d Months\n",loanTenureInMonths); 
		System.out.printf("Monthly EMI : %.2f",emi); 
		
	}
}





