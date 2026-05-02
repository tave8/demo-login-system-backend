package giuseppetavella.demo_login_system.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import giuseppetavella.demo_login_system.models.EmailAttachment;
import giuseppetavella.demo_login_system.exceptions.EmailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * Send an email.
     * Many attachments.
     * 
     * @throws EmailSendingException if any problem occurred during email sending
     */
    public String sendEmail(String recipient, 
                            String subject, 
                            String html,
                            List<EmailAttachment> attachments) throws EmailSendingException 
    {
        
        CreateEmailOptions params = this.buildEmailParams(
                recipient, 
                subject, 
                html, 
                this.toAPIAttachments(attachments)
        );
        
        try {
            
            CreateEmailResponse data = resend.emails().send(params);
            return data.getId();
            
        } catch (ResendException e) {
            throw new EmailSendingException(e.getMessage());
        }
    }

    /**
     * Send an email.
     * One attachment.
     */
    public String sendEmail(String recipient,
                               String subject,
                               String html,
                               EmailAttachment attachment) throws EmailSendingException
    {

        return this.sendEmail(recipient, subject, html, List.of(attachment));
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
     * Build the email params.
     * Can add attachments.
     * API-specific.
     */
    private CreateEmailOptions buildEmailParams(String recipient, 
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
     * API-specific.
     */
    private CreateEmailOptions buildEmailParams(String recipient,
                                               String subject,
                                               String html)
    {
        return this.buildEmailParams(recipient, subject, html, List.of());
    }


    /**
     * Turn a list of app attachments, to API-specific attachments.
     * (adapter/translation layer)
     */
    private List<Attachment> toAPIAttachments(List<EmailAttachment> emailAttachments) 
    {
        List<Attachment> attachments = new ArrayList<>();

        for(EmailAttachment emailAttachment : emailAttachments) {
            // library-specific object
            Attachment attachment = Attachment.builder()
                                        .fileName(emailAttachment.getFilename())
                                        .content(emailAttachment.getBase64Content())
                                        .build();

            attachments.add(attachment);
        }
        
        return attachments;
    }
    

}
