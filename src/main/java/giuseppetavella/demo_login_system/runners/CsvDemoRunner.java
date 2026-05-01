package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.helpers.FileHelper;
import giuseppetavella.demo_login_system.models.EmailAttachment;
import giuseppetavella.demo_login_system.models.EmailAttachmentFromURL;
import giuseppetavella.demo_login_system.services.AppCsvGenerationService;
import giuseppetavella.demo_login_system.services.AppEmailService;
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
    private AppEmailService appEmailService;

    @Override
    public void run(String... args) throws Exception {
        

        User user = new User(
                "giuseppetavella8+@gmail.com",
                "1234",
                "Giuseppe",
                "Tavella"
        );

        this.appEmailService.sendArticlesReport(
                user,
                this.appCsvGenerationService.generateArticlesReport()
        );
        
        
    }
}
