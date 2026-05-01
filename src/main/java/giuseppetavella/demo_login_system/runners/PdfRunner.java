package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.services.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PdfRunner implements CommandLineRunner {
    
    @Autowired
    private PdfGenerationService pdfGenerationService;
    
    @Override
    public void run(String... args) throws Exception {
        
        // this.pdfGenerationService.generateInvoice();
        
    }
}
