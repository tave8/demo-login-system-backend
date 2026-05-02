package giuseppetavella.demo_login_system.exceptions;

public class FileDownloadException extends FileException {
    public FileDownloadException(String message) {
        super("Error while fetching or downloading a remote file. DETAILS: " + message);
    }
}
