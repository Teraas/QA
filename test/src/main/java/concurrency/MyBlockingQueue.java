package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author harish.kumar-mbp
 * createdOn 11/02/24
 */
public class MyBlockingQueue<E> {
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
