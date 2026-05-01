package giuseppetavella.demo_login_system.models;

import giuseppetavella.demo_login_system.exceptions.FileDownloadException;
import giuseppetavella.demo_login_system.helpers.FileHelper;

import java.io.IOException;

public class EmailAttachmentFromURL extends EmailAttachment {
    public EmailAttachmentFromURL(String url, String attachmentFilename) throws FileDownloadException 
    {
        super(FileHelper.urlToBase64(url), attachmentFilename);
    }
}
