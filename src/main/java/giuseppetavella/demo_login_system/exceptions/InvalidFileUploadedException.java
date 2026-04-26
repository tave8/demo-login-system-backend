package giuseppetavella.demo_login_system.exceptions;

public class InvalidFileUploadedException extends RuntimeException {
    public InvalidFileUploadedException(String message) {
        super("File sent is not valid. DETAILS: " + message);
    }
}
