package practicals.oops.abstraction;

/**
 * Inherits from BankAccount and adds interest logic.
 * 
 * @author Het
 * @since 14/10/25
 */
public class SavingsAccount extends BankAccount {

	/**
	 * Constructor to initialize savings account details.
	 * 
	 * @param accountHolder Name of Account holder
	 * @param amount        Initial balance
	 */
	public SavingsAccount(String accountHolder, double amount) {
		super(accountHolder, amount);
	}

	/**
	 * Deposits specific amount into the Saving Account
	 * 
	 * @param amount Amount to deposit
	 */
	@Override
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited " + amount + " in Savings Account.");
	}

	/**
	 * withdraw specific amount into the Saving Account. Fails if balance is
	 * insufficient.
	 * 
	 * @param amount Amount to withdraw
	 */
	@Override
	public void withdraw(double amount) {
		if (amount > balance) {
			System.out.println("Insufficient Balance");
		} else {
			balance -= amount;
			System.out.println("Withdrawn " + amount + " from Savings Account.");
		}
	}

	/**
	 * Add interest in to saving account
	 */
	public void addInterest() {
		System.out.println("Inside Add interest method");
	}

}