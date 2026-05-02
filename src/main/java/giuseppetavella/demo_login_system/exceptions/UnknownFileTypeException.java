package giuseppetavella.demo_login_system.exceptions;

public class UnknownFileTypeException extends RuntimeException {
    public UnknownFileTypeException(String mimeType) {
        super("Unsupported or unrecognized file extension for MIME type: '" + mimeType 
                +"'. Possible cause: While extracting the file extension from a file, this file extensione"
                +" was not internally recognized, mapped or supported.");
    }
}
