package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.entities.User;
import giuseppetavella.demo_login_system.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvDemoRunner implements CommandLineRunner {

    @Autowired
    private AppCsvGenerationService appCsvGenerationService;
    
    @Autowired
    private AppEmailService appEmailService;
    
    @Autowired
    private MediaUploadService mediaUploadService;

    @Override
    public void run(String... args) throws Exception {
        
        // CSV GENERATION

        // User user = new User(
        //         "giuseppetavella8+@gmail.com",
        //         "1234",
        //         "Giuseppe",
        //         "Tavella"
        // );
        //
        // this.appEmailService.sendArticlesReport(
        //         user,
        //         this.appCsvGenerationService.generateArticlesReport()
        // );


        // CSV UPLOAD
        
        // User user = new User(
        //         "giuseppetavella8+@gmail.com",
        //         "1234",
        //         "Giuseppe",
        //         "Tavella"
        // );
        //
        // String csvUrl = this.mediaUploadService.uploadFile(
        //         this.appCsvGenerationService.generateArticlesReport() 
        // );
        //
        // System.out.println(csvUrl);
        
    }
}
