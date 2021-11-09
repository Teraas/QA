package Tests;

public class ThreadWaitTest  {

	int amount = 9000;

	// synchronized function because i wil use for
	// waiting thread. Here synchronized means inter-
	// thread communication
	synchronized void withdraw(int amount)
	{
		System.out.println("withdrawing...");

		// check if balance amount is less than withdraw
		// amount in this condition. deposit()  synchronized
		// method call and deposit amount after that this
		// thread again execute
		if (this.amount < amount) {

			System.out.println("Amount is not enough; waiting + " +
					" for deposit...");
			try {
				wait();
			}
			catch (Exception e) {
			}
		}

		System.out.println("after deposit, balance is available: " +
				this.amount);
		this.amount -= amount;
		System.out.println("withdraw completed...");
		System.out.println("after Withdraw, balance is available: " +
				this.amount);
	}

	synchronized void deposit(int amount)
	{
		System.out.println("going to deposit...");
		this.amount += amount;
		System.out.println("deposit completed... ");
		notify(); // It has to be called for the waiting thread to resume. This should also be called form synchronized block
	}
}

class Wait {

	public static void main(String args[]) {
		final ThreadWaitTest c = new ThreadWaitTest();
		// create two new thread and start
		// them simultaneously
		new Thread() {
			@Override
			public void run() {
				c.withdraw(15000);
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				c.deposit(10000);
			}
		}.start();
	}
}
