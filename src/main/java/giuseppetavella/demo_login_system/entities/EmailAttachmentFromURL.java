package giuseppetavella.demo_login_system.entities;

import giuseppetavella.demo_login_system.helpers.FileHelper;

import java.io.IOException;

public class EmailAttachmentFromURL extends EmailAttachment {
    public EmailAttachmentFromURL(String url, String attachmentFilename) throws IOException {
        super(FileHelper.urlToBase64(url), attachmentFilename);
    }
}
