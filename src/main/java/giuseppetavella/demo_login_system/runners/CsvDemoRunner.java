package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.helpers.FileHelper;
import giuseppetavella.demo_login_system.models.EmailAttachment;
import giuseppetavella.demo_login_system.models.EmailAttachmentFromURL;
import giuseppetavella.demo_login_system.services.AppCsvGenerationService;
import giuseppetavella.demo_login_system.services.CsvGenerationService;
import giuseppetavella.demo_login_system.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CsvDemoRunner implements CommandLineRunner {

    @Autowired
    private AppCsvGenerationService appCsvGenerationService;
    
    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        
        

        Map<String, Object> vars = Map.of();

        String recipient = "giuseppetavella8@gmail.com";
        String subject = "subject test";
        String html = "<b>hello</b>";

        EmailAttachment emailAttachment1 = new EmailAttachment(
                this.appCsvGenerationService.generateArticlesReportAttachment(),
                "articles_report.csv"
        );

        // EmailAttachment emailAttachment2 = new EmailAttachmentFromURL(
        //         "https://foodandfriends.org/wp-content/uploads/2021/12/CHEW-Lesson-Intuitive-Cooking.pdf",
        //         "i_called_this.pdf"
        // );

        List<EmailAttachment> emailAttachments = List.of(
                emailAttachment1 
                // emailAttachment2
        );

        this.emailService.sendEmailWithAttachments(
                recipient,
                subject,
                html,
                emailAttachments
            );
        
        
    }
}
