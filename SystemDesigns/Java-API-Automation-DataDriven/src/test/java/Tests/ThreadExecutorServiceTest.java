package Tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorServiceTest {


	public static void main(String[] args) {
		A obj = new A();
		B obj2 = new B();
		//obj.start();
		//obj2.start();
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(obj);
		service.execute(obj2);
		System.out.println("Mainn thread   " + 1111111111*1111111111*1111111111*1111111111*1111111111);
		service.shutdown();

		//obj.show();
		//obj2.show();
	}
}


class A extends Thread {
	public void run() {
		show();;
	}
	public void show() {
		for(int i=0;i<5;i++) {
			System.out.println("Hi");
			try { Thread.sleep(1000);} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class B extends Thread {

	public void run() {
		show();
	}
	public void show() {
		for(int i=0;i<5;i++) {
			System.out.println("Hello");
			try { Thread.sleep(1000);} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
