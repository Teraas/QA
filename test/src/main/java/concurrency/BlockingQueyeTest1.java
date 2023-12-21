package concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author harish.kumar-mbp
 * createdOn 05/08/23
 */
public class BlockingQueyeTest1 {
    BlockingQueue queue = new ArrayBlockingQueue(10); // Is thread safe

    public void producer(){
        final Runnable producer = () -> {
            while (true){
                try {
                    System.out.println("here1");
                    queue.put(getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        Thread Thread1 = new Thread(producer);
        Thread Thread2 = new Thread(producer);
        Thread1.start();
        Thread2.start();
    }
    public void consumer(){
        final Runnable consumer = () -> {
            while (true){
                try {
                    String val = queue.take().toString();
                    System.out.println("here2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        Thread Thread1 = new Thread(consumer);
        Thread Thread2 = new Thread(consumer);
        Thread1.start();
        Thread2.start();
    }

    private String getValue() {
        return "test"+new Random(1212).nextInt();
    }

    public static void main(String[] agrs) throws InterruptedException {
        BlockingQueyeTest1 test = new BlockingQueyeTest1();
        test.producer();
        test.consumer();
        Thread.sleep(1000);
    }


}
