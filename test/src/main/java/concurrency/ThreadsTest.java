package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DesignPatterns.example.User;

public class ThreadsTest {
    public ThreadsTest(User user) {
        this.user = user;
    }

    User user;

    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(4);
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
}
