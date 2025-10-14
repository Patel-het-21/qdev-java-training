package practicals.oops.abstraction;

public class SavingsAccount extends BankAccount{

	private final double INTEREST_RATE = 0.04; 
	
	public SavingsAccount(String accountHolder,double amount) {
		super(accountHolder,amount); 
	}
	
	@Override
	public void deposit(double amount) {
		balance+=amount; 
		System.out.println("Deposited " + amount + " in Savings Account.");
	}

	@Override
	public void withdraw(double amount) {
		if(amount>balance) {
			System.out.println("Insufficient Balance");
		}else {
			balance-=amount;
			System.out.println("Withdrawn " + amount + " from Savings Account.");
		}
	}
	
	//Method for SavingsAccount
	public void addInterest() {
		System.out.println("Inside Add interest method");
	}
	
}
