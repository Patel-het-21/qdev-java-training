package practicals.oops.abstraction;

/**
 * Inherits from BankAccount and allows overdraft withdrawals.
 * 
 * @author Het
 * @since 14/10/25
 */
public class CurrentAccount extends BankAccount {

	private final double OVERDRAFT_LIMIT = 5000;

	/**
	 * Constructor to initialize current account details.
	 * 
	 * @param accountHolder Name of account holder
	 * @param balance       Initial balance
	 */
	public CurrentAccount(String accountHolder, double balance) {
		super(accountHolder, balance);
	}

	/**
	 * Deposits the specified amount into the current account.
	 * 
	 * @param amount Amount to deposit
	 */
	@Override
	public void deposit(double amount) {
		balance += amount;
		System.out.println("Deposited " + amount + " in Savings Account.");
	}

	/**
	 * Withdraws the specified amount from the current account. Allows overdraft up
	 * to the overdraft limit.
	 * 
	 * @param amount Amount to withdraw
	 */
	@Override
	public void withdraw(double amount) {
		if (amount > balance + OVERDRAFT_LIMIT) {
			System.out.println("Overdraft limit exceeded.");
		} else {
			balance -= amount;
			System.out.println("Withdrawn " + amount + " from Current Account.");
		}
	}

}