package giuseppetavella.demo_login_system;

import giuseppetavella.demo_login_system.models.EmailAttachment;
import giuseppetavella.demo_login_system.services.AppEmailService;
import giuseppetavella.demo_login_system.services.AppPdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CronJob {
    
    @Autowired private AppEmailService appEmailService;
    
    @Autowired private AppPdfGenerationService appPdfGenerationService;
    

    // this cron means every minute
    // @Scheduled(cron = "0 * * * * *")
    // public void sendEmail() {
    //    
    //     this.appEmailService.sendMeInvoiceReport();
    //    
    // }
    
}
