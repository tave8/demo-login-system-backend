package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.services.AppCsvService;
import giuseppetavella.demo_login_system.services.AppEmailService;
import giuseppetavella.demo_login_system.services.AppPdfService;
import giuseppetavella.demo_login_system.services.base.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileUploadR2DemoRunner implements CommandLineRunner {
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private AppCsvService appCsvGenerationService;
    
    @Autowired
    private AppPdfService appPdfGenerationService;
    
    @Autowired
    private AppEmailService appEmailService;

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
        //
        // this.appEmailService.sendPdf("giuseppetavella8@gmail.com", pdfUrl);
        //
    }
}




