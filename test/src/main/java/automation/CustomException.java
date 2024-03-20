package automation;

/**
 * @author harish.kumar-mbp
 * createdOn 15/02/24
 */
public class CustomException extends Exception {
    public CustomException() {
        super("This is a custom exception.");
    }

    public CustomException(String message) {
        super(message);
    }
}
