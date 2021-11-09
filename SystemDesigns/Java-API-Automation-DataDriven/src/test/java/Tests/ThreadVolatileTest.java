package Tests;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadVolatileTest {

	public static void main(String args[]) throws InterruptedException {
	 final  Count c = new Count();

		Thread t = new Thread(new Runnable() {
			public void run() {
				for(int i=1;i<=1000;i++) {
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					try {
						c.increment();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=1;i<=1000;i++) {
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					try {
						c.increment();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();

		t2.start();
		t.join();
		t2.join();

		System.out.println(" Current counter " + c.counter);
		System.out.println(" Current counter1 " + c.counter1);
	}
}

class Count {
	  volatile int counter1; // Volatile can not trusted 100% for memory synchronization purposes.
	  AtomicInteger counter = new AtomicInteger();
	  Lock lock = new ReentrantLock();
	public  void increment() throws InterruptedException {
		counter.addAndGet(1);
		boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
		//lock.lock();
		if(isLockAcquired)
			counter1++;
		lock.unlock();
	}
}
