package giuseppetavella.demo_login_system.exceptions;

public class InvalidUUIDStringException extends InvalidDataFormatException {
    public InvalidUUIDStringException(String itemId) {
        super("The string '" + itemId + "' cannot be cast to a valid UUID.");
    }
}
