package DesignPatterns.example;

/**
 * @author harish.kumar-mbp
 * @created 21/02/23
 */
public class Notification {
    String notifyTo = "email";
    public void sentNotification( String email){
        this.notifyTo = email;
    }

}
