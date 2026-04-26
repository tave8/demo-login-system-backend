package giuseppetavella.demo_login_system.exceptions;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String message) {
        super("Error while sending an email: DETAILS: " + message);
    }
}
