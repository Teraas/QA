package concurrency;

import java.util.concurrent.*;

import DesignPatterns.example.User;

public class ThreadsTest {
    public ThreadsTest(User user) {
        this.user = user;
    }

    User user;

    public static void main(String[] args){
        ExecutorService service1 = new ThreadPoolExecutor(1,2,2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));
        ExecutorService service = Executors.newFixedThreadPool(4);
        Future f = service.submit(new Test());
        Future f2 = service1.submit(new Test());
        try {
            f.get(); // get response from thread execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setEmail("email");
        ThreadsTest test = new ThreadsTest(user);
        System.out.println(test.getUser().getEmail());
        service.execute(() -> 
            System.out.println(test.getUser().getEmail())
            );
        for(int i =0;i<10;i++){

        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    static class Test implements Runnable {

        @Override
        public void run() {

        }
    }
}
