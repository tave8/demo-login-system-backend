package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.exceptions.EmailSendingException;
import giuseppetavella.demo_login_system.models.EmailAttachment;
import giuseppetavella.demo_login_system.models.EmailAttachmentFromURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

/**
 * Send business-specific emails.
 * 
 * Examples:
 * - welcome on signup
 * - reset password
 * - etc.
 */
@Service
public class AppEmailService extends EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Send welcome email on signup.
     */
    // public void sendWelcome() {
    //     Context context = new Context();
    //     context.setVariable("firstname", "Giuseppe");
    //
    //     String htmlBody = templateEngine.process("emails/signup", context);
    //    
    //     this.sendEmail("giuseppetavella8@gmail.com", "Welcome!", htmlBody);
    // }

    /**
     * Send verify your account email.
     * Should be sent only after signup.
     */
    public void sendVerifyEmail(User user, String verificationUrl) throws EmailSendingException
    {
        
        Context context = new Context();
        context.setVariable("firstname", user.getFirstname());
        context.setVariable("verificationUrl", verificationUrl);

        String htmlBody = templateEngine.process("emails/verify_email", context);

        this.sendEmail(user.getEmail(), "Verify your email", htmlBody);
    }


    /**
     * Send forgot password authorization email.
     */
    public void sendForgotPasswordAuthorization(User user, String verificationUrl) throws EmailSendingException
    {

        Context context = new Context();
        context.setVariable("verificationUrl", verificationUrl);

        String htmlBody = templateEngine.process("emails/forgot_password_authorization", context);

        this.sendEmail(user.getEmail(), "Reset your password", htmlBody);
    }


    /**
     * Send articles report email.
     */
    // public void sendArticlesReport(User user, byte[] articlesReportCsv) throws EmailSendingException 
    // {
    //
    //     Context context = new Context();
    //     context.setVariable("firstname", user.getFirstname());
    //
    //     String htmlBody = templateEngine.process("emails/articles_report", context);
    //    
    //     EmailAttachment attachment = new EmailAttachment(
    //             articlesReportCsv, 
    //             "articles_report.csv"
    //     );
    //
    //     this.sendEmail(
    //             user.getEmail(), 
    //             "Articles Report", 
    //             htmlBody, 
    //             attachment
    //     );
    // }
    

    public void sendPdf(String recipient, String pdfUrl)
    {
        this.sendEmail(
                recipient,
                "Here's your pdf",
                "Hello",
                new EmailAttachmentFromURL(pdfUrl, "pdf_from_internet.pdf")
        );
    }

}
