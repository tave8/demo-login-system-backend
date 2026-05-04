package giuseppetavella.demo_login_system.exceptions;

public class HtmlTemplateException extends RuntimeException {
    public HtmlTemplateException(String message) {
        super("Error while working with / filling a HTML template. DETAILS: " + message);
    }
}
