package giuseppetavella.demo_login_system.exceptions;

public class AIException extends RuntimeException {
    public AIException(String message) {
        super("Error while working with AI. DETAILS: " + message);
    }
}
