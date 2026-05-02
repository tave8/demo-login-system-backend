package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.helpers.FileHelper;
import giuseppetavella.demo_login_system.services.AppCsvGenerationService;
import giuseppetavella.demo_login_system.services.AppPdfGenerationService;
import giuseppetavella.demo_login_system.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Component
public class FileUploadR2DemoRunner implements CommandLineRunner {
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private AppCsvGenerationService appCsvGenerationService;
    
    @Autowired
    private AppPdfGenerationService appPdfGenerationService;

    @Override
    public void run(String... args) throws Exception {

        //  byte[] csvBytes = this.appCsvGenerationService.generateArticlesReport();
        //
        //  byte[] pdfBytes = this.appPdfGenerationService.generateInvoice(Map.of());
        // 
        // 
        // String pdfUrl = this.fileUploadService.upload(pdfBytes, "pdf");
        //
        // System.out.println(pdfUrl);

        
        
    }
}




