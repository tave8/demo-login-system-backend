package giuseppetavella.demo_login_system.exceptions;

public class ForgotPasswordVerificationException extends RuntimeException {
    public ForgotPasswordVerificationException(String message) {
        super("Error while verifying 'forgot password' authorization. DETAILS: " + message);
    }
}
