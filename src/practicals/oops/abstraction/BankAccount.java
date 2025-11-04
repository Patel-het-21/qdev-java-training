package practicals.oops.abstraction;

/**
 * Abstract class representing a generic bank account. Provide structure for
 * account operations such as deposit, withdraw and show balance.
 * 
 * @author Het
 * @since 14/10/25
 */

public abstract class BankAccount {

	protected String accountHolder;
	protected double balance;

	/**
	 * 
	 * @param accountHolder Name of account holder
	 * @param balance       Initial balance
	 */
	public BankAccount(String accountHolder, double balance) {
		this.accountHolder = accountHolder;
		this.balance = balance;
	}

	/**
	 * Abstract method to deposit money in to account
	 * 
	 * @param amount Amount to deposit
	 */
	public abstract void deposit(double amount);

	/**
	 * Abstract method to withdraw money from account
	 * 
	 * @param amount Amount to withdraw
	 */
	public abstract void withdraw(double amount);

	/*
	 * Method to show balance of account
	 */
	public void showBalance() {
		System.out.println("Account Holder Name : " + accountHolder);
		System.out.println("Balance : " + balance);
	}

}