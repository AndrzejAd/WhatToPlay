package whattoplay.exceptions;

/**
 *
 * @author Andrzej
 */
public class NotValidPasswordException extends RuntimeException {

    public NotValidPasswordException() {
        super("Password doesnt have a number or its length isnt between 8 and 32.");
    }

    public NotValidPasswordException(String message) {
        super(message);
    }
    
    
    
}
