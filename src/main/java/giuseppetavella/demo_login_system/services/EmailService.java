package giuseppetavella.demo_login_system.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import giuseppetavella.demo_login_system.exceptions.EmailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Send emails.
 */
@Service
public class EmailService {
    
    @Autowired
    private Resend resend;
    
    @Autowired
    private CreateEmailOptions.Builder defaultParams;

    /**
     * Send an email.
     */
    public String sendEmail(String recipient, String subject, String html) throws EmailSendingException {

        // CreateEmailOptions params = this.defaultParams
        //         .to(recipient)
        //         .subject(subject)
        //         .html(html)
        //         .build();

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Giuseppe Tavella <info@mail.giuseppetavella.com>")
                .to("giuseppetavella8@gmail.com")
                .subject("it works!")
                .html("<strong>hello world</strong>")
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            return data.getId();
            
        } catch (ResendException e) {
            throw new EmailSendingException(e.getMessage());
        }
    }
    
}
