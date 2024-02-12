package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author harish.kumar-mbp
 * createdOn 11/02/24
 */
public class ExampleCountDownLatch {

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Task(latch));
        executor.submit(new Task(latch));
        executor.submit(new Task(latch));

        try {
            latch.await(); // this is waiting until count is 0. after that main thread will continue
            // continue main thread work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Runnable {
    private CountDownLatch latch;

    public Task(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("RUnning my task");
        latch.countDown();
    }
}
