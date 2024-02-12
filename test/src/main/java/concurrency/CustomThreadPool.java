package concurrency;

/**
 * @author harish.kumar-mbp
 * createdOn 11/02/24
 */
public class CustomThreadPool {

    //private volatile int corePoolSize;

    private volatile int maximumPoolSize;

    MyBlockingQueue queue;

    public CustomThreadPool(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        queue = new MyBlockingQueue(10);
    }
}
