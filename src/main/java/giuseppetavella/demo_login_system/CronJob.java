package giuseppetavella.demo_login_system;

import giuseppetavella.demo_login_system.services.AppEmailService;
import giuseppetavella.demo_login_system.services.AppPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CronJob {
    
    @Autowired private AppEmailService appEmailService;
    
    @Autowired private AppPdfService appPdfGenerationService;
    

    // this cron means every minute
    // @Scheduled(cron = "0 * * * * *")
    // public void sendEmail() {
    //    
    //     this.appEmailService.sendMeInvoiceReport();
    //    
    // }
    
}
