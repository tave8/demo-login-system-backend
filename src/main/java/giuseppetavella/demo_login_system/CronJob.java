package giuseppetavella.demo_login_system;

import giuseppetavella.demo_login_system.services.AppEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CronJob {
    
    @Autowired private AppEmailService appEmailService;

    private static final Logger LOGGER = Logger.getLogger(CronJob.class.getName());
    

    // this cron means every minute
    @Scheduled(cron = "0 * * * * *")
    public void sendEmail() {

        
        // this.appEmailService.sendMeInvoiceReport();
        // //
        // LOGGER.info("CRON JOB: send email: email sent");

    }
    
}
