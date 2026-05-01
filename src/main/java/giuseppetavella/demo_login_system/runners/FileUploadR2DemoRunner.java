package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.services.AppCsvGenerationService;
import giuseppetavella.demo_login_system.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FileUploadR2DemoRunner implements CommandLineRunner {
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private AppCsvGenerationService appCsvGenerationService;

    @Override
    public void run(String... args) throws Exception {

         byte[] csvBytes = this.appCsvGenerationService.generateArticlesReport();

        // System.out.println(Arrays.toString(csvBytes));
        
        this.fileUploadService.upload("articles_report.csv", csvBytes);
        
    }
}




