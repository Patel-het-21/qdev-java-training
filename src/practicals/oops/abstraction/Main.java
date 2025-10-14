package practicals.oops.abstraction;


/**
 * Author      : Het Patel  
 * Date        : 14/10/25  
 * Description : Demonstrates data abstraction using abstract classes.
 */
public class Main {

	/**
     * This is the main method, the entry point for the Java application.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		
		// Create BankAccount reference to a SavingsAccount object
		BankAccount saving = new SavingsAccount("Deep", 10000);
		
		// Create BankAccount reference to a CurrentAccount object
		BankAccount current = new CurrentAccount("Hiren", 5000);
		
		saving.deposit(1000);
		saving.showBalance();
		
		saving.withdraw(2000);
		saving.showBalance();
		
		current.deposit(3000);
		current.showBalance();
        
		current.withdraw(9000);  // within overdraft
		current.showBalance();
		
		// Downcasting
		if(saving instanceof SavingsAccount) {
			SavingsAccount savingsAccount = (SavingsAccount) saving;
			savingsAccount.addInterest();
			savingsAccount.showBalance();
		}else {
			System.out.println("Not Saving Account Instance");
		}
		
		
	}

}
