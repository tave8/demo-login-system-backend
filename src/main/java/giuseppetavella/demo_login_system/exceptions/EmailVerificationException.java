package giuseppetavella.demo_login_system.exceptions;

public class EmailVerificationException extends RuntimeException {
    public EmailVerificationException(String message) {
        super("Error regarding account email verification. DETAILS: " + message);
    }
}
