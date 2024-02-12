package concurrency;

import java.util.concurrent.*;

/**
 * @author harish.kumar-mbp
 * createdOn 11/02/24
 */
public class ExampleCyclicBarrier {

    public static void main(String[] args){
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Task1(barrier));
        executor.submit(new Task1(barrier));
        executor.submit(new Task1(barrier));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task1 implements Runnable {
    private CyclicBarrier barrier;

    public Task1(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        while(true){
            try {
                barrier.await(); // all task thread would come to this point then only all will go further
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("RUnning my task");
        }


    }
}
