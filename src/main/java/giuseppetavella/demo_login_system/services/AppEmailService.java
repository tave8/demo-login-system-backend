package giuseppetavella.demo_login_system.services;

import giuseppetavella.demo_login_system.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
    public void sendWelcome() {
        Context context = new Context();
        context.setVariable("firstname", "Giuseppe");

        String htmlBody = templateEngine.process("emails/signup", context);
        
        this.sendEmail("giuseppetavella8@gmail.com", "Welcome!", htmlBody);
    }
    
}
