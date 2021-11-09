public class ThreadTest {


	public static void main(String[] args) throws InterruptedException {
		Hi obj = new Hi();
		Hello obj2 = new Hello();
		obj.start();
		obj.join();
		System.out.println("first thread " + obj.getName() + " " + obj.getPriority());
		obj2.start();
		System.out.println("first thread " + obj2.getName() + " " + obj2.getPriority());
		//obj.show();
		//obj2.show();
	}
}

class Hi extends Thread {
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
class Hello extends Thread {

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
