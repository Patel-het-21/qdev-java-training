package practicals.oops.abstraction;

/**
 * Description : Demonstrates data abstraction using abstract classes.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Main {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		/**
		 * Create BankAccount reference to a SavingsAccount object
		 */
		BankAccount saving = new SavingsAccount("Deep", 10000);
		/**
		 * Create BankAccount reference to a CurrentAccount object
		 */
		BankAccount current = new CurrentAccount("Hiren", 5000);
		/**
		 * Performing deposit and withdrawal on savings account.
		 */
		saving.deposit(1000);
		saving.showBalance();
		saving.withdraw(2000);
		saving.showBalance();
		/**
		 * Performing deposit and withdrawal on current account.
		 */
		current.deposit(3000);
		current.showBalance();
		current.withdraw(9000);
		current.showBalance();

		/**
		 * Downcasting: Accessing subclass-specific method (addInterest) from the
		 * abstract class reference.
		 */
		if (saving instanceof SavingsAccount) {
			SavingsAccount savingsAccount = (SavingsAccount) saving;
			savingsAccount.addInterest();
			savingsAccount.showBalance();
		} else {
			System.out.println("Not Saving Account Instance");
		}
	}

}