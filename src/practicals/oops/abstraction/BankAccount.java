package practicals.oops.abstraction;

//abstract base class
public abstract class BankAccount {
	protected String accountHolder; 
	protected double balance; 
	
	//pera constructor
	public BankAccount(String accountHolder,double balance) {
		this.accountHolder = accountHolder; 
		this.balance = balance; 
	}
	
	//abstract methods
	public abstract void deposit(double amount); 
	public abstract void withdraw(double amount); 
	
	public void showBalance(){
		System.out.println("Account Holder Name : " + accountHolder); 
		System.out.println("Balance : " + balance); 
	}
}
