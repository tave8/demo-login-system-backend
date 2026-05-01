package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.services.AppEmailService;
import giuseppetavella.demo_login_system.services.AppPdfGenerationService;
import giuseppetavella.demo_login_system.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailTestRunner implements CommandLineRunner {
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private AppEmailService appEmailService;
    
    @Autowired
    private AppPdfGenerationService appPdfGenerationService;

    @Override
    public void run(String... args) throws Exception {

        
        // String emailID = this.emailService.sendEmail("giuseppetavella8@gmail.com", "title!", "<i>whatsup</i>");

        // System.out.println(emailID);
        
        // User user = new User(
        //         "hunjsnajnsajkdna3923njnkjdnjkwendkjwnejkd@gmail.com",
        //         "1234",
        //         "Giuseppe",
        //         "Tavella"
        // );
        //
        // appEmailService.sendVerifyEmail(user);
        
        Map<String, Object> vars = Map.of();
        
        String recipient = "giuseppetavella8@gmail.com";
        String subject = "subject test";
        String html = "<b>hello</b>";
        String outputAttachmentFilename = "i_called_this.pdf";
        String base64FileContent = this.appPdfGenerationService.generateInvoiceAttachment(vars);
        
        this.emailService.sendEmailWithAttachment(
                recipient,
                subject,
                html,
                outputAttachmentFilename,
                base64FileContent
        );
        
        
    }
    
}
