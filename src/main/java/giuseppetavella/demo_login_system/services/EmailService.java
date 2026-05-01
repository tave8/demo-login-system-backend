package giuseppetavella.demo_login_system.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import giuseppetavella.demo_login_system.exceptions.EmailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Send emails.
 * Hides the email API library-specific implementation details.
 */
@Service
public class EmailService {
    
    // Email API
    @Autowired
    private Resend resend;
    
    @Autowired
    private CreateEmailOptions.Builder defaultParams;

    /**
     * Send an email with the default sender name, user and domain.
     * 
     * @throws EmailSendingException if any problem occurred during email sending
     */
    public String sendEmail(String recipient, 
                            String subject, 
                            String html,
                            List<Attachment> attachments) throws EmailSendingException 
    {

        CreateEmailOptions params = this.buildEmailParams(recipient, subject, html, attachments);
        
        try {
            CreateEmailResponse data = resend.emails().send(params);
            return data.getId();
            
        } catch (ResendException e) {
            throw new EmailSendingException(e.getMessage());
        }
    }

    /**
     * Send an email.
     * No attachments.
     */
    public String sendEmail(String recipient,
                            String subject,
                            String html) throws EmailSendingException
    {
        return this.sendEmail(recipient, subject, html, List.of());
    }


    /**
     * Send an email with an attachment.
     */
    public String sendEmailWithAttachment(String recipient,
                                          String subject,
                                          String html,
                                          String outputAttachmentFilename,
                                          String base64FileContent) throws EmailSendingException
    {

        Attachment attachment = Attachment.builder()
                .fileName(outputAttachmentFilename)
                .content(base64FileContent)
                .build();
        
        List<Attachment> attachments = List.of(attachment);
        
        return this.sendEmail(recipient, subject, html, attachments);
    }


    /**
     * Build the email params.
     * Can add attachments.
     */
    protected CreateEmailOptions buildEmailParams(String recipient, 
                                               String subject, 
                                               String html,
                                               List<Attachment> attachments) 
    {
        return this.defaultParams
                .to(recipient)
                .subject(subject)
                .html(html)
                .attachments(attachments)
                .build();
    }

    
    /**
     * Build the email params.
     * No attachments.
     */
    protected CreateEmailOptions buildEmailParams(String recipient,
                                               String subject,
                                               String html)
    {
        return this.buildEmailParams(recipient, subject, html, List.of());
    }


}
