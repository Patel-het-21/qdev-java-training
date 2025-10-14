package practicals.oops.abstraction;

public class CurrentAccount extends BankAccount{

	private final double OVERDRAFT_LIMIT = 5000;
	
	public CurrentAccount(String accountHolder, double balance) {
		super(accountHolder, balance);
	}

	@Override
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited " + amount + " in Savings Account.");
	}

	@Override
	public void withdraw(double amount) {
		if(amount>balance + OVERDRAFT_LIMIT) {
			System.out.println("Overdraft limit exceeded.");
		}else {
			balance-=amount;
			System.out.println("Withdrawn " + amount + " from Current Account.");
		}
	}

	
}
