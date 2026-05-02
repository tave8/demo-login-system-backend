package giuseppetavella.demo_login_system.exceptions;

public class UnknownFileTypeException extends RuntimeException {
    public UnknownFileTypeException(String mimeType) {
        super("Could not determine file extension for MIME type: " + mimeType);
    }
}
