package giuseppetavella.demo_login_system.runners;

import giuseppetavella.demo_login_system.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmailTestRunner implements CommandLineRunner {
    
    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {

        
        this.emailService.sendEmail("giuseppetavella8@gmail.com", "title!", "<i>whatsup</i>");
        
    }
    
}
