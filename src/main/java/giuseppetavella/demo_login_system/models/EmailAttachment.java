package giuseppetavella.demo_login_system.models;

import giuseppetavella.demo_login_system.helpers.FileHelper;

public class EmailAttachment {
    
    // base64-encoded file
    private final String base64Content;
    
    // this is what is shown in the email
    private final String filename;
    
    public EmailAttachment(String base64Content, String filename) {
        this.base64Content = base64Content;
        this.filename = filename;
    }

    public EmailAttachment(byte[] bytes, String filename) {
        this(FileHelper.toBase64(bytes), filename);
    }
    
    public String getBase64Content() {
        return base64Content;
    }

    public String getFilename() {
        return filename;
    }
}
