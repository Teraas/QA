package concurrency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author harish.kumar-mbp
 * createdOn 05/08/23
 */
public class CustomBlockingQueueTest1 {
    MyBlockingQueue queue = new MyBlockingQueue(10); // Is  thread safe

    public void producer(){
        final Runnable producer = () -> {
            while (true){
                System.out.println("here1");
                queue.put(getValue());
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
                String val = queue.take().toString();
                System.out.println("here2 " + val);
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
        CustomBlockingQueueTest1 test = new CustomBlockingQueueTest1();
        test.producer();
        test.consumer();
        Thread.sleep(1000);
    }
// using lock
class MyBlockingQueue<E> {
    Queue<E> queue; // Is not thread safe
    Lock lock = new ReentrantLock(true);
    int maxSize = 20;
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    public MyBlockingQueue(int size){
        queue = new LinkedList();
        this.maxSize = size;

    }
    public void put(E e){
        lock.lock();
        try{
            while(queue.size()>=maxSize){
                //block the thread
                // Use Conditions to manage block condition and wait
                notFull.await();
            }

            queue.add(e);
            notEmpty.signalAll();
        } catch(Exception ex){
            System.out.println(" Exception - " + ex.getMessage());
        } finally {
            lock.unlock();
        }


    }

    public E take(){
        lock.lock();
        E val = null;
        try{
            while(queue.isEmpty()){ // so every waiting thread check the condition again
                // block the thread
                notEmpty.await();
            }
            val = queue.remove();
            notFull.signalAll();

        } catch(Exception ex){
            System.out.println(" Exception - " + ex.getMessage());
        } finally {
            lock.unlock();
        }


        return val;

    }

}
}
