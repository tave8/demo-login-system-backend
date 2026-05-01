package giuseppetavella.demo_login_system.exceptions;

public class CsvGenerationException extends RuntimeException {
    public CsvGenerationException(String message) {
        super("Error while generating a CSV. DETAILS: " + message);
    }
}
